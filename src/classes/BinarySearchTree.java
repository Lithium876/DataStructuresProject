package classes;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import gui.fileChooser;

public class BinarySearchTree {
	
	private Node ROOT;
	private int length;
	
	public BinarySearchTree(){
		this.ROOT = null;
		length = 0;
	}
	
	public int lengthOfTree(){
		return length;
	}
	
	public Node getRoot(){
		return ROOT;
	}
	
	public boolean isEmpty(){
		if (ROOT == null){
			return true;
		}
		return false;
	}
	
	public void loadDataset(){
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
				String words[] = strLine.split("\\t");
				this.InsertNode(words[0], words[1], words[2]);
			}
			in.close();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		EndTime = System.nanoTime();
		System.out.println("Loading dataset from file to Binary Search Tree took: "+(EndTime - StartTime)+" nanoseconds");
	}
	
	public void InOrderTraversal(Node R){
		if(R != null){
			InOrderTraversal(R.getLeftSubTree());
			System.out.print(R.getWord()+"\t"+R.getPartOfSpeech()+"\t"+R.getMeaning()+"\n");
			InOrderTraversal(R.getRightSubTree());
		}
	}
	
	public void InsertNode(String word, String partOfSpeech, String meaning){
		
		word = word.replaceAll("^\\W", "");
		Node N = new Node(word, partOfSpeech, meaning);
		Node T;
		
		if(N != null){
			if(ROOT == null){
				ROOT = N;
			}else{
				T = ROOT;
				while(true){
					if(N.getWord().compareToIgnoreCase(T.getWord()) < 0){
						if(T.getLeftSubTree()==null){
							T.setLeftSubTree(N);
							break;
						}else{
							T = T.getLeftSubTree();
						}
					}else{
						if(T.getRightSubTree() == null){
							T.setRigtSubTree(N);
							break;
						}else{
							T = T.getRightSubTree();
						}
					}
				}
			}
		}
		length++;
	}
	
	public void lookUp(String searchValue){
		long StartTime =0;
		long EndTime =0;
		boolean found = false;
		Node currentPtr = this.ROOT; 
		StartTime = System.nanoTime();
		while(currentPtr != null){
			if(currentPtr.getWord().equals(searchValue)){
				found = true;
				System.out.println();
				EndTime = System.nanoTime();
				System.out.println("Searching for the word '"+searchValue+"' in the tree took: "+(EndTime - StartTime)+" nanoseconds");
				System.out.println(currentPtr.getWord()+"\t"+currentPtr.getPartOfSpeech()+"\t"+currentPtr.getMeaning());
				break;
			}else if(currentPtr.getWord().compareToIgnoreCase(searchValue) > 0){
				currentPtr = currentPtr.getLeftSubTree();
			}else{
				currentPtr = currentPtr.getRightSubTree();
			}
		}
		if(!found){
			System.out.println("Not Found!");
		}
	}
	
	 public void display(){
		long StartTime =0;
		long EndTime =0;
		StartTime = System.nanoTime();
	    if (ROOT != null){
	      System.out.println(ROOT.displayTree());
	      }
		  EndTime = System.nanoTime();
		  System.out.println("Displaying all words, part of speech and defintion in the tree took: "+(EndTime - StartTime)+" nanoseconds");
	    }
	 
}