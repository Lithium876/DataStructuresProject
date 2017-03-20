import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import gui.fileChooser;

public class LinkedList {
	
	private Node HEAD;
	private int length;
	
	public LinkedList(){
		HEAD = null;
		length = 0;
	}
	
	@SuppressWarnings("unused")
	public boolean isFull(){
	    Node new_node = new Node("DAta");
	    if(new_node != null){
	        return false;
	    }
	    return true;
	}
	
	public int lengthOfList(){
		return length;
	}
	
	public boolean isEmpty(){
		if (HEAD == null){
			return true;
		}
		return false;
	}
	public void loadAndSortDataset(){
		long StartTime =0;
		long EndTime =0;
		try{
			fileChooser file = new fileChooser();
			FileInputStream fstream = new FileInputStream(file.openFile());
			StartTime = System.nanoTime();
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null){
				this.Dictionary(strLine);
			}
			in.close();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		EndTime = System.nanoTime();
		System.out.println("Loading dataset from file to linked list took: "+(EndTime - StartTime)+" nanoseconds");
	}
	
	public void Dictionary(String wordMeaning){
		if(isFull()){
			System.out.println("Error - Cannot insert the item");
		}else{
			Node newWordMeaning = new Node(wordMeaning);
			Node temp = new Node();
			if(isEmpty()){
				this.HEAD = newWordMeaning;
			}
			else{
				temp = this.HEAD;
			    while ((temp.getNextWordMeaning() != null)) {
			           temp = temp.getNextWordMeaning();
			    }
			    temp.setNextWordMeaing(newWordMeaning);
			}
			length++;
		}
	}
	
	public void bubbleSort() {
		long StartTime = 0;
		long EndTime = 0;
		if (isEmpty() || this.HEAD.getNextWordMeaning() == null){
	        System.out.println("An empty list is already sorted."); 
		}else {
			StartTime = System.nanoTime();
	        Node current = this.HEAD;
	        boolean swapDone = true;
	        while (swapDone) {
	        	swapDone = false;
	            while (current != null) {
	            	if(current.getNextWordMeaning() != null && current.getWordMeaning().compareTo(current.getNextWordMeaning().getWordMeaning()) >0){
	                    String temp = current.getWordMeaning();
	                    current.setWordMeaning(current.getNextWordMeaning().getWordMeaning());
	                    current.getNextWordMeaning().setWordMeaning(temp);
	                    swapDone = true;
	                }
	                current = current.getNextWordMeaning();
	            }
	            current = this.HEAD;
	        }
	    	EndTime = System.nanoTime();
	    }
		System.out.println("Sorting the linked list took: "+(EndTime - StartTime)+" nanoseconds");
	}
	
	public void addToDictionary(String wordMeaning){
		long StartTime = 0;
		long EndTime = 0;
		//implements insertion sort
		if(isFull()){
			System.out.println("Error - Cannot insert the item");
		}else{
			StartTime = System.nanoTime();
			Node newWordMeaning = new Node(wordMeaning);
			Node currPtr = new Node();
			Node temp = new Node();
			boolean insert = false;
			if(isEmpty()){
				this.HEAD = newWordMeaning;
			}
			else{
				if(this.HEAD.getWordMeaning().compareTo(wordMeaning)>0){
					newWordMeaning.setNextWordMeaing(this.HEAD);
					this.HEAD = newWordMeaning;
				}else{
					temp = this.HEAD;
					currPtr = this.HEAD.getNextWordMeaning();
					while(currPtr != null){
						if(temp.getWordMeaning().compareTo(wordMeaning)<0 && currPtr.getWordMeaning().compareTo(wordMeaning)>0){
							temp.setNextWordMeaing(newWordMeaning);
							newWordMeaning.setNextWordMeaing(currPtr);
							insert = true;
							break;
						}else{
							temp = currPtr;
							currPtr = currPtr.getNextWordMeaning();
						}
					}
				}
				if(insert==false){
						temp.setNextWordMeaing(newWordMeaning);
				}
			}
			length++;
			EndTime = System.nanoTime();
			System.out.println("Adding the word '"+wordMeaning.split("\\s")[0]+"' to the linked list took: "+(EndTime - StartTime)+" nanoseconds");
		}
	}
	
