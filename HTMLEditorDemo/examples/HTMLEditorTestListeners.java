/*
 * HTMLEditorTest.java
 *
 * Created on 12 February 2004, 08:13
 */
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import sferyx.administration.editors.*;
import javax.swing.text.*;
/**
 *This example shows how to add various listeners to the editor
 *
 */
public class HTMLEditorTestListeners extends JFrame implements FocusListener, DocumentListener{
    HTMLEditor edHTMLEditor;
    JTextArea edJEditorPane;
    public static void main(String args[]) {
        
           try
      {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      }
      catch(Exception exc)
      {
      }  
        
        new HTMLEditorTestListeners();

    }

    public void focusGained(FocusEvent e) {
     
        edJEditorPane.append("Focus gained\n");
    }

    public void focusLost(FocusEvent e) {
        
         edJEditorPane.append("Focus lost\n");
    }

    public void changedUpdate(javax.swing.event.DocumentEvent documentEvent)
    {
       
        edJEditorPane.append("Document Changed\n");
    }

    public void insertUpdate(javax.swing.event.DocumentEvent documentEvent) {
       
        edJEditorPane.append("Insert Update\n");
    }

    public void removeUpdate(javax.swing.event.DocumentEvent documentEvent) {

       
        edJEditorPane.append("Remove Update\n");
    }
 
    /** Creates a new instance of HTMLEditorTest */
    public HTMLEditorTestListeners() {
        Container con = getContentPane();
        con.setLayout(new BorderLayout());//new GridLayout(1,2));
        con.add(edHTMLEditor = new HTMLEditor(),"Center");
        edJEditorPane = new JTextArea();
        JScrollPane scroll=new JScrollPane(edJEditorPane);
        scroll.setPreferredSize(new Dimension(200,400));
        con.add(scroll,"East");

        edHTMLEditor.setContent("Hello, please edit the document and watch the console here for the changes -->");
        edHTMLEditor.getInternalJEditorPane().addFocusListener(this);
        edHTMLEditor.addDocumentListener(this);
        edJEditorPane.addFocusListener(this);

		setSize(800,600);
                setLocation(200,200);
        setVisible(true);
    }



}
