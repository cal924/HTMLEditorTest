package com.inet.html.samples;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

import com.inet.html.InetHtmlEditorKit;
import com.inet.html.InetHtmlFactory;
import com.inet.html.ViewPainter;
import com.inet.html.css.HTML;
import com.inet.html.utils.Logger;
import com.inet.html.views.ImageView;

/**
 * This sample shows how to display custom images which cannot be resolved by a normal URL. To do
 * so this {@link ImageView} class has to be extended and the {@link InetHtmlFactory} has to be modified
 * to provide the overwritten instance instead of a normal {@link ImageView}.
 */
public class CustomImageLoading {

    /**
     * A simple frame to show the custom image loading in action
     * @param args will be ignored
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom ImageView Sample");
        frame.setLayout(new BorderLayout());
        JEditorPane p = new JEditorPane();
        p.setEditorKit(new ExtEditorKit());
        p.setContentType( "text/html" );
        p.setText("<img src=\"inetsoftware#lib/tpl/inettpl-3/images/logo.png\">");
        frame.add(p, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }

    /**
     * An extended InetHtmlEditorKit to provide the custom view factory instead of the default one
     */
    public static class ExtEditorKit extends InetHtmlEditorKit {

        /**
         * {@inheritDoc}
         */
        @Override
        public ViewFactory getViewFactory() {
            return new ExtViewFactory();
        }
    }

    /**
     * An extended InetHtmlFactory to provide the custom image view class
     */
    public static class ExtViewFactory extends InetHtmlFactory {

        /**
         * {@inheritDoc}
         */
        @Override
        public View create(Element elem) {
            AttributeSet attrs = elem.getAttributes();
            Object tag = attrs.getAttribute(StyleConstants.NameAttribute);
            if (tag == HTML.Tag.IMG) {
                // Views für IMG-Elemten ändern
                return new ExtImageView(elem);
            }

            return super.create(elem);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ImageView createImage(Element elem) {
            // Just a note:
            // Despite it's name method if NOT used for IMG-elements. It's called to create
            // the list item marker in case there are image list markers.
            return super.createImage(elem);
        }
    }

    /**
     * This image view has the ability to load images other than only be URL.openStream() 
     */
    public static class ExtImageView extends ImageView {

        public static final String IMAGE_PREFIX = "inetsoftware#"; 
        
        private Image image;
        
        /**
         * Creates a new image view for an element
         * @param elem the element to create the view for, must not be <code>null</code>
         */
        public ExtImageView(Element elem) {
            super(elem);
        }

        /**
         * Creates a new image view for an element
         * @param elem the element to create the view for, must not be <code>null</code>
         * @param painter the painter instance to control paged printing, may be <code>null</code> 
         */
        public ExtImageView(Element elem, ViewPainter painter) {
            // NOTE: If you wan't to combine this sample with paged printing, don't forget to
            // call this constructor with the correct view painter! 
            super(elem, painter);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public synchronized Image getImage() {
            if( image != null ){
                return image;
            }

            String src = getImageSrc();
            
            if ( isCustomImageReference( src ) ) {
                image = resolveCustomImageSrc( src );
            } else { 
                image = super.getImage();
            }
            return image;
        }

        /**
         * Determines whether the SRC attribute if an image element references a an image by URL
         * or has to be resolved by the additional implementation
         * @param src the src attribute content
         * @return <code>true</code> if it references a custom image, <code>false</code> if the default 
         * implementation can be used
         */
        private boolean isCustomImageReference( String src ){
            // Put your code to identify custom images here:
            return src != null && src.toLowerCase().startsWith( "inetsoftware#" );
        }
        
        /**
         * This method resolve the src attribute for a custom image to the actual image instance
         * @param src the src attribute if the IMG element
         * @return the image created according to the src attribute, may be <code>null</code> 
         */
        private Image resolveCustomImageSrc( String src ) {
            // Put your code here to load custom images:
            URL url;
            try {
                url = new URL("http://www.inetsoftware.de/" + src.substring( IMAGE_PREFIX.length() ) );
            } catch( MalformedURLException e ) {
                Logger.error( e );
                return null;
            }                
            
            InputStream in;
            try {
                in = url.openStream();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buffer = new byte[16384];
                int length = 0;
                try{
                    while( (length = in.read( buffer )) >= 0 ) {
                        out.write( buffer, 0, length );
                    }
                    out.close();
                } finally {
                    in.close();
                }
                return Toolkit.getDefaultToolkit().createImage( out.toByteArray() );
            } catch( IOException e ) {
                Logger.error( e );
                return null;
            }

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isLoadComplete() {
            String src = getImageSrc();
            if ( isCustomImageReference( src ) ) {
                // If there is no caching these images are loaded instantaneously. The image view
                // will always display the images as missing until this method returns true!
                return true;
            }
            return super.isLoadComplete();
        }
    }
}