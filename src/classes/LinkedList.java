/*Lomar Lilly 1401375
 * Darryl Brown 1503803
 */

package classes;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import gui.fileChooser;
import gui.mainWindow;

public class LinkedList 
{
	//Class Variables
	private Node HEAD;
	private int length;
	private String filePath;
	
	//Default Constructor
	public LinkedList()
	{
		HEAD = null;
		length = 0;
	}
	
	//Check if list is full
	@SuppressWarnings("unused")
	public boolean isFull()
	{
	    Node new_node = new Node();
	    if(new_node != null)
	    {
	        return false;
	    }
	    return true;
	}
	
	//get the length of list
	public int lengthOfList()
	{
		return length;
	}
	
	//check if the list is empty
	public boolean isEmpty()
	{
		if (HEAD == null)
		{
			return true;
		}
		return false;
	}
	
	//Get dataset file path
	public String getFileName(){
		return filePath;
	}
	
	//set dataset file path
	public void setFileName(String fileName){
		this.filePath = fileName;
	}
	
	//Loads and parse the dataset from file
	public String loadDataset()
	{
		long StartTime =0;
		long EndTime =0;
		try
		{
			//loads file chooser
			fileChooser file = new fileChooser();
			FileInputStream fstream = new FileInputStream(file.openFile("Linked List and Binary Search Tree"));
			StartTime = System.nanoTime();
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			//reading from the file line by line
			while ((strLine = br.readLine()) != null)
			{
				String words[] = strLine.split("\\t");
				this.Dictionary(words[0], words[1], words[2]);
			}
			in.close();
		}
		catch(Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}
		EndTime = System.nanoTime();
		return "Loading dataset from file to linked list took: "+(EndTime - StartTime)+" nanoseconds";
	}
	
	//The linked List with the alias Dictionary
	public void Dictionary(String word, String partOfSpeech, String meaning)
	{
		//check if the linked list is full
		if(isFull())
		{
			System.out.println("Error - Cannot insert the item");
		}
		else
		//If list not full
		{
			word = word.replaceAll("\\W", ""); //Remove all symbols on the word
			Node newWordMeaning = new Node(word, partOfSpeech, meaning ); 
			Node temp = new Node();
			if(isEmpty())
			{
				this.HEAD = newWordMeaning;
			}
			else
			{
				temp = this.HEAD;
			    while ((temp.getNextWordMeaning() != null)) 
			    {
			           temp = temp.getNextWordMeaning();
			    }
			    temp.setNextWordMeaning(newWordMeaning);
			}
			length++;
		}
	}
	
