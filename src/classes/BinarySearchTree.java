/*Lomar Lilly 1401375
 * Darryl Brown 1503803
 */

package classes;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import gui.FileChooser;

public class BinarySearchTree 
{
	//Class Variables
	private Node ROOT;
	private int length;
	private int called=0;
	
	//Default Constructor
	public BinarySearchTree()
	{
		this.ROOT = null;
		length = 0;
	}
	
	//Check if tree is full
	@SuppressWarnings("unused")
	public boolean isFull()
	{
		BinarySearchTree BST = new BinarySearchTree();
	    if(BST != null)
	    {
	        return false;
	    }
	    return true;
	}
	
	//Get the length of the tree
	public int lengthOfTree()
	{
		return length;
	}
	
	//get the root of the tree
	public Node getRoot()
	{
		return ROOT;
	}
	
	//Check if the tree is empty
	public boolean isEmpty()
	{
		if (ROOT == null)
		{
			return true;
		}
		return false;
	}
	
	//Loads and parse the dataset from file
	public String loadDataset()
	{
		long StartTime =0;
		long EndTime =0;
		try
		{
			//loads file chooser
			FileInputStream fstream = new FileInputStream(FileChooser.getFilePath());
			StartTime = System.nanoTime();
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			//reading from the file line by line
			while ((strLine = br.readLine()) != null)
			{
				String words[] = strLine.split("\\t");
				this.insert(words[0], words[1], words[2]);
			}
			in.close();
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}
		EndTime = System.nanoTime();
		return "Loading dataset from file to Binary Search Tree took: "+(EndTime - StartTime)+" nanoseconds";
	}
	
