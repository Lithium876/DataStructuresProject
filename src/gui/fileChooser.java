package gui;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import classes.LinkedList;

public class fileChooser 
{
	
	private static String filePath;
	
	public static String getFilePath()
	{
		return filePath;
	}
	
	public void setFilePath(String filePath)
	{
		fileChooser.filePath = filePath;
	}
	
	public String openFile(String DataStructure)
	{
		JButton open = new JButton();
		JFileChooser fc = new JFileChooser();
		//REMEMBER TO UNCOMMENT THIS PART!!!!
		//fc.setCurrentDirectory(new java.io.File("user.home"));
		//REMEMBER TO TAKE THIS PART OUT!
		fc.setCurrentDirectory(new java.io.File("C:\\Users\\Admin\\Downloads"));
		fc.setDialogTitle("Import Dictionary Data for "+DataStructure);
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if(fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION)
		{
			
		}
		this.setFilePath(fc.getSelectedFile().getAbsolutePath());
		return fc.getSelectedFile().getAbsolutePath();
	}
}