	//Sorting the linked list with bubble sort
	public String bubbleSort() 
	{
		long StartTime = 0;
		long EndTime = 0;
		if (isEmpty() || this.HEAD.getNextWordMeaning() == null)
		{
	        System.out.println("An empty list is already sorted."); 
		}
		else 
		{
			StartTime = System.nanoTime();
	        Node current = this.HEAD;
	        boolean swapDone = true;
	        while (swapDone) 
	        {
	        	swapDone = false;
	            while (current != null) 
	            {
	            	if(current.getNextWordMeaning() != null && current.getWord().compareToIgnoreCase(current.getNextWordMeaning().getWord()) > 0)
	            	{
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
		this.displayDictionary();
		return "Sorting the linked list took: "+(EndTime - StartTime)+" nanoseconds";
	}
	
	//Adding data to the linked list
	public String addToDictionary(String word, String partOfSpeech, String meaning)
	{
		long StartTime = 0;
		long EndTime = 0;
		
	    word = Character.toUpperCase(word.charAt(0)) + word.substring(1);//Capitalizes the first letter in the word
		
	    //check if the list is full
		if(isFull())
		{
			System.out.println("Error - Cannot insert the item");
		}
		else
			//if list is not full
		{
		
			StartTime = System.nanoTime();
			Node newWordMeaning = new Node(word, partOfSpeech, meaning);
			Node currPtr = new Node();
			Node temp = new Node();
			boolean insert = false;
			if(isEmpty())
			{
				this.HEAD = newWordMeaning;
			}
			if(this.locate(newWordMeaning.getWord()) != -1){
				System.out.println(newWordMeaning.getMeaning());
				return "Sorry! the word '"+newWordMeaning.getWord()+"' already exists";
				
			}
			else
			{
				try{
					BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getFilePath(),true));
					writer.write(newWordMeaning.getWord()+"\t"+newWordMeaning.getPartOfSpeech()+"\t"+newWordMeaning.getMeaning());
					writer.close();
				}catch(Exception e){
					System.out.println(e);
				}
				if(this.HEAD.getWord().compareToIgnoreCase(word)>0)
				{
					newWordMeaning.setNextWordMeaning(this.HEAD);
					this.HEAD = newWordMeaning;
				}
				else
				{
					temp = this.HEAD;
					currPtr = this.HEAD.getNextWordMeaning();
					while(currPtr != null){
						if(temp.getWord().compareToIgnoreCase(word)<0 && currPtr.getWord().compareToIgnoreCase(word)>0)
						{
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
				if(insert==false)
				{
					temp.setNextWordMeaning(newWordMeaning);
				}
				length++;
				EndTime = System.nanoTime();
			}
		}
		return "Adding the word '"+word+"' to the linked list took: "+(EndTime - StartTime)+" nanoseconds";
	}
	
	//Search the list for a search value given by the user
	public String lookUp(String searchValue)
	{
		long StartTime = 0;
		long EndTime = 0;
		Node currentPtr = this.HEAD;
		boolean found = false;
		StartTime = System.nanoTime();
		int index = this.locate(searchValue)-1;
		
		while(currentPtr != null)
		{
			if(currentPtr.getWord().compareToIgnoreCase(searchValue)==0) //if the search value matches the word at the node's current pointer 
			{
				found = true;
				index++;
				System.out.println("Found at index: " +Integer.toString(index)+"\n"+currentPtr.getWord()+"\t"+currentPtr.getPartOfSpeech()+"\t"+currentPtr.getMeaning());
			}
			currentPtr = currentPtr.getNextWordMeaning();
		}
		EndTime = System.nanoTime();
		//if search value not found
		if(!found)
		{
			return "Not Found";
		}
		return "Searching for the word '"+searchValue+"' in the linked list took: "+(EndTime - StartTime)+" nanoseconds";
	}
	
	//finds a search value and return's it's index
	public int locate(String element)
	{
		int index = 0;
		//check the linked list is empty
		if(isEmpty())
		{
			System.out.println("The list is empty");
		}
		else
		{
			Node currentPtr = this.HEAD;
			while(currentPtr != null)
			{
				if(currentPtr.getWord().compareToIgnoreCase(element)==0) //if the search value matches the word at the node's current pointer
				{
					return index;
				}
				index++;
				currentPtr = currentPtr.getNextWordMeaning();
			}
		}
		return -1; //returned if search element not found
	}
	
	//Takes a sentences from the user and checks if each word in the sentence is in the dataset loaded in the program
	public String validateSentence(String sentence)
	{
		long StartTime = 0;
		long EndTime = 0;
		ArrayList<String> arr = new ArrayList<String>(); //A string array list that will store the words found in the sentence that are not in the dataset loaded
		String word[] = sentence.split("[\\W]"); //splitting the sentence on every whitespace and punctuation
		int i;
		int found;
		long extraStartTime = 0;
		long extraEndTime = 0;
		long extraTime;
		StartTime = System.nanoTime();
		for(i=0;i<word.length;i++)
		{
			found = this.locate(word[i]);
			if(found==-1) //if word was found
			{
				arr.add(word[i]);
			}
			else
			{	
				extraStartTime = System.nanoTime();
				JOptionPane.showMessageDialog(null, "'"+word[i]+"'Already exists in dictionary!","Word Exists", JOptionPane.INFORMATION_MESSAGE);
				extraEndTime = System.nanoTime();
			}
		}
		EndTime = System.nanoTime();
		extraTime = extraEndTime - extraStartTime;
		EndTime = EndTime - extraTime;
		int j;
		//Traverses the array that contains the words not found in the dataset and prompts the user
		//on wheather to add or discard the word
		if(arr.size()!=0)
		{
			for(j=0;j<arr.size();j++)
			{
				if (JOptionPane.showConfirmDialog(null, "'"+arr.get(j)+"' was not found in the dictionary.\n Would you like to add it to the database of words?", "Word not found in Linked List!",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
				{

					String partOfSpeech = JOptionPane.showInputDialog(null, "Enter part of speech", "Adding the word: '"+arr.get(j)+"'to Linked List", JOptionPane.QUESTION_MESSAGE);
					String meaning = JOptionPane.showInputDialog(null, "Enter Meaning", "Adding the word: '"+arr.get(j)+"'", JOptionPane.QUESTION_MESSAGE);
					
					String str = this.addToDictionary(arr.get(j), partOfSpeech, meaning);
					JOptionPane.showMessageDialog(null, str, "Added "+arr.get(j)+" to Linked List", JOptionPane.INFORMATION_MESSAGE);
				} 
				else 
				{
					continue;
				}
			}
		}
		return "Validaing the sentence took (Linked List): "+(EndTime - StartTime)+" nanoseconds";		
	}
	
	//Displays the dataset
	public String displayDictionary()
	{
		long StartTime = 0;
		long EndTime = 0;
		Node currNode = HEAD;
		
		StartTime = System.nanoTime();
		try{
			while(currNode != null)
			{
				currNode.display();
				currNode = currNode.getNextWordMeaning();
			}
		}catch(Exception e){
			System.out.println(e);
		}
		EndTime = System.nanoTime();
		return "Displaying words in the linked list took: "+(EndTime - StartTime)+" nanoseconds";
	}
}
