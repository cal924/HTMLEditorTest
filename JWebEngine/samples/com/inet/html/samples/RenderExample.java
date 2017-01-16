package com.inet.html.samples;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.plaf.basic.BasicTextPaneUI;
import javax.swing.text.EditorKit;
import javax.swing.text.JTextComponent;

import com.inet.html.InetHtmlConfiguration;
import com.inet.html.InetHtmlEditorKit;

/**
 * Side by side render comparison of the Java HTML renderer and the JWebEngine. 
 * The editors in this example are not editable to support browsing.
 */
public class RenderExample extends Applet implements ActionListener{
    
    private JEditorPane webEngine;
    private JEditorPane sunEngine;
    private JTextField address;
    private JSplitPane split;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        super.start();
        initContent();
        setName( "JWebEngine Render Example" );
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setVisible( boolean b ) {
        super.setVisible( b );
        if( b == true ){
            // For some reason the caret does not work correctly if this is called before the
            // editors are visible
            String urlString = getParameter( "location" );
            URL location = null;
            try {
                location = new URL("http://www.inetsoftware.de");
            } catch( MalformedURLException e1 ) {
                // ignore, url is correct
            }
            try{
                location = new URL( urlString );
            } catch( MalformedURLException e ){
                System.err.println("The location parameter is not a valid URL: " + urlString );
            }
            setPages( location );
            split.setDividerLocation( 0.5f );
        }
    }
    
    /**
     * Creates the GUI
     */
    private void initContent(){
        split = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
        split.setResizeWeight( 0.5f );

        // We use a kit instance override here to avoid that the UI sets the kit by
        // the content type
        final InetHtmlEditorKit kit = new InetHtmlEditorKit();
        kit.setDefaultConfig( InetHtmlConfiguration.getBrowserConfig() );
        webEngine = new JEditorPane();
        webEngine.setOpaque( false );
        webEngine.setEditorKit( kit );
        webEngine.setUI( new BasicTextPaneUI(){
            @Override
            public EditorKit getEditorKit( JTextComponent tc ) {
                return kit;
            }
        });
        JScrollPane webScroll = new JScrollPane( webEngine );
        webScroll.setDoubleBuffered( true );
        sunEngine= new JEditorPane();
        
        JScrollPane sunScroll = new JScrollPane( sunEngine );
        Dimension size = new Dimension( 500, 800 );
        webScroll.setPreferredSize( size );
        sunScroll.setPreferredSize( size );
        
        Listener l = new Listener();
        webEngine.addHyperlinkListener( l );
        sunEngine.addHyperlinkListener( l );
        webEngine.setEditable( false );
        sunEngine.setEditable( false );
        
        JPanel webEnginePanel = new JPanel( new BorderLayout() );
        webEnginePanel.add( webScroll, BorderLayout.CENTER );
        webEnginePanel.add( new JLabel( "JWebEngine Renderer" ), BorderLayout.NORTH );
        
        JPanel sunEnginePanel = new JPanel( new BorderLayout() );
        sunEnginePanel.add( sunScroll, BorderLayout.CENTER );
        sunEnginePanel.add( new JLabel( "Sun Java(TM) internal HTML Renderer" ), BorderLayout.NORTH );
        
        split.add( webEnginePanel );
        split.add( sunEnginePanel );
        
        this.setLayout( new BorderLayout( 5, 5 ) );
        this.add( split, BorderLayout.CENTER );
        
        JPanel north = new JPanel( new BorderLayout() );
        address = new JTextField();
        address.addActionListener( this );
        north.add( address, BorderLayout.CENTER );
        north.add( new JLabel( "Address: " ), BorderLayout.WEST );
        this.add( north, BorderLayout.NORTH );
    }
    
    /**
     * Loads an address to both editors
     * @param page the address to load
     */
    private void setPages( URL page){
        try {
            webEngine.setPage( page );
        } catch( IOException e ) {
            e.printStackTrace();
        }
        try {
            sunEngine.setPage( page );
        } catch( IOException e ) {
            e.printStackTrace();
        }
        address.setText( page.toString() );
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed( ActionEvent e ) {
        try {
            URL url = new URL( address.getText() );
            setPages( url );
            return;
        } catch( MalformedURLException e1 ) {            
        }
        try {
            // give it a second try, maybe the protocol was forgotten
            URL url = new URL( "http://" + address.getText() );
            setPages( url );
            return;
        } catch( MalformedURLException e1 ) {            
            e1.printStackTrace();
        }        
    }
    
    /**
     * Creates a standalone frame for this example
     * @param args the command line arguments will be ignored
     */
    public static void main( String[] args ) {
        JFrame frame = new JFrame( "JWebEngine Render Example" );
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        frame.setLayout( new BorderLayout() );   
        RenderExample example = new RenderExample();
        example.initContent();
        frame.add( example, BorderLayout.CENTER );
        frame.pack();
        frame.setVisible( true );
        URL location = null;
        try {
            location = new URL("http://www.inetsoftware.de");
        } catch( MalformedURLException e ) {
        }
        if( args.length > 0 ){
            try {
                location = new URL( args[0] );
            } catch( MalformedURLException e ) {
            }
        }
        example.setPages( location );
    }
    
    /**
     * A simple hyperlink listener which reacts only on click
     */
    private class Listener implements HyperlinkListener {

        /**
         * {@inheritDoc}
         */
        @Override
        public void hyperlinkUpdate( HyperlinkEvent e ) {
            if( e.getEventType() == HyperlinkEvent.EventType.ACTIVATED ){
                setPages( e.getURL() );
            }
        }
        
    }

}
