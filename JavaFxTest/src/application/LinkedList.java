package application;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import application.FileChooser;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LinkedList {
	@FXML
	private Label LLT;
	@FXML
	private TextField txtLL;
	
	private Node HEAD;
	private int length;
	
	
	
	public LinkedList(){
		HEAD = null;
		length = 0;
	}
	
	@SuppressWarnings("unused")
	public boolean isFull(){
	    Node new_node = new Node("DAta","DAta2","DAta3");
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
			FileChooser file = new FileChooser();
			FileInputStream fstream = new FileInputStream(file.openFile());
			StartTime = System.nanoTime();
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null){
				String words[] = strLine.split("\\t");
				this.Dictionary(words[0], words[1], words[2]);
			}
			in.close();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		EndTime = System.nanoTime();
		System.out.println("Loading dataset from file to linked list took: "+(EndTime - StartTime)+" nanoseconds");
	    LLT.setText(Long.toString(EndTime - StartTime)+" Nanoseconds");
	}
	
	public void Dictionary(String word, String partOfSpeech, String meaning){
		if(isFull()){
			System.out.println("Error - Cannot insert the item");
		}else{
			word = word.replaceAll("^\\W", "");
			Node newWordMeaning = new Node(word, partOfSpeech, meaning );
			Node temp = new Node();
			if(isEmpty()){
				this.HEAD = newWordMeaning;
			}
			else{
				temp = this.HEAD;
			    while ((temp.getNextWordMeaning() != null)) {
			           temp = temp.getNextWordMeaning();
			    }
			    temp.setNextWordMeaning(newWordMeaning);
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
	            	if(current.getNextWordMeaning() != null && current.getWord().compareToIgnoreCase(current.getNextWordMeaning().getWord()) >0){
	                    String word = current.getWord();
	                    String partOfSpeech = current.getPartOfSpeech();
	                    String meaning = current.getMeaning();
	                    current.setWord(current.getNextWordMeaning().getWord());
	                    current.setPartOfSpeech(current.getNextWordMeaning().getPartOfSpeech());
	                    current.setMeaning(current.getNextWordMeaning().getMeaning());
	                    current.getNextWordMeaning().setWord(word);
	                    current.getNextWordMeaning().setPartOfSpeech(partOfSpeech);
	                    current.getNextWordMeaning().setMeaning(meaning);
	                    swapDone = true;
	                }
	                current = current.getNextWordMeaning();
	            }
	            current = this.HEAD;
	        }
	    	EndTime = System.nanoTime();
	    }
		System.out.println("Bubble sorting the linked list took: "+(EndTime - StartTime)+" nanoseconds");
		LLT.setText(Long.toString(EndTime - StartTime)+" Nanoseconds");
	}
		
	public void addToDictionary(String word, String partOfSpeech, String meaning){
		long StartTime = 0;
		long EndTime = 0;
		
		if(isFull()){
			System.out.println("Error - Cannot insert the item");
		}
		else
		{
			StartTime = System.nanoTime();
			Node newWordMeaning = new Node(word, partOfSpeech, meaning);
			Node currPtr = new Node();
			Node temp = new Node();
			boolean insert = false;
			if(isEmpty()){
				this.HEAD = newWordMeaning;
			}
			else
			{
				if(this.HEAD.getWord().compareTo(word)>0){
					newWordMeaning.setNextWordMeaning(this.HEAD);
					this.HEAD = newWordMeaning;
				}
				else
				{
					temp = this.HEAD;
					currPtr = this.HEAD.getNextWordMeaning();
					while(currPtr != null){
						if(temp.getWord().compareTo(word)<0 && currPtr.getWord().compareTo(word)>0){
							temp.setNextWordMeaning(newWordMeaning);
							newWordMeaning.setNextWordMeaning(currPtr);
							insert = true;
							break;
						}
						else
						{
							temp = currPtr;
							currPtr = currPtr.getNextWordMeaning();
						}
					}
				}
				if(insert==false){
						temp.setNextWordMeaning(newWordMeaning);
				}
			}
			length++;
			EndTime = System.nanoTime();
			System.out.println("Adding the word '"+word+"' to the linked list took: "+(EndTime - StartTime)+" nanoseconds");
		}
	}
	
	public String lookUp(String searchValue){
		long StartTime = 0;
		long EndTime = 0;
		Node currentPtr = this.HEAD;
		StartTime = System.nanoTime();
		while(currentPtr != null){
			if(currentPtr.getWord().equals(searchValue)){
				int index = this.locate(searchValue);
				EndTime = System.nanoTime();
				System.out.println("Searching for the word '"+searchValue+"' in the linked list took: "+(EndTime - StartTime)+" nanoseconds");
				return "\nFound at index: " +Integer.toString(index)+"\n"+currentPtr.getWord()+"\t"+currentPtr.getPartOfSpeech()+"\t"+currentPtr.getMeaning();
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
				if(currentPtr.getWord().equals(element)){
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
		if(arr.size()!=0)
		{
			for(j=0;j<arr.size();j++)
			{
				System.out.println("'"+arr.get(j)+"' was not found in the dictionary, would you like to add it to the database of words?\n y/n");
				String respond = in.nextLine();
				if(respond.equalsIgnoreCase("y")){
					System.out.println("Enter part of speech: ");
					String partOfSpeech = in.nextLine();
					System.out.println("Enter Meaning: ");
					String meaning = in.nextLine();
					this.addToDictionary(arr.get(j), partOfSpeech, meaning);
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
			txtLL.setText(currNode.getWord()+" "+currNode.getPartOfSpeech()+" "+currNode.getMeaning()+"\n");
			currNode = currNode.getNextWordMeaning();
		}
		EndTime = System.nanoTime();
		System.out.println("Displaying all words, part of speech and defintion in the linked list took: "+(EndTime - StartTime)+" nanoseconds");
		LLT.setText(Long.toString(EndTime - StartTime)+" Nanoseconds");
	}

}

