package gui;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class fileChooser {
	
	public String openFile(){
		JButton open = new JButton();
		JFileChooser fc = new JFileChooser();
		//REMEMBER TO UNCOMMENT THIS PART!!!!
		//fc.setCurrentDirectory(new java.io.File("user.home"));
		//REMEMBER TO TAKE THIS PART OUT!
		fc.setCurrentDirectory(new java.io.File("C:\\Users\\Admin\\Downloads"));
		fc.setDialogTitle("Import Dictionary Data");
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if(fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION){
			
		}
		return fc.getSelectedFile().getAbsolutePath();
	}
}