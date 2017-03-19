
public class Node {
	private String word_meaning;
	private Node nextWordMeaning;
	
	public Node(){
		word_meaning = "";
		nextWordMeaning = null;
	}
	
	public Node(String word_meaning){
		this.word_meaning = word_meaning;
		nextWordMeaning = null;
	}
	
	public void setWordMeaning(String word_meaning){
		this.word_meaning =  word_meaning;
	}
	
	public String getWordMeaning(){
		return word_meaning;
	}
	
	public void setNextWordMeaing(Node nextWordMeaning){
		this.nextWordMeaning = nextWordMeaning;
	}
	
	public Node getNextWordMeaning(){
		return nextWordMeaning;
	}
	
	public void display(){
		System.out.println(this.word_meaning);
	}
}