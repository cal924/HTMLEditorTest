/*
 * MyCustomEmptyTag.java
 *
 * Created on 24 marzo 2005, 13.43
 */
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import sferyx.administration.editors.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
/**
 *
 * @author  Vassil Boyadjiev
 */
  public class MyCustomEmptyTag extends sferyx.administration.xmlcomponents.GenericEmptyXMLTagComponent {
    
     
   public   MyCustomEmptyTag()
   {
   }
    
    public Component getComponent()
    {
   
         Element elem=getElement();
         JButton component=new JButton("This is my custom tag: "+elem.getAttributes().getAttribute(StyleConstants.NameAttribute).toString());
         component.setUI(new javax.swing.plaf.basic.BasicButtonUI());
         component.setOpaque(true);
         component.setBackground(Color.yellow);
         component.setBorderPainted(false);
         component.setForeground(Color.blue);
         
         // We can add listeners her and whatever we want. We can add easily icons etc.
         component.setEnabled(true);
         component.addActionListener(new MyCustomEmptyTag.MyActionListener());
         return component;
    }
    
     
    
    
    
    class MyActionListener implements ActionListener
    {
        
        public void actionPerformed(java.awt.event.ActionEvent actionEvent) 
        {
            
            JOptionPane.showMessageDialog(null,"You clicked on the custom tag!");
            
        }        
     
        
        
    }
    
    
}