import java.io.*;

public class Driver {

	public static void main(String[] args) {
		LinkedList lList = new LinkedList();
	
		  try{
			  // Open the file that is the first 
			  // command line parameter
			  FileInputStream fstream = new FileInputStream("C:\\Users\\Admin\\Downloads\\wb1913_samp260.txt");
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  //Read File Line By Line
			  while ((strLine = br.readLine()) != null){
			  // Print the content on the console
				 lList.insertWordMeaning(strLine);
			  }
			  //Close the input stream
			  in.close();
			    }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }

		
		lList.displayList();
		System.out.print("\nLength of the list: "+lList.lengthOfList() );

	}
}
