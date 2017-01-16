/*
 * HTMLEditorCustomizationDemo.java
 *
 * Created on 6 febbraio 2006, 16.10
 */
import javax.swing.*;
import javax.swing.text.html.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.io.*;
import javax.swing.event.*;
/**
 *
 * @author  Vassil Boyadjiev
 */
public class HTMLEditorCustomizationDemo extends javax.swing.JFrame {
    
     sferyx.administration.editors.HTMLEditor htmlEditor;
    
     
     static
     {
            try
      {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
       
      }
      catch(Exception exc)
      {
      }  
     }
     
    JFrame jf; 
    /** Creates new form HTMLEditorCustomizationDemo */
    public HTMLEditorCustomizationDemo() {
        initComponents();
   
      setSize(350,650);
      setLocation(850,200);
      setResizable(false);
        
      jf=new JFrame();
      jf.setBackground(Color.white);
      jf.setTitle("Sferyx HTML Editor");
     
     // CustomXMLTagsGrammarSpecification.setTreatAllBlockTagsAsBlocks(true);
     
     
     
     htmlEditor=new sferyx.administration.editors.HTMLEditor();
      
      jf.getContentPane().setLayout(new BorderLayout());
      jf.getContentPane().add("Center", htmlEditor);
        
      jf.setSize(800,600);
      jf.setLocation(100,100);
      jf.show();
      jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
      jf.setIconImage(htmlEditor.getSharedIcon("sferyx-logo").getImage());
      setIconImage(htmlEditor.getSharedIcon("sferyx-logo").getImage());
      
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        hideSourceEditor = new javax.swing.JCheckBox();
        hideMainMenu = new javax.swing.JCheckBox();
        hideFormattingToolbar = new javax.swing.JCheckBox();
        hideShortcutsToolbar = new javax.swing.JCheckBox();
        hidePreview = new javax.swing.JCheckBox();
        hideStatusBar = new javax.swing.JCheckBox();
        disablePopupMenu = new javax.swing.JCheckBox();
        guiLabel = new javax.swing.JLabel();
        guiLabel1 = new javax.swing.JLabel();
        translateButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        loadStyleSheetButton = new javax.swing.JButton();
        removeItems = new javax.swing.JButton();
        restoreDefaults = new javax.swing.JButton();
        jSeparator111 = new javax.swing.JSeparator();
        removeToolbarItems = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        changeIcon = new javax.swing.JButton();
        eventsLogScrollPane = new javax.swing.JScrollPane();
        eventsLog = new javax.swing.JTextArea();
        addDocumentListener = new javax.swing.JButton();
        getContentButton = new javax.swing.JButton();
        getContentButton1 = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

        getContentPane().setLayout(null);

        setTitle("Sferyx HTMLEditor Demo");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        hideSourceEditor.setText("Hide Source Editor");
        hideSourceEditor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                hideSourceEditorItemStateChanged(evt);
            }
        });

        getContentPane().add(hideSourceEditor);
        hideSourceEditor.setBounds(10, 40, 140, 30);

        hideMainMenu.setText("Hide Main Menu");
        hideMainMenu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                hideMainMenuItemStateChanged(evt);
            }
        });

        getContentPane().add(hideMainMenu);
        hideMainMenu.setBounds(180, 40, 120, 30);

        hideFormattingToolbar.setText("Hide Formatting Toolbar");
        hideFormattingToolbar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                hideFormattingToolbarItemStateChanged(evt);
            }
        });

        getContentPane().add(hideFormattingToolbar);
        hideFormattingToolbar.setBounds(10, 70, 170, 30);

        hideShortcutsToolbar.setText("Hide Shortcuts Toolbar");
        hideShortcutsToolbar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                hideShortcutsToolbarItemStateChanged(evt);
            }
        });

        getContentPane().add(hideShortcutsToolbar);
        hideShortcutsToolbar.setBounds(180, 70, 170, 30);

        hidePreview.setText("Hide Preview");
        hidePreview.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                hidePreviewItemStateChanged(evt);
            }
        });

        getContentPane().add(hidePreview);
        hidePreview.setBounds(10, 100, 110, 30);

        hideStatusBar.setText("Hide status bar");
        hideStatusBar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                hideStatusBarItemStateChanged(evt);
            }
        });

        getContentPane().add(hideStatusBar);
        hideStatusBar.setBounds(180, 100, 111, 30);

        disablePopupMenu.setText("Disable Popup Menu");
        disablePopupMenu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                disablePopupMenuItemStateChanged(evt);
            }
        });

        getContentPane().add(disablePopupMenu);
        disablePopupMenu.setBounds(10, 130, 170, 30);

        guiLabel.setFont(new java.awt.Font("Arial", 1, 14));
        guiLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        guiLabel.setText("API Demo - GUI Customization Features:");
        getContentPane().add(guiLabel);
        guiLabel.setBounds(20, 0, 290, 30);

        guiLabel1.setFont(new java.awt.Font("Arial", 1, 14));
        guiLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        guiLabel1.setText("Content manipulation features:");
        getContentPane().add(guiLabel1);
        guiLabel1.setBounds(40, 290, 250, 30);

        translateButton.setText("Translate in German");
        translateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                translateButtonActionPerformed(evt);
            }
        });

        getContentPane().add(translateButton);
        translateButton.setBounds(10, 440, 150, 26);

        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(10, 30, 320, 2);

        getContentPane().add(jSeparator11);
        jSeparator11.setBounds(10, 318, 320, 2);

        loadStyleSheetButton.setText("Load Style Sheet");
        loadStyleSheetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadStyleSheetButtonActionPerformed(evt);
            }
        });

        getContentPane().add(loadStyleSheetButton);
        loadStyleSheetButton.setBounds(170, 440, 150, 26);

        removeItems.setText("Remove \"Window\" & \"Help\" Menus");
        removeItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeItemsActionPerformed(evt);
            }
        });

        getContentPane().add(removeItems);
        removeItems.setBounds(10, 170, 310, 26);

        restoreDefaults.setBackground(new java.awt.Color(204, 204, 204));
        restoreDefaults.setFont(new java.awt.Font("Arial", 1, 12));
        restoreDefaults.setForeground(new java.awt.Color(204, 0, 0));
        restoreDefaults.setText("Restore defaults");
        restoreDefaults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreDefaultsActionPerformed(evt);
            }
        });

        getContentPane().add(restoreDefaults);
        restoreDefaults.setBounds(10, 560, 150, 29);

        getContentPane().add(jSeparator111);
        jSeparator111.setBounds(10, 540, 320, 2);

        removeToolbarItems.setText("Remove \"Fonts\",  \"Insert Image\" & \"Open\" icons");
        removeToolbarItems.setToolTipText("null");
        removeToolbarItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeToolbarItemsActionPerformed(evt);
            }
        });

        getContentPane().add(removeToolbarItems);
        removeToolbarItems.setBounds(10, 200, 309, 26);

        addButton.setText("Add toolbar button with action");
        addButton.setToolTipText("null");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        getContentPane().add(addButton);
        addButton.setBounds(10, 230, 310, 26);

        changeIcon.setText("Change icon");
        changeIcon.setToolTipText("null");
        changeIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeIconActionPerformed(evt);
            }
        });

        getContentPane().add(changeIcon);
        changeIcon.setBounds(10, 260, 310, 26);

        eventsLogScrollPane.setViewportView(eventsLog);

        getContentPane().add(eventsLogScrollPane);
        eventsLogScrollPane.setBounds(10, 360, 310, 70);

        addDocumentListener.setText("Add document listener - Log below:");
        addDocumentListener.setToolTipText("null");
        addDocumentListener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDocumentListenerActionPerformed(evt);
            }
        });

        getContentPane().add(addDocumentListener);
        addDocumentListener.setBounds(10, 330, 310, 26);

        getContentButton.setText("getContent");
        getContentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getContentButtonActionPerformed(evt);
            }
        });

        getContentPane().add(getContentButton);
        getContentButton.setBounds(10, 480, 150, 26);

        getContentButton1.setText("setContent");
        getContentButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getContentButton1ActionPerformed(evt);
            }
        });

        getContentPane().add(getContentButton1);
        getContentButton1.setBounds(170, 480, 150, 26);

        exitButton.setBackground(new java.awt.Color(204, 204, 204));
        exitButton.setFont(new java.awt.Font("Arial", 1, 12));
        exitButton.setForeground(new java.awt.Color(0, 0, 204));
        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        getContentPane().add(exitButton);
        exitButton.setBounds(170, 560, 150, 29);

        pack();
    }//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // Add your handling code here:
        JOptionPane.showMessageDialog(htmlEditor, "<html><h3>Thank you for using our demo!</h3>If you have questions regarding our products <br>do not hesitate to check our website at <b><font color=\"blue\">http://www.sferyx.com</font></b><br> or drop a line to <b><font color=\"blue\">support@sferyx.com</font></b></html>");
        System.exit(0);
        
    }//GEN-LAST:event_exitButtonActionPerformed

    private void getContentButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getContentButton1ActionPerformed
        // Add your handling code here:
        JOptionPane.showMessageDialog(htmlEditor, "Setting the content of the editor to the following:\n<h1>This is some HTML content inserted from external of the editor button</h1>");
        htmlEditor.setContent("<h1>This is some HTML content inserted from external of the editor button</h1>");
        
    }//GEN-LAST:event_getContentButton1ActionPerformed

    private void getContentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getContentButtonActionPerformed
        // Add your handling code here:
        
         JOptionPane.showMessageDialog(htmlEditor, "Currently the content of the editor is the following:\n"+htmlEditor.getContent());
        
    }//GEN-LAST:event_getContentButtonActionPerformed

    private void addDocumentListenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDocumentListenerActionPerformed
        // Add your handling code here:
        
        JOptionPane.showMessageDialog(htmlEditor, "Adding document listener - keep an eye on the Log console in the demo panel and type something in the editor!");
        
         class my_doc_listener implements DocumentListener
     {
     
         public void changedUpdate(DocumentEvent evt)
         {
             
         
             eventsLog.append(evt.toString()+"\n");
           
         }
         
         public void removeUpdate(DocumentEvent evt)
         {
             eventsLog.append(evt.toString()+"\n");
         }
         
         public void insertUpdate(DocumentEvent evt)
         {
                eventsLog.append(evt.toString()+"\n");             

         }
         
     }
        
        
        htmlEditor.addDocumentListener(new my_doc_listener() );
    }//GEN-LAST:event_addDocumentListenerActionPerformed

    private void changeIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeIconActionPerformed
        // Add your handling code here:
        
         JOptionPane.showMessageDialog(htmlEditor, "<html>You can change all icons you want & customize entire the Look&Feel of the editor easily!<br>This will replce the <b>\"Save file\"</b> icon with the <b>\"Sferyx logo\"</b> icon</html>");
         originalIcon=new ImageIcon(htmlEditor.getSharedIcon("file-save").getImage());
         htmlEditor.setSharedIcon("file-save",htmlEditor.getSharedIcon("sferyx-logo"));
         htmlEditor.updateUI();
        
    }//GEN-LAST:event_changeIconActionPerformed
