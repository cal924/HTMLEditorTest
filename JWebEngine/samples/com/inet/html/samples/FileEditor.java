package com.inet.html.samples;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import com.inet.editor.BaseEditor;

/**
 * This sample shows how to use the JWebEngine as a text editor. This sample uses the {@link BaseEditor}
 * class to use all actions and the optional spell checker component. To activate the spell checker, please
 * add the JOrtho libraries and dictionaries to the class path when running this sample. 
 * @author i-net software GmbH 
 */
public class FileEditor extends JApplet{
    
    private BaseEditor editor;
    private File lastOpen = null;
    private final String openFirst;
    
    /**
     * Creates the file editor applet with an inner {@link BaseEditor}.
     */
    public FileEditor(){
        this.openFirst = null;        
    }
    
    /**
     * Creates the file editor frame with an inner {@link BaseEditor}.
     * @param openFirst an optional file path to open a file at startup, may be null
     */
    public FileEditor(String openFirst){
        this.openFirst = openFirst;        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        super.init();
        // create the editor with a scrollable text area
        editor = new BaseEditor( true );
        // use BorderLayout to maximize the editor 
        this.setLayout( new BorderLayout() );
        this.add( editor, BorderLayout.CENTER );
        // Gets the default toolbar of the editor. This toolbar can be null in case
        // the editor is used as a display-only component
        JToolBar toolbar = editor.getToolbar();
        if( toolbar != null ){
            // add our own actions to load and save
            toolbar.add( new LoadAction() );
            toolbar.add( new SaveAction() );
            // NOTE: custom actions will always be added to the 'general' group of the BaseEditor
            // toolbar. This group is the first one on the toolbar an will be displayed to the left.
        }
        openFile( openFirst );
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
                    editor.setPage( file.toURI().toURL() );
                    lastOpen = file;
                } catch( MalformedURLException e1 ) {
                    e1.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Returns the editor to apply custom settings
     * @return the base editor used by this instance
     */
    public BaseEditor getEditor(){
        return editor;
    }
    
    /**
     * Opens an editor frame
     * @param args if the first argument is set and a valid path/filename it will be loaded
     */
    public static void main( String[] args ) {
        JFrame frame = new JFrame();
        frame.setLayout( new BorderLayout() );
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        frame.setTitle( "JWebEngine file editor sample" );
        FileEditor fileEditor = new FileEditor( args != null && args.length > 0 ? args[0] : null );
        fileEditor.init();
        // NOTE: DO NOT PACK THE BaseEditor!
        // The BaseEditor has a very small minimum size since it's toolbar is scalable. So, if you pack
        // this component it will be minimized to the width of the smallest button.
        frame.add( fileEditor );
        frame.setSize( 800, 600 );
        // display the component
        frame.setVisible( true );
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
            Frame parent = getParentFrame( FileEditor.this );
            FileDialog openDialog = new FileDialog( parent );
            openDialog.setMode( FileDialog.LOAD );
            openDialog.setVisible( true );
            String path = openDialog.getDirectory();
            openFile( (path != null ? path + File.separator : "") + openDialog.getFile() );
        }
        
    }
    
    /**
     * Simple action to open a file-save dialog and set the selected file to the editor 
     */
    private class SaveAction extends AbstractAction{
        
        /**
         * Creates the action and set's it's name to 'Save'
         */
        public SaveAction( ) {
            this.putValue( Action.NAME, "Save" );
        }

        /**
         * Opens the dialog and saves the editor content to file, if the dialog was not canceled
         * @param e the event, which activated the action, will be ignored here
         */
        @Override
        public void actionPerformed( ActionEvent e ) {
            Frame parent = getParentFrame( FileEditor.this );
            FileDialog saveDialog = new FileDialog( parent );
            if( lastOpen != null ){
                // set the default location to the last opened file
                saveDialog.setFile( lastOpen.getName() );
                saveDialog.setDirectory( lastOpen.getParent() );
            }
            saveDialog.setMode( FileDialog.SAVE );
            saveDialog.setVisible( true );
            String fileName = saveDialog.getFile();
            String path = saveDialog.getDirectory();
            if( fileName != null ){
                File file = new File( (path != null ? path + File.separator : "") + fileName );
                FileWriter out = null;
                try {
                    out = new FileWriter( file );
                    String text = editor.getText();
                    out.write( text );
                    out.close();
                } catch( IOException e1 ) {
                    e1.printStackTrace();
                }
            }
        }
        
    }
}
