package com.inet.html.samples.paged;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.Document;
import javax.swing.text.View;

import com.inet.html.InetHtmlDocument;
import com.inet.html.InetHtmlEditorKit;
import com.inet.html.InetHtmlFactory;
import com.inet.html.ViewPainter;
import com.inet.html.views.HtmlRootView;

/**
 * This sample shows how to render an HTML page into several page images. This is especially useful for printing
 * or splitting large pages to avoid memory issues.<BR>
 * The sample uses a custom {@link ViewPainter} to control the clipping of all content elements (which are
 * text, images and horizontal rules). Any box element is drawn and clipped like normal.
 */
public class PagedRenderer {

    /**
     * This method renders the HTML referenced by an URL into several images without clipping text or image
     * elements.
     * @param url the page to be rendered
     * @param width the width of a page
     * @param height the height of a page
     * @return the rendered pages, never <code>null</code>
     * @throws IOException for a <code>null</code> or invalid URL specification
     */
    public static List<Image> renderPaged( String url, int width, int height ) throws IOException {
        // prepare the EditorKit with the custom PainterFactory
        final PagedPainterFactory painterFactory = new PagedPainterFactory();
        final InetHtmlFactory viewFactory = new InetHtmlFactory( painterFactory, new InetHtmlFactory.DefaultFontFactory() );
        InetHtmlEditorKit kit = new InetHtmlEditorKit(){
            @Override
            public javax.swing.text.ViewFactory getViewFactory() {
                return viewFactory;
            };
            @Override
            public Document createDefaultDocument() {
                InetHtmlDocument doc = (InetHtmlDocument)super.createDefaultDocument();
                // disable async to make the code shorter; if you require async loading, simply
                // put everything after the setPage-Call into a PropertyChangeListener which listens
                // for a change of "page"
                doc.setAsynchronousLoadPriority(-1);
                // set media to print to cause the renderer to wait for the images to be loaded
                doc.setDocumentProperty( InetHtmlDocument.PROPERTY_MEDIA, InetHtmlDocument.MEDIA_PRINT );
                return doc;
            }            
        };
        
        // Create the EditorPane to load and render the content
        JEditorPane editor = new JEditorPane();
        editor.setEditorKit( kit );
        editor.setPage( url );
        editor.setSize( width, 1 );
        editor.setOpaque( false ); // to avoid getting a look&feel dependent background
        editor.setBorder( new EmptyBorder( 0, 0, 0, 0 ) ); // required to get rid of the 3px borders
        
        // To avoid the overhead of the EditorPane, use only the root render the content
        View root = editor.getUI().getRootView( editor );
        root.setSize( width, 1 ); // this forces the layouter to layout with 'width' and the minimum height possible for the current content
        int requiredHeight = (int)root.getPreferredSpan( View.Y_AXIS );
        editor.setSize( width, requiredHeight ); 
        
        List<Image> images = new ArrayList<Image>();
        int yOffset = 0;
        while(true){
            // create page image
            BufferedImage img = new BufferedImage( width, height, BufferedImage.TYPE_INT_ARGB );
            images.add( img );
            
            // create sub-graphics to translate the clip into the image area
            Graphics g2d = img.getGraphics().create( 0, -yOffset, img.getWidth(), yOffset + img.getHeight() );
            g2d.setClip( 0, yOffset, img.getWidth(), img.getHeight() );
            
            // reset latest y-offset of the painter factory and render the page
            painterFactory.reset();
            editor.paint( g2d );
            yOffset = painterFactory.getNextYOffset();
                        
            if( yOffset == Integer.MAX_VALUE ){
                // NOTE: this yOffset can mean two different states: either this was the last page so nothing
                // was clipped OR no elements hit the lowe bound of the clip(in that case this was NOT the last page)
                
                int maxY = (int)g2d.getClipBounds().getMaxY();
                if( maxY >= requiredHeight ){
                    // no clipping and the clip covered the bottom area of the document -> this was the last page
                    break;
                } else {
                    // the clip did only cover an area somewhere within the document, continue
                    yOffset = maxY;
                }
            }
        }
        return images;
    }
    
    /**
     * Illustration of the CSS2.1 spec main page rendered into 800x600 images 
     */
    public static void main( String[] args ) throws IOException {
        final List<Image> images = renderPaged( "http://www.w3.org/TR/CSS2/", 800, 600 );
        
        JFrame f = new JFrame( "Pages" );
        f.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        f.setLayout( new BorderLayout() );
        final JLabel label = new JLabel( new ImageIcon( images.get( 0 ) ) );
        f.add( label, BorderLayout.CENTER );
        final SpinnerNumberModel model = new SpinnerNumberModel( 0, 0, images.size()-1, 1 );
        final JSpinner spinner = new JSpinner( model );
        f.add( spinner, BorderLayout.NORTH );
        spinner.addChangeListener( new ChangeListener() {
            @Override
            public void stateChanged( ChangeEvent e ) {
                label.setIcon( new ImageIcon( images.get( model.getNumber().intValue() ) ) );
            }
        });
        f.pack();
        f.setVisible( true );
    }
}
