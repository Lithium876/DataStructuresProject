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
		
		Node N = new Node(word, partOfSpeech, meaning);
		Node T;
		
		if(N != null){
			if(ROOT == null){
				ROOT = N;
			}else{
				T = ROOT;
				while(true){
					if(N.getWord().compareTo(T.getWord()) < 0){
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
}