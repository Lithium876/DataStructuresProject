package execution;
import java.util.Scanner;

import classes.BinarySearchTree;
import classes.LinkedList;

public class Driver {

	public static void main(String[] args) {
		LinkedList lList = new LinkedList();
		BinarySearchTree BST = new BinarySearchTree();
		
		BST.loadDataset();
		///lList.loadDataset();
		//lList.bubbleSort();
		//System.out.println("InOreder Traversal");
		//BST.InOrderTraversal(BST.getRoot());
//		System.out.println(BST.lengthOfTree());
		BST.lookUp("Yoke");
//		System.out.println();
		//lList.lookUp("Yoke");
//		System.out.println();
//		lList.displayDictionary();
//		System.out.println();
//		BST.display();
		
//		Scanner input= new Scanner(System.in);
//		int option=0;
//				 
//		System.out.println("*******************************************************");
//		System.out.println("*                  Data Dictionary                    *");
//		System.out.println("*******************************************************");
//		System.out.println("*     Press 1 to Load the Data set(LinkedList)        *");
//		System.out.println("*     Press 2 to Sort the Data set(LinkedList)        *");
//		System.out.println("*     Press 3 to Add words to dictionary              *");
//		System.out.println("*     Press 4 to search for a word                    *");
//		System.out.println("*     Press 5 to view the length of the list          *");
//		System.out.println("*     Press 6 to enter a sentence for validation      *");
//		System.out.println("*     Press 7 to Display the Dictionary               *");
//		System.out.println("*     Press 8 to Exit System                          *");
//		System.out.println("*******************************************************");
//		option=input.nextInt();
//		input.nextLine();
//		while(option!=8){
//			switch(option){
//				case 1:
//					lList.loadDataset();
//					break;
//				case 2:
//					lList.bubbleSort();
//					break;
//				case 3:
//					lList.addToDictionary("Paul", "n.", "A good ute");
//					break;
//				case 4:
//					lList.lookUp("Abbreviation"); 
//					break;
//				case 5:
//					System.out.print("\nLength of the list: "+lList.lengthOfList()+"\n");
//					break;
//				case 6:
//					lList.validateSentence("Paul is a good yute");
//					break;
//				case 7:
//					lList.displayDictionary();
//					break;
//				case 8:
//					break;
//				default:
//					System.out.println("Invalid Input!! \n");
//					break;
//			}	
//			option=input.nextInt();
//		}
//		input.close();
     }
}