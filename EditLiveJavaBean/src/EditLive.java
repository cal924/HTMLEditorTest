import java.awt.BorderLayout;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.ephox.editlive.ELJBean;


/**
 * This sample shows how to use the JWebEngine as a text editor. This sample uses the {@link BaseEditor}
 * class to use all actions and the optional spell checker component. To activate the spell checker, please
 * add the JOrtho libraries and dictionaries to the class path when running this sample. 
 * @author i-net software GmbH 
 */
public class EditLive extends JApplet{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3096133702667489730L;

	public static void main( String[] args ) {
        JFrame frame = new JFrame();
        frame.setLayout( new BorderLayout() );
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        frame.setTitle( "Edit!Live editor sample" );
        ELJBean editLive = new ELJBean();
        
        String htmlContent = new String("<p>This is an example html page</p>");
          
        editLive.setBody(htmlContent);
        editLive.init();
        // NOTE: DO NOT PACK THE BaseEditor!
        // The BaseEditor has a very small minimum size since it's toolbar is scalable. So, if you pack
        // this component it will be minimized to the width of the smallest button.
        frame.add( editLive );
        frame.setSize( 800, 600 );
        // display the component
        frame.setVisible( true );
    }
}
