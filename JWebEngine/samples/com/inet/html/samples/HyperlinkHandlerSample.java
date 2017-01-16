package com.inet.html.samples;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import com.inet.editor.BaseEditor;
import com.inet.editor.handler.HyperlinkHandler;

/**
 * This sample demonstrates how to customize the {@link BaseEditor}s reaction on hyperlinks. 
 * The {@link BaseEditor} offers the possibility to register a handler instance to modify
 * the internal {@link HyperlinkListener}.<BR>
 * In this sample a handler will be created which restricts all actions on hyperlinks 
 * to the domain &quot;localhost&quot;
 * @author i-net software GmbH 
 */
public class HyperlinkHandlerSample extends Applet{

    private static final String CONTENT = 
        "<html><body>" +
        "<a href=\"http://localhost/allowed\" title=\"local allowed\">allowed localhost URL</a><br>" +
        "<a href=\"file://file.txt\">allowed file URL</a><br>" +
        "<hr>" +
        "<a href=\"http://www.ebay.com\" style=\"color:red\">forbidden external URL</a><br>" +
        "<a href=\"http://192.168.0.123\" style=\"color:red\">forbidden external URL with IP</a><br>" +
        "</body></html>";
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        super.init();
        
        // we use the file editor sample here and modify it's hyperlink handler
        FileEditor fileEditor = new FileEditor( null );
        fileEditor.init();
        modifyHandler( fileEditor.getEditor() );
        
        this.setLayout( new BorderLayout() );
        this.add( fileEditor, BorderLayout.CENTER );
    }
    
    /**
    * Opens an editor frame
    * @param args the command line arguments will be ignored here
    */
    public static void main( String[] args ) {
        JFrame frame = new JFrame();
        frame.setLayout( new BorderLayout() );
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        frame.setTitle( "JWebEngine hyperlink handler sample" );
        
        // we use the file editor sample here and modify it's hyperlink handler
        FileEditor fileEditor = new FileEditor( null );
        fileEditor.init();
        modifyHandler( fileEditor.getEditor() );
        frame.add( fileEditor );
        
        // NOTE: DO NOT PACK THE BaseEditor!
        // The BaseEditor has a very small minimum size since it's toolbar is scalable. So, if you pack
        // this component it will be minimized to the width of the smallest button.
        frame.setSize( 800, 600 );
        // display the component
        frame.setVisible( true );
        
    }
    
    /**
     * Sets the custom handler to a given {@link BaseEditor}
     * @param baseEditor the {@link BaseEditor} to modify
     */
    private static void modifyHandler( BaseEditor baseEditor ){        
        // now we set our handler
        baseEditor.setCustomHyperlinkHandler( new Handler() );
        // to show the effect of the handler we insert some content here
        // NOTE: The editor will not display tooltips for forbidden locations as well here!
        baseEditor.setText( CONTENT, true );
        // this disables the toolbar and set the editor to not editable
        baseEditor.setTextShowMode();
    }
    
    /**
     * This is our {@link HyperlinkHandler} which will consume all events which point to an
     * external location.
     */
    private static class Handler implements HyperlinkHandler{

        /**
         * Checks if an URL will not redirect to any domain different do localhost
         * @param url the URL to be checked
         * @return true if the url is null or references to localhost
         */
        private boolean allowAccess( URL url ){
            return url == null || // no URL will cause no reaction on activate so it is allowed here 
                "localhost".equalsIgnoreCase( url.getHost() ) || // check for localhost domain
                "file".equals( url.getProtocol() ); // files are on localhost as well!            
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean processActivated( HyperlinkEvent event ) {
            // if the URL is NOT allowed we return true to mark the event as processed
            URL url = event != null ? event.getURL() : null;
            if( allowAccess( url) ){
                return false;
            } else {
                System.err.println("ERROR: You are not allowed to open the location '" + url + "'" );
                return true;
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean processEntered( HyperlinkEvent event ) {
            // if the URL is NOT allowed we return true to mark the event as processed
            return !allowAccess( event != null ? event.getURL() : null);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean processExited( HyperlinkEvent event ) {
            // if the URL is NOT allowed we return true to mark the event as processed
            return !allowAccess( event != null ? event.getURL() : null);
        }
        
    }
    
}
