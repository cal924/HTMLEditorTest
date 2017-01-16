package com.inet.html.samples;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.inet.editor.BaseEditor;

/**
 * This sample shows how to use the JWebEngine as a text viewer. It demonstrates how to use the system
 * preferences to display HTML content in JEditorPane using JWebEngine. 
 * @author i-net software GmbH 
 */
public class FileViewer extends Applet{
    static{
        // register JWebEngine for HTML content
        JEditorPane.registerEditorKitForContentType( "text/html", "com.inet.html.InetHtmlEditorKit" );
        // register the StylesEditorKit for plain text content - this is the default as well
        JEditorPane.registerEditorKitForContentType( "text/plain", "javax.swing.text.StyledEditorKit" );
    }
    
    private JEditorPane editor;
    
    /**
     * Creates the file editor frame with an inner {@link BaseEditor}.
     */
    public FileViewer(){
    }
    
    @Override
    public void init() {
        super.init();
        // creates the editor
        editor = new JEditorPane();
        // disable editing, this is only a viewer
        editor.setEditable( false );
        // use BorderLayout to maximize the editor 
        this.setLayout( new BorderLayout() );
        this.add( editor, BorderLayout.CENTER );
        this.add( new JButton( new LoadAction() ), BorderLayout.NORTH );
    }
    
    /**
     * Loads a file to the editor. Will do nothing if the path is null
     * @param path the path and file name of the file to open
     */
    private void openFile( String path ){
        if( path != null ){
            File file = new File( path );
            if( file.exists() && file.canRead() ){
                try {
                    // NOTE: JEditorPane.setPage will determine the content type and 
                    // use the JWebEngine on demand here
                    editor.setPage( file.toURI().toURL() );
                } catch( MalformedURLException e1 ) {
                    e1.printStackTrace();
                } catch( IOException e ) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Tries to find the parent Frame of a component
     * @param c the component to get the parent of
     * @return the parent frame or null
     */
    private Frame getParentFrame( Component c ){
        Container parent = c.getParent();
        while( parent != null && !(parent instanceof Frame) ){
            parent = parent.getParent(); 
        }
        return (Frame)parent;
    }
    
    /**
     * Opens an editor frame
     * @param args the command line arguments will be ignored here
     */
    public static void main( String[] args ) {
        JFrame frame = new JFrame();
        frame.setLayout( new BorderLayout() );
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        frame.setTitle( "JWebEngine file viewer sample" );
        FileViewer fileEditor = new FileViewer( );
        fileEditor.init();
        frame.add( fileEditor, BorderLayout.CENTER );
        // set an own default size, since the JEdtiorPane has no content yet
        frame.setSize( 800, 600 );
        // display the component
        frame.setVisible( true );
    }
    
    /**
     * Simple action to open a file-open dialog and set the selected file to the editor 
     */
    private class LoadAction extends AbstractAction{
        
        /**
         * Creates the action and set's it's name to 'Load'
         */
        public LoadAction( ) {
            this.putValue( Action.NAME, "Load" );
        }

        /**
         * Opens the dialog and loads the file, if the dialog was not canceled
         * @param e the event, which activated the action, will be ignored here
         */
        @Override
        public void actionPerformed( ActionEvent e ) {
            FileDialog openDialog = new FileDialog( getParentFrame(FileViewer.this) );
            openDialog.setMode( FileDialog.LOAD );
            openDialog.setVisible( true );
            String path = openDialog.getDirectory();
            openFile( (path != null ? path + File.separator : "") + openDialog.getFile() );
        }
        
    }
}