	//Traverses the tree in order left to right
	public void inOrderTraversal(Node R)
	{
		try
		{
			if(R != null)
			{
				inOrderTraversal(R.getLeftSubTree());
				R.display();
				inOrderTraversal(R.getRightSubTree());
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	//Sorts the tree
	public void sort(Node R)
	{
		if(R != null)
		{
			try
			{
				sort(R.getLeftSubTree());
				R.display();
				sort(R.getRightSubTree());
			}
			catch(Exception err)
			{
				System.out.println(err);
			}
		}	
	}
	
	//Inserting into the Binary Tree
	public long insert(String word, String partOfSpeech, String meaning)
	{
		long StartTime =0;
		long EndTime =0;
		StartTime = System.nanoTime();
		word = word.replaceAll("\\W", "");//Remove all symbols on the word
		Node N = new Node(word, partOfSpeech, meaning);
		Node T;
		
		if(N != null)
		{
			if(isEmpty())//Checks if the tree is empty
			{
				ROOT = N;
			}
			else
			{
				T = ROOT;
				while(true)
				{
					if(N.getWord().compareToIgnoreCase(T.getWord()) < 0)
					{
						if(T.getLeftSubTree()==null)
						{
							T.setLeftSubTree(N);
							break;
						}
						else
						{
							T = T.getLeftSubTree();
						}
					}
					else
					{
						if(T.getRightSubTree() == null)
						{
							T.setRightSubTree(N);
							break;
						}
						else
						{
							T = T.getRightSubTree();
						}
					}
				}
			}
		}
		length++;
		EndTime = System.nanoTime();
		return (EndTime - StartTime);	
	}
	
	//adding data to the binary tree
	public String addWord(String word, String partOfSpeech, String meaning)
	{
		word = Character.toUpperCase(word.charAt(0)) + word.substring(1); //Capitalizes the first letter in the word
		int found = locate(word);
		if(found==-1)//if word not found in tree
		{
			long time = insert(word, partOfSpeech, meaning);
			return "Adding the word '"+word+"' to the Tree took: "+time+" nanoseconds";	
		}
		else
		{
			return "Sorry! the word '"+word+"' already exists";
		}
	}
	
	//Search the list for a search value given by the user
	public String lookUp(String searchValue)
	{
		long StartTime =0;
		long EndTime =0;
		boolean found = false;
		int leftindex = 0;
		int rightindex = 0;
		int index = 0;
		String str = "(Root of Tree)";
		Node currentPtr = this.ROOT; 
		StartTime = System.nanoTime();
		while(currentPtr != null)
		{
			if(currentPtr.getWord().compareToIgnoreCase(searchValue)==0)
			{
				found = true;
				System.out.println();
				EndTime = System.nanoTime();
				if(leftindex > rightindex)
				{
					index += leftindex;
					str = "(Left Sub Tree)";
				}
				else if(leftindex < rightindex)
				{
					index += rightindex;
					str = "(Right Sub Tree)";
				}
				System.out.println("Found at index: " +Integer.toString(index)+" "+str+"\n"+currentPtr.getWord()+"\t"+currentPtr.getPartOfSpeech()+"\t"+currentPtr.getMeaning());
			}
			if(currentPtr.getWord().compareToIgnoreCase(searchValue) > 0)
			{
				leftindex++;
				currentPtr = currentPtr.getLeftSubTree();
			}
			else
			{
				rightindex++;
				currentPtr = currentPtr.getRightSubTree();
			}
		}
		//if search value not found
		if(!found)
		{
			return "Not Found!";
		}
		return "Searching for the word '"+searchValue+"' in the tree took: "+(EndTime - StartTime)+" nanoseconds\n";
	}
	
	//finds a search value and return's it's index
	public int locate(String searchWord)
	{
		Node currentPtr;
		int index = 0;
		currentPtr = this.ROOT;
		while(currentPtr!=null)
		{
			if(currentPtr.getWord().equals(searchWord))
			{ 
				index++;
				return index;
			}
			else if(currentPtr.getWord().compareToIgnoreCase(searchWord) > 0)
			{
				index++;
				currentPtr = currentPtr.getLeftSubTree();
			}
			else if (currentPtr.getWord().compareToIgnoreCase(searchWord) < 0)
			{
				index++;
				currentPtr = currentPtr.getRightSubTree();
			}
		}
		return -1; //returned if search value not found
	}
	
	//Takes a sentences from the user and checks if each word in the sentence is in the dataset loaded in the program
	public String validateSentence(String sentence)
	{
		long StartTime = 0;
		long EndTime = 0;
		ArrayList<String> arr = new ArrayList<String>();//A string array list that will store the words found in the sentence that are not in the dataset loaded
		String word[] = sentence.split("[\\W]");//splitting the sentence on every whitespace and punctuation
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
				if (JOptionPane.showConfirmDialog(null, "'"+arr.get(j)+"' was not found in the dictionary.\n Would you like to add it to the database of words?", "Word not found in Binary Tree!",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
				{

					String partOfSpeech = JOptionPane.showInputDialog(null, "Enter part of speech", "Adding the word: '"+arr.get(j)+"'", JOptionPane.QUESTION_MESSAGE);
					String meaning = JOptionPane.showInputDialog(null, "Enter Meaning", "Adding the word: '"+arr.get(j)+"'to Binary Tree", JOptionPane.QUESTION_MESSAGE);
					String str = this.addWord(arr.get(j), partOfSpeech, meaning);
					JOptionPane.showMessageDialog(null, str, "Added "+arr.get(j)+" to Binary Tree", JOptionPane.INFORMATION_MESSAGE);
				} 
				else 
				{
				    continue;
				}
			}
		}
		return "Validaing the sentence took (Binary Tree): "+(EndTime - StartTime)+" nanoseconds";	
	}
	
	//Displays the dataset
	 public String display()
	 {
		long StartTime =0;
		long EndTime =0;
		StartTime = System.nanoTime();
		this.inOrderTraversal(this.ROOT);
		  EndTime = System.nanoTime();
		  return "Displaying words in the Binary Search Tree took: "+(EndTime - StartTime)+" nanoseconds\n";
	}
}