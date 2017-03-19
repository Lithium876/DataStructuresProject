import java.io.*;

public class Driver {

	public static void main(String[] args) {
		LinkedList lList = new LinkedList();
		 
		long StartTime = System.currentTimeMillis();
		try{
			FileInputStream fstream = new FileInputStream("C:\\Users\\Admin\\Downloads\\wb1913_samp260.txt");
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
		System.out.println(lList.lookUp("Abbreviation")); 

		//lList.display();
		System.out.print("\nLength of the list: "+lList.lengthOfList() );
	}
}