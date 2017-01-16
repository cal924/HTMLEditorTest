package com.inet.html.samples;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.inet.editor.BaseEditor;
import com.inet.editor.BaseEditorToolBar;

/**
 * This sample demonstrates how to modify the toolbar of the {@link BaseEditor}
 * @author i-net software GmbH 
 */
public class ToolbarCustomizingExample{

    /** An {@link BaseEditor} with modified toolbar components  */
    private static class ModifiedEditor extends BaseEditor{
        
        /** Create an editor with modified controls */
        public ModifiedEditor() {
            // NOTE: You can only modify the initial toolbar, so it's recommended to start
            // with HTML controls activated. Otherwise these controls will be added
            // later on demand
            super( true );
        }
        
        /** {@inheritDoc} */
        @Override
        protected boolean hasFormatButton() {
            // NOTE: Only overwritten to show that this is the way to remove the format or show it on demand
            return super.hasFormatButton();
        }
        
        /** {@inheritDoc} */
        @Override
        protected void addButtons( List<Component> defaultComponents, List<Component> editComponents, List<Component> formatComponents ) {
            super.addButtons( defaultComponents, editComponents, formatComponents );
            // This method is called by the toolbar controller after all buttons have been created
            // and before the buttons are added to the GUI.
            // Every change on these lists will be persistent and reflected by the GUI(even the order)
            // If you want to hide a component, remove it since the visibility is adjusted by the toolbar controller.
            
            // Example: remove the font family combo box
            Iterator<Component> i = formatComponents.iterator();
            while( i.hasNext() ){
                Component comp = i.next();
                // NOTE: each component which is not a separator has a name which equals one
                // of the BaseEditorToolBar.DEFAULT_COMPONENTS enum constant names
                if( BaseEditorToolBar.DEFAULT_COMPONENTS.cmbFontFamily.name().equals( comp.getName() ) ){
                    i.remove();
                    break;
                }
            }
            
            // reorder the remaining format components
            Collections.reverse( formatComponents );
        }
    }
    
    /**
     * Displays a frame to show the modified editor
     * @param args will be ignored
     */
    public static void main( String[] args ) {
        JFrame frame = new JFrame( "JWebEngine toolbar customizing example" );
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        frame.setLayout( new BorderLayout() );
        frame.add( new ModifiedEditor(), BorderLayout.CENTER );
        frame.setSize( 600, 600 );
        frame.setVisible( true );
    }
}
