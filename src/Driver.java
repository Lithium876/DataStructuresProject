

public class Driver {

	public static void main(String[] args) {
		LinkedList lList = new LinkedList();
		
		lList.loadAndSortDataset();
		
		lList.bubbleSort();
		lList.addWord("Paul", "n.", "A good ute");
		System.out.println(lList.lookUp("Occasional")); 
		System.out.print("\nLength of the list: "+lList.lengthOfList()+"\n");
		lList.validateSentence("Paul is a good yute");
		System.out.println(lList.lookUp("Good")); 
		//lList.displayDictionary();
	}
}