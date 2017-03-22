package execution;

import java.util.Scanner;

import classes.LinkedList;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList lList = new LinkedList();
		Scanner input= new Scanner(System.in);
		int option=0;
	 
		 lList.loadAndSortDataset();
		 
		System.out.println("**************************************************************");
		System.out.println("*                      Data Dictionary                       *");
		System.out.println("**************************************************************");
		System.out.println("*            Press 1 to Sort the Data set(LinkedList)        *");
		System.out.println("*            Press 2 to Add words to dictionary              *");
		System.out.println("*            Press 3 to search for a word                    *");
		System.out.println("*            Press 4 to view the length of the list          *");
		System.out.println("*            Press 5 to enter a sentence for validation      *");
		System.out.println("*            Press 6 to Display the Dictionary               *");
		System.out.println("*            Press 7 to Exit System                          *");
		System.out.println("**************************************************************");
		option=input.nextInt();
		input.nextLine();
		while(option!=7)
		{
			switch(option)
			{
			 case 1:
					lList.bubbleSort();
				 break;
			 case 2:
				    lList.addToDictionary("Paul", "n.", "A good ute");
				 break;
			 case 3:
				 System.out.println(lList.lookUp("Abbreviation")); 
				 break;
			 case 4:
				 System.out.print("\nLength of the list: "+lList.lengthOfList()+"\n");
				 break;
			 case 5:
				  lList.validateSentence("Paul is a good yute");
				 break;
			 case 6:
				 lList.displayDictionary();
				 break;
			 default:
				 System.out.println("Invalid Input!! \n");
			    break;
			}
			
			System.out.println("**************************************************************");
			System.out.println("*                      Data Dictionary                       *");
			System.out.println("**************************************************************");
			System.out.println("*            Press 1 to LOAD the Data set                    *");
			System.out.println("*            Press 2 to Sort the Data set(LinkedList)        *");
			System.out.println("*            Press 3 to Add words to dictionary              *");
			System.out.println("*            Press 4 to search for a word                    *");
			System.out.println("*            Press 5 to view the length of the list          *");
			System.out.println("*            Press 6 to enter a sentence for validation      *");
			System.out.println("*            Press 7 to Display the Dictionary               *");
			System.out.println("*            Press 8 to Exit System                          *");
			System.out.println("**************************************************************");
			option=input.nextInt();
			
		}
       input.close();
	//	System.out.println(lList.lookUp("Good")); 
	}

}
