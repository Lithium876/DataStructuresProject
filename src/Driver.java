import java.io.*;

import gui.fileChooser;

public class Driver {

	public static void main(String[] args) {
		LinkedList lList = new LinkedList();
		fileChooser file = new fileChooser();
		long StartTime =0;
		
		try{
			FileInputStream fstream = new FileInputStream(file.openFile());
			StartTime = System.currentTimeMillis();
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null){
				lList.addToDictionary(strLine);
			}
			in.close();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		long EndTime = System.currentTimeMillis();
		  
		System.out.println("Linked List Inserstion Sort Took: "+(EndTime - StartTime)+" milliseconds\n");
		System.out.println(lList.lookUp("Zooid")); 

		lList.displayDictionary();
		System.out.print("\nLength of the list: "+lList.lengthOfList() );
	}
}