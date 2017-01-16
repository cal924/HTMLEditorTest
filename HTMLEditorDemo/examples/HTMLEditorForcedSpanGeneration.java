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
public class HTMLEditorForcedSpanGeneration extends JFrame 
{
    HTMLEditor htmlEditor;

    
    public static void main(String args[]) 
    {
        new HTMLEditorForcedSpanGeneration();

    }

   
    public HTMLEditorForcedSpanGeneration() 
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
        htmlEditor.setForcedSpanGeneration(true);
        htmlEditor.setContent("<span style=\" font-size:14pt;\">This example shows how to enable </span><span style=\" font-weight:bold; background-color:#ffff00; font-size:14pt;\">SPAN</span><span style=\" font-size:14pt;\"> generation instead of </span><span style=\" font-weight:bold; background-color:#ffff00; font-size:14pt;\">FONT</span><span style=\" font-size:14pt;\"> tags in order to avoid use of deprecated HTML tags and generate standards compliant content</span>");
        con.add(htmlEditor);
        pack();
	setSize(700,500);
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-getWidth()/2,Toolkit.getDefaultToolkit().getScreenSize().height/2-getHeight()/2);
        setVisible(true);
    }



}
