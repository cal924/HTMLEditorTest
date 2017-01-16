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
public class HTMLEditorReadOnlySections extends JFrame 
{
    HTMLEditor htmlEditor;

    
    public static void main(String args[]) 
    {
        new HTMLEditorReadOnlySections();

    }

   
    public HTMLEditorReadOnlySections() 
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
       // htmlEditor.setSingleParagraphSpacing(true);
        htmlEditor.setContent("This example shows how read-only elements can be defined within the editor - you can use this feature to create <b>templates</b> or whatever you like:<span readonly=\"true\" style=\"font-size:18px;background-color:yellow;color:red;\">This is read-only text</span> And here is a normal text <br><p  readonly=\"true\" style=\"font-size:18px;background-color:yellow;color:red;\">This is a readonly paragraph<br> with multiple lines</p>Here is some normal text, and <table readonly=\"true\"  style=\"font-size:18px;background-color:yellow;color:red;\"><tr><td>This is a readonly table</td></tr></table>Again normal text and <div  readonly=\"true\"  style=\"font-size:18px;background-color:yellow;color:red;\">This is a readonly DIV element</div>");
        //htmlEditor.setSourceEditorVisible(false);
        //htmlEditor.setPreviewVisible(false);
        con.add(htmlEditor);
        pack();
	setSize(700,500);
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-getWidth()/2,Toolkit.getDefaultToolkit().getScreenSize().height/2-getHeight()/2);
        setVisible(true);
    }



}
