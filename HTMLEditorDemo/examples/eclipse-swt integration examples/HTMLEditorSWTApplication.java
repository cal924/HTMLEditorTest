/*
 * Created on 20-mag-2006
 *
 *
 */
//package swtexample;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Panel;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.*;
import sferyx.administration.editors.*;

/**
 * @author Vassil Boyadjiev
 *
 * Sferyx JSyndrome HTMLEditor running inside an Eclipse SWT Application
 */
public class HTMLEditorSWTApplication
{

	  Label label;
	  HTMLEditorSWTBean htmlEditorSWTBean;

	  public HTMLEditorSWTApplication()
	  {
		   Display display = new Display();
		   Shell shell = new Shell(display);
		  shell.setLayout(new FillLayout(SWT.VERTICAL));
		  shell.setText("HTMLEditor Demo");

		  label = new Label(shell, SWT.CENTER);
		  label.setText("This is a SWT Label");

		  Button ok = new Button (shell, SWT.PUSH);
		  ok.setText ("Click to set label text from HTMLEditor");
		  ok.addSelectionListener(new SelectionAdapter()
		  {
				public void widgetSelected(SelectionEvent e)
				{
					label.setText(htmlEditorSWTBean.getContent());
				}
		  });

		    Composite composite = new Composite(shell, SWT.NO_BACKGROUND | SWT.EMBEDDED);

		    Frame frame = SWT_AWT.new_Frame(composite);
		    htmlEditorSWTBean=new HTMLEditorSWTBean();
		    frame.add(htmlEditorSWTBean);

		    shell.open();
		    while (!shell.isDisposed())
		    {
		      if (!display.readAndDispatch()) display.sleep();
		    }
		    display.dispose();
		  }




	public static void main(String[] args)
	{

		new HTMLEditorSWTApplication();
     }

}