ImageIcon originalIcon;
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // Add your handling code here:
        
      JOptionPane.showMessageDialog(htmlEditor, "<html>New button is added at the end of the shortcuts toolbar - <b>click on it to try </b></html>");   
        
     JButton myToolbarButton=htmlEditor.createMenuButton(htmlEditor.getEditingToolBar(), "Click me", "Click me", htmlEditor.getSharedIcon("sferyx-logo"));
     
     
     
     class my_listener implements ActionListener
     {
     
         public void actionPerformed(ActionEvent evt)
         {
             
             JOptionPane.showMessageDialog(htmlEditor, "Now we will insert the following string into the editor:\n<h1>This is a HTML content inserted from a custom button</h1>");
           
             htmlEditor.setContent("<h1>This is a HTML content inserted from a custom button</h1>");
         }
         
         
     }
     
     myToolbarButton.addActionListener(new my_listener());
     
    }//GEN-LAST:event_addButtonActionPerformed

    private void removeToolbarItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeToolbarItemsActionPerformed
        // Add your handling code here:
        htmlEditor.setRemovedToolbarItems("fontsList,insertImageButton,openFileButton");
    }//GEN-LAST:event_removeToolbarItemsActionPerformed

    private void restoreDefaultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreDefaultsActionPerformed
        // Add your handling code here:
        //Container parent=htmlEditor.getParent();
      
        if(originalIcon!=null) htmlEditor.setSharedIcon("file-save",originalIcon);
      
        jf.getContentPane().remove(htmlEditor);
      
        htmlEditor=new sferyx.administration.editors.HTMLEditor();
        jf.getContentPane().add("Center",htmlEditor);
        jf.getContentPane().doLayout();
        jf.getContentPane().validate();
         
        
    }//GEN-LAST:event_restoreDefaultsActionPerformed

    private void removeItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeItemsActionPerformed
        // Add your handling code here:
        
        htmlEditor.setRemovedMenus("menuWindow,menuHelp");
        
    }//GEN-LAST:event_removeItemsActionPerformed

    private void loadStyleSheetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadStyleSheetButtonActionPerformed
        // Add your handling code here:
      
       
       htmlEditor.setContent("This is a sample content to illustrate the dynamic styles usage. In this case we have used the following method:<br><span class=\"mystyle\">htmlEditor.loadExternalStyleSheet(ClassLoader.getSystemResource(\"demo-stylesheet.css\").toString());</span><br> but if we want we can load external styles also from URLs etc.");
       //htmlEditor.loadStyleSheetRules("body {background-color:#EAEAEA;color:blue; font-size:12px;font-family:helvetica;} .my_style{font-size:14px;color:black;font-weight:bold;}");
       htmlEditor.loadExternalStyleSheet(ClassLoader.getSystemResource("demo-stylesheet.css").toString());
       
       
    }//GEN-LAST:event_loadStyleSheetButtonActionPerformed

    private void translateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_translateButtonActionPerformed
        // Add your handling code here:
        htmlEditor.loadInterfaceLanguageFile("sample-german-translation.txt");
        
    }//GEN-LAST:event_translateButtonActionPerformed

    private void disablePopupMenuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_disablePopupMenuItemStateChanged
        // Add your handling code here:
         htmlEditor.setPopupMenuVisible(!((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_disablePopupMenuItemStateChanged

    private void hideStatusBarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_hideStatusBarItemStateChanged
        // Add your handling code here:
         htmlEditor.setStatusBarVisible(!((JCheckBox)evt.getSource()).isSelected());

    }//GEN-LAST:event_hideStatusBarItemStateChanged

    private void hidePreviewItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_hidePreviewItemStateChanged
        // Add your handling code here:
         htmlEditor.setPreviewVisible(!((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_hidePreviewItemStateChanged

    private void hideShortcutsToolbarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_hideShortcutsToolbarItemStateChanged
        // Add your handling code here:
        htmlEditor.setShortcutToolbarVisible(!((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_hideShortcutsToolbarItemStateChanged

    private void hideFormattingToolbarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_hideFormattingToolbarItemStateChanged
        // Add your handling code here:
        htmlEditor.setFormattingToolbarVisible(!((JCheckBox)evt.getSource()).isSelected());
        
    }//GEN-LAST:event_hideFormattingToolbarItemStateChanged

    private void hideMainMenuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_hideMainMenuItemStateChanged
        // Add your handling code here:
        htmlEditor.setMainMenuVisible(!((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_hideMainMenuItemStateChanged

    private void hideSourceEditorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_hideSourceEditorItemStateChanged
        // Add your handling code here:
        htmlEditor.setSourceEditorVisible(!((JCheckBox)evt.getSource()).isSelected());
    }//GEN-LAST:event_hideSourceEditorItemStateChanged
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new HTMLEditorCustomizationDemo().show();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton removeToolbarItems;
    private javax.swing.JButton getContentButton;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JCheckBox hidePreview;
    private javax.swing.JSeparator jSeparator111;
    private javax.swing.JButton addDocumentListener;
    private javax.swing.JScrollPane eventsLogScrollPane;
    private javax.swing.JButton loadStyleSheetButton;
    private javax.swing.JCheckBox disablePopupMenu;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton translateButton;
    private javax.swing.JCheckBox hideMainMenu;
    private javax.swing.JButton getContentButton1;
    private javax.swing.JButton changeIcon;
    private javax.swing.JLabel guiLabel;
    private javax.swing.JTextArea eventsLog;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JCheckBox hideFormattingToolbar;
    private javax.swing.JButton restoreDefaults;
    private javax.swing.JCheckBox hideShortcutsToolbar;
    private javax.swing.JButton addButton;
    private javax.swing.JButton removeItems;
    private javax.swing.JCheckBox hideStatusBar;
    private javax.swing.JCheckBox hideSourceEditor;
    private javax.swing.JLabel guiLabel1;
    // End of variables declaration//GEN-END:variables
    
}