	public void addWord(String word, String partOfSpeech, String definition){
		if(this.locate(word) != -1){
			System.out.println("Sorry, the word "+word+" already exists!");
		}else{
			String dictionaryEntry = word+"\t"+partOfSpeech+"\t"+definition;
			this.addToDictionary(dictionaryEntry);
		}
	}
	
	public String lookUp(String searchValue){
		long StartTime = 0;
		long EndTime = 0;
		Node currentPtr = this.HEAD;
		StartTime = System.nanoTime();
		while(currentPtr != null){
			if(currentPtr.getWordMeaning().split("\\s")[0].equals(searchValue)){
				int index = this.locate(searchValue);
				EndTime = System.nanoTime();
				System.out.println("Searching for the word '"+searchValue+"' in the linked list took: "+(EndTime - StartTime)+" nanoseconds");
				return "\nFound at index: " +Integer.toString(index)+"\n"+currentPtr.getWordMeaning();
			}
			currentPtr = currentPtr.getNextWordMeaning();
		}
			EndTime = System.nanoTime();
			System.out.println("Searching for the word '"+searchValue+"' in the linked list took: "+(EndTime - StartTime)+" nanoseconds");
			return "Not Found";
	}
	
	
	public int locate(String element){
		int index = 0;
		if(isEmpty()){
			System.out.println("The list is empty");
		}else{
			Node currentPtr = this.HEAD;
			while(currentPtr != null){
				if(currentPtr.getWordMeaning().split("\\s")[0].equals(element)){
					return index;
				}
				index++;
				currentPtr = currentPtr.getNextWordMeaning();
			}
		}
		return -1;
		
	}
	public void validateSentence(String sentence){
		long StartTime = 0;
		long EndTime = 0;
		ArrayList<String> arr = new ArrayList<String>();
		String word[] = sentence.split("[\\W]");
		Scanner in = new Scanner(System.in);
		int i;
		int found;
		StartTime = System.nanoTime();
		for(i=0;i<word.length;i++){
			String capitalized = Character.toUpperCase(word[i].charAt(0)) + word[i].substring(1);
			found = this.locate(capitalized);
			if(found==-1){
				arr.add(capitalized);
			}
		}
		EndTime = System.nanoTime();
		System.out.println("Validaing the sentence took (Linked List): "+(EndTime - StartTime)+" nanoseconds");
		int j;
		if(arr.size()!=0){
			for(j=0;j<arr.size();j++){
				System.out.println("'"+arr.get(j)+"' was not found in the dictionary, would you like to add it to the database of words?\n y/n");
				String respond = in.nextLine();
				if(respond.equals("y")){
					System.out.println("Enter part of speech: ");
					String partOfSpeech = in.nextLine();
					System.out.println("Enter Meaning: ");
					String meaning = in.nextLine();
					this.addWord(arr.get(j), partOfSpeech, meaning);
				}
			}
		}
		in.close();
	}
	
	public void displayWordsInDictionary(){
		Node currNode = HEAD;
		while(currNode != null){
			currNode.displayWord();
			currNode = currNode.getNextWordMeaning();
		}
	}
	
	public void displayDictionary(){
		long StartTime = 0;
		long EndTime = 0;
		Node currNode = HEAD;
		StartTime = System.nanoTime();
		while(currNode != null){
			currNode.display();
			currNode = currNode.getNextWordMeaning();
		}
		EndTime = System.nanoTime();
		System.out.println("Displaying all words, part of speech and defintion in the linked list took: "+(EndTime - StartTime)+" nanoseconds");
	}
}
