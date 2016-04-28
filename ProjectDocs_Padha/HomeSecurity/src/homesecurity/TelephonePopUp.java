package homesecurity;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TelephonePopUp{
	
	public TelephonePopUp(){
		JFrame frame = new JFrame();
		Object response = JOptionPane.showInputDialog(frame, "Enter response code: ");
		
		System.out.println(response);
	}

}
