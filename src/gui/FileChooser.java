/*Lomar Lilly 1401375
 * Darryl Brown 1503803
 */
package gui;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import classes.LinkedList;

public class FileChooser {
	
	private static String filePath;
	
	public static String getFilePath(){
		return filePath;
	}
	
	public void setFilePath(String filePath){
		FileChooser.filePath = filePath;
	}
	
	public String openFile(String DataStructure){
		JButton open = new JButton();
		JFileChooser fc = new JFileChooser();
		try
		{
			//REMEMBER TO UNCOMMENT THIS PART!!!!
			//fc.setCurrentDirectory(new java.io.File("user.home"));
			//REMEMBER TO TAKE THIS PART OUT!
			fc.setCurrentDirectory(new java.io.File("C:\\Users\\Admin\\Downloads"));
			fc.setDialogTitle("Import Dictionary Data for "+DataStructure);
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			if(fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION){
				System.out.println("file loaded");
			}
			this.setFilePath(fc.getSelectedFile().getAbsolutePath());
		}
		catch(Exception err)
		{
			System.out.println(err);
		}
		return fc.getSelectedFile().getAbsolutePath();
	}
}