

public class Driver {

	public static void main(String[] args) {
		LinkedList lList = new LinkedList();
		
		lList.loadAndSortDataset();
		
		lList.addWord("Abbreviation", "n.", "A good ute");
		
		System.out.print("\n\nLength of the list: "+lList.lengthOfList()+"\n\n");
		
		//lList.displayDictionary();
		
		System.out.println(lList.lookUp("Zxander")); 
	}
}