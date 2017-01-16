/*
 * Copyright 2002-2009 Sferyx Srl. All rights reserved.
 * HTMLEditorSingleLineSpacing.java
 *
 * Created on 12 February 2004, 08:13
 */
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import sferyx.administration.editors.*;
import javax.swing.text.*;
/**
 *This example shows how to make the toolbars wrap within a smaller space.
 *
 */
public class HTMLEditorSingleLineSpacing extends JFrame 
{
    HTMLEditor htmlEditor;

    
    public static void main(String args[]) 
    {
        new HTMLEditorSingleLineSpacing();

    }

   
    public HTMLEditorSingleLineSpacing() 
    {
        
         try
      {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      }
      catch(Exception exc)
      {
      }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container con = getContentPane();
        htmlEditor = new HTMLEditor();
        htmlEditor.setSingleParagraphSpacing(true);
        htmlEditor.setContent("This example shows the single line paragraph spacing to emulate MS Word like text editing");
        htmlEditor.setSourceEditorVisible(false);
        htmlEditor.setPreviewVisible(false);
        con.add(htmlEditor);
        pack();
	setSize(700,500);
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-getWidth()/2,Toolkit.getDefaultToolkit().getScreenSize().height/2-getHeight()/2);
        setVisible(true);
    }



}
