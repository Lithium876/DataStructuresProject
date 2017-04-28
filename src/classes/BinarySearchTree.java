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

import gui.fileChooser;

public class BinarySearchTree 
{
	private Node ROOT;
	private int length;
	private int called=0;
	
	public BinarySearchTree()
	{
		this.ROOT = null;
		length = 0;
	}
	
	public int lengthOfTree()
	{
		return length;
	}
	
	public Node getRoot()
	{
		return ROOT;
	}
	
	public boolean isEmpty()
	{
		if (ROOT == null)
		{
			return true;
		}
		return false;
	}
	
	public String loadDataset()
	{
		long StartTime =0;
		long EndTime =0;
		try
		{
			FileInputStream fstream = new FileInputStream(fileChooser.getFilePath());
			StartTime = System.nanoTime();
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
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
	
	public void sort(Node R)
	{
		if(R != null)
		{
			sort(R.getLeftSubTree());
			sort(R.getRightSubTree());
		}	
	}
	
	public long insert(String word, String partOfSpeech, String meaning)
	{
		long StartTime =0;
		long EndTime =0;
		StartTime = System.nanoTime();
		word = word.replaceAll("^\\W", "");
		Node N = new Node(word, partOfSpeech, meaning);
		Node T;
		
		if(N != null)
		{
			if(isEmpty())
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
	
	public String addWord(String word, String partOfSpeech, String meaning)
	{
		word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
		int found = locate(word);
		if(found==-1)
		{
			long time = insert(word, partOfSpeech, meaning);
			return "Adding the word '"+word+"' to the Tree took: "+time+" nanoseconds";	
		}
		else
		{
			return "Sorry! the word '"+word+"' already exists";
		}
	}
	
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
		
		if(!found)
		{
			return "Not Found!";
		}
		return "Searching for the word '"+searchValue+"' in the tree took: "+(EndTime - StartTime)+" nanoseconds\n";
	}
	
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
		return -1;
	}
	
	public void validateSentence(String sentence)
	{
		long StartTime = 0;
		long EndTime = 0;
		ArrayList<String> arr = new ArrayList<String>();
		String word[] = sentence.split("[\\W]");
		Scanner in = new Scanner(System.in);
		int i;
		int found;
		StartTime = System.nanoTime();
		for(i=0;i<word.length;i++)
		{
			found = this.locate(word[i]);
			if(found==-1)
			{
				arr.add(word[i]);
			}
		}
		EndTime = System.nanoTime();
		System.out.println("Validaing the sentence took (Tree): "+(EndTime - StartTime)+" nanoseconds");
		int j;
		if(arr.size()!=0)
		{
			for(j=0;j<arr.size();j++)
			{
				System.out.println("'"+arr.get(j)+"' was not found in the dictionary, would you like to add it to the database of words?\n y/n");
				String respond = in.nextLine();
				if(respond.equals("y"))
				{
					System.out.println("Enter part of speech: ");
					String partOfSpeech = in.nextLine();
					System.out.println("Enter Meaning: ");
					String meaning = in.nextLine();
					this.addWord(arr.get(j), partOfSpeech, meaning);
				}
				else
				{
					System.out.println("'"+arr.get(j)+"' was ignored..");
				}
			}
		}
	}
	
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
