/*Lomar Lilly 1401375
 * Darryl Brown 1503803
 */

package gui;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import classes.BinarySearchTree;
import classes.CustomOutputStream;
import classes.LinkedList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class mainWindow 
{
	private JFrame frmDataStructureBenchmarker;
	private JTextField linkedListTime;
	private JTextField tree;
	private JTextArea LinkedList;
	private PrintStream Standardout;
	private JTextField word;
	private JTextField partOfSpeech;
	private JTextArea meaning;
	private JButton Sort;
	private JButton btnDisplay;
	private JButton btnAddWord;
	private JTextField searchValue;
	private JTextArea sentence;
	private JButton btnSearch;
	private JButton btnValidate;
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					mainWindow window = new mainWindow();
					window.frmDataStructureBenchmarker.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public mainWindow() 
	{
		initialize();
	}

	private void initialize() 
	{
		LinkedList lList = new LinkedList();
		BinarySearchTree BST = new BinarySearchTree();
		frmDataStructureBenchmarker = new JFrame();
		frmDataStructureBenchmarker.setTitle("Data Structure Benchmarker");
		frmDataStructureBenchmarker.setResizable(false);
		frmDataStructureBenchmarker.setBounds(100, 20, 1104, 663);
		frmDataStructureBenchmarker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDataStructureBenchmarker.getContentPane().setLayout(null);
		//Java look and feel code for GUI current look
		try 
		{
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
		    {
		        if ("Nimbus".equals(info.getName())) 
		        {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} 
		catch(Exception e) 
		{
		    System.out.println(e);
		}
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(554, 50, 534, 342);
		frmDataStructureBenchmarker.getContentPane().add(scrollPane_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 532, 342);
		frmDataStructureBenchmarker.getContentPane().add(scrollPane);
		
		JTextArea LinkedList_1 = new JTextArea();
		scrollPane.setViewportView(LinkedList_1);
		LinkedList_1.setEditable(false);
	
	    JTextArea treeDisplay = new JTextArea();
		treeDisplay.setEditable(false);
		scrollPane_2.setViewportView(treeDisplay);
		
		JButton btnNewButton = new JButton("Load Dataset");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//Button Action that loads data set into both data structures, Linked list and Binary tree
				try
				{
					btnNewButton.setEnabled(false);
					searchValue.setEnabled(true);
					btnDisplay.setEnabled(true);
					sentence.setEnabled(true);
					Sort.setEnabled(true);
					word.setEnabled(true);
					
					String str = lList.loadDataset();
					String str2 = BST.loadDataset();
					
					linkedListTime.setText(str);
					tree.setText(str2);
				}
				catch(Exception err)
				{
					System.out.println(err);
				}
			}
		});
		btnNewButton.setBounds(10, 443, 135, 40);
		frmDataStructureBenchmarker.getContentPane().add(btnNewButton);
		
		btnDisplay = new JButton("Display");
		btnDisplay.setEnabled(false);
		btnDisplay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDisplay.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//Button Action that Displays all the words in the respective data structures.
				try
				{
					LinkedList_1.setText(null);
					treeDisplay.setText(null);
					btnDisplay.setEnabled(false);
					
					PrintStream printStream = new PrintStream(new CustomOutputStream(LinkedList_1));
					System.setOut(printStream);
				    System.setErr(printStream);    
					String str = lList.displayDictionary();
					linkedListTime.setText(str);
					
					printStream = new PrintStream(new CustomOutputStream(treeDisplay));
					System.setOut(printStream);
				    System.setErr(printStream);
				    String str2 = BST.display();
				    tree.setText(str2);
				}
				catch(Exception err)
				{
					System.out.println(err);
				}
			}
		});
		btnDisplay.setBounds(10, 545, 135, 40);
		frmDataStructureBenchmarker.getContentPane().add(btnDisplay);
		
		Sort = new JButton("Sort");
		Sort.setEnabled(false);
		Sort.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Sort.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//Button Action that sorts the dataset in each data structure
				try
				{
					Sort.setEnabled(false);
					btnDisplay.setEnabled(true);
					LinkedList_1.setText(null);
					treeDisplay.setText(null);
					
					PrintStream printStream = new PrintStream(new CustomOutputStream(LinkedList_1));
					System.setOut(printStream);
				    System.setErr(printStream); 
					String str = lList.bubbleSort();
					linkedListTime.setText(str); 
					
					printStream = new PrintStream(new CustomOutputStream(treeDisplay));
					System.setOut(printStream);
				    System.setErr(printStream);
					long StartTime =0;
					long EndTime =0;
					StartTime = System.nanoTime();
					BST.sort(BST.getRoot());
					EndTime = System.nanoTime();
					tree.setText("Sorting the binary Search tree took: "+(EndTime - StartTime)+" nanoseconds.");
				}
				catch(Exception err)
				{
					System.out.println(err);
				}
			}
		});
		Sort.setBounds(10, 494, 135, 40);
		frmDataStructureBenchmarker.getContentPane().add(Sort);
		
		linkedListTime = new JTextField();
		linkedListTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		linkedListTime.setEditable(false);
		linkedListTime.setBounds(8, 403, 534, 29);
		frmDataStructureBenchmarker.getContentPane().add(linkedListTime);
		linkedListTime.setColumns(10);
		
		tree = new JTextField();
		tree.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tree.setEditable(false);
		tree.setColumns(10);
		tree.setBounds(552, 403, 534, 29);
		frmDataStructureBenchmarker.getContentPane().add(tree);
		
		JLabel lblLinkedList = new JLabel("Linked List");
		lblLinkedList.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		lblLinkedList.setBounds(170, 0, 195, 47);
		frmDataStructureBenchmarker.getContentPane().add(lblLinkedList);
		
		JLabel lblBinarySearchTree = new JLabel("Binary Search Tree");
		lblBinarySearchTree.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		lblBinarySearchTree.setBounds(672, 0, 341, 47);
		frmDataStructureBenchmarker.getContentPane().add(lblBinarySearchTree);
		
		btnAddWord = new JButton("Add Word");
		btnAddWord.setEnabled(false);
		btnAddWord.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddWord.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//Button Action that new words to each data structure
				try
				{
					btnDisplay.setEnabled(true);
					btnAddWord.setEnabled(false);
					LinkedList_1.setText(null);
					treeDisplay.setText(null);
					
					String str = lList.addToDictionary(word.getText(), partOfSpeech.getText(), meaning.getText());
					linkedListTime.setText(str);  
					
					String str2 = BST.addWord(word.getText(), partOfSpeech.getText(), meaning.getText());
					tree.setText(str2);
				
					word.setText(null);
					partOfSpeech.setText(null);
					meaning.setText(null);
				}
				catch(Exception err)
				{
					System.out.println(err);
				}
			}
		});
		btnAddWord.setBounds(353, 590, 135, 40);
		frmDataStructureBenchmarker.getContentPane().add(btnAddWord);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(155, 443, 10, 187);
		frmDataStructureBenchmarker.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(166, 529, -10, 134);
		frmDataStructureBenchmarker.getContentPane().add(separator_1);
		
		word = new JTextField();
		word.addKeyListener(new KeyAdapter() 
		{
			//This action listener function listens to the text field and activate the "Add Word" button
			//if the user types a word in the field
			@Override
			public void keyReleased(KeyEvent e) 
			{
				try
				{
					if(word.getText().length()==0)
					{
						btnAddWord.setEnabled(false);
					}
					else
					{
						btnAddWord.setEnabled(true);
					}
				}
				catch(Exception err)
				{
					System.out.println(err);
				}
			}
		});
		word.setEnabled(false);
		word.setBounds(297, 448, 245, 31);
		frmDataStructureBenchmarker.getContentPane().add(word);
		word.setColumns(10);
		
		partOfSpeech = new JTextField();
		partOfSpeech.setColumns(10);
		partOfSpeech.setBounds(297, 485, 245, 30);
		frmDataStructureBenchmarker.getContentPane().add(partOfSpeech);
		
		JLabel lblWord = new JLabel("Word:");
		lblWord.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblWord.setBounds(170, 446, 51, 30);
		frmDataStructureBenchmarker.getContentPane().add(lblWord);
		
		JLabel lblPartOfSpeech = new JLabel("Part of Speech:");
		lblPartOfSpeech.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblPartOfSpeech.setBounds(170, 483, 134, 30);
		frmDataStructureBenchmarker.getContentPane().add(lblPartOfSpeech);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//Button Action that creates a brand new instance of the GUI and the program
				try
				{
					frmDataStructureBenchmarker.dispose();
					mainWindow reset = new mainWindow();
					reset.frmDataStructureBenchmarker.setVisible(true);
				}
				catch(Exception err)
				{
					System.out.println(err);
				}
			}
		});
		btnReset.setBounds(30, 596, 89, 23);
		frmDataStructureBenchmarker.getContentPane().add(btnReset);
		
		JLabel lblMeaning = new JLabel("Meaning:");
		lblMeaning.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblMeaning.setBounds(170, 526, 121, 30);
		frmDataStructureBenchmarker.getContentPane().add(lblMeaning);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(297, 526, 245, 54);
		frmDataStructureBenchmarker.getContentPane().add(scrollPane_3);
		
		meaning = new JTextArea();
		meaning.setLineWrap(true);
		meaning.setWrapStyleWord(true);
		scrollPane_3.setViewportView(meaning);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(554, 441, 10, 187);
		frmDataStructureBenchmarker.getContentPane().add(separator_2);
		
		searchValue = new JTextField();
		searchValue.addKeyListener(new KeyAdapter() 
		{
			//This action listener function listens to the text field and activate the "Search" button
			//if the user types a word in the field
			@Override
			public void keyReleased(KeyEvent e) 
			{
				try
				{
					if(searchValue.getText().length()==0)
					{
						btnSearch.setEnabled(false);
					}
					else
					{
						btnSearch.setEnabled(true);
					}
				}
				catch(Exception err)
				{
					System.out.println(err);
				}
			}
		});
		searchValue.setEnabled(false);
		searchValue.setColumns(10);
		searchValue.setBounds(712, 454, 232, 31);
		frmDataStructureBenchmarker.getContentPane().add(searchValue);
		
		JLabel lblSearch = new JLabel("Look Up:");
		lblSearch.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblSearch.setBounds(576, 453, 108, 30);
		frmDataStructureBenchmarker.getContentPane().add(lblSearch);
		
		btnSearch = new JButton("Search");
		btnSearch.setEnabled(false);
		btnSearch.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//Button Action that searches each data structure for a search value entered by the user
				try
				{
					LinkedList_1.setText(null);
					treeDisplay.setText(null);
					btnDisplay.setEnabled(true);
					
					PrintStream printStream = new PrintStream(new CustomOutputStream(LinkedList_1));
					System.setOut(printStream);
				    System.setErr(printStream);
				    String str = lList.lookUp(searchValue.getText());
					linkedListTime.setText(str);  
					
					printStream = new PrintStream(new CustomOutputStream(treeDisplay));
					System.setOut(printStream);
				    System.setErr(printStream);
				    String str2 = BST.lookUp(searchValue.getText());
				    tree.setText(str2);			
				}
				catch(Exception err)
				{
					System.out.println(err);
				}	
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.setBounds(956, 448, 135, 40);
		frmDataStructureBenchmarker.getContentPane().add(btnSearch);
		
		JLabel lblValidateSentence = new JLabel("Validate Sentence:");
		lblValidateSentence.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblValidateSentence.setBounds(576, 545, 135, 30);
		frmDataStructureBenchmarker.getContentPane().add(lblValidateSentence);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(713, 550, 232, 67);
		frmDataStructureBenchmarker.getContentPane().add(scrollPane_1);
		
		sentence = new JTextArea();
		sentence.setLineWrap(true);
		sentence.setWrapStyleWord(true);
		scrollPane_1.setViewportView(sentence);
		sentence.addKeyListener(new KeyAdapter() 
		{
			//This action listener function listens to the text field and activate the "Validate" button
			//if the user types a word in the field
			@Override
			public void keyReleased(KeyEvent e) 
			{
				try
				{
					if(sentence.getText().length()==0)
					{
						btnValidate.setEnabled(false);
					}
					else
					{
						btnValidate.setEnabled(true); 
					}
				}
				catch(Exception err)
				{
					System.out.println(err);
				}
			}
		});
		sentence.setEnabled(false);
		
		btnValidate = new JButton("Validate");
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Button Action that validates the sentence entered by the user
				//The validation happens in both data structure to get the time each structure take
				//to validate the sentence given
				try
				{
					JOptionPane.showMessageDialog(null, "In order to accurately benchmark the two data structres\nin validating sentences, you will be promted to add words twice.","NOTICE", JOptionPane.INFORMATION_MESSAGE);
					
					String str = lList.validateSentence(sentence.getText());
					linkedListTime.setText(str);  	
					
					JOptionPane.showMessageDialog(null, "You will be promted again to add words that are not in the dictionary","REMINDER", JOptionPane.INFORMATION_MESSAGE);
					
					String str2 = BST.validateSentence(sentence.getText());
					tree.setText(str2);
					sentence.setText(null);
				}
				catch(Exception err)
				{
					System.out.println(err);
				}
			}
		});
		btnValidate.setEnabled(false);
		btnValidate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnValidate.setBounds(953, 561, 135, 40);
		frmDataStructureBenchmarker.getContentPane().add(btnValidate);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(563, 515, 525, 2);
		frmDataStructureBenchmarker.getContentPane().add(separator_3);
	}
}
