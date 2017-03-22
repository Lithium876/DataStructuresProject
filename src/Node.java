
public class Node {
	private String word;
	private String partOfSpeech;
	private String meaning;
	private Node nextWordMeaning;
	
	public Node(){
		this.word = "";
		this.partOfSpeech = "";
		this.meaning = "";
		this.nextWordMeaning = null;
	}
	
	public Node(String word, String partOfSpeech, String meaning) {
		this.word = word;
		this.partOfSpeech = partOfSpeech;
		this.meaning = meaning;
		this.nextWordMeaning = null;
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getPartOfSpeech() {
		return partOfSpeech;
	}

	public void setPartOfSpeech(String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public Node getNextWordMeaning() {
		return nextWordMeaning;
	}

	public void setNextWordMeaning(Node nextWordMeaning) {
		this.nextWordMeaning = nextWordMeaning;
	}
	
	public void display(){
		System.out.println(this.word+"\t"+this.partOfSpeech+"\t"+this.meaning);
	}
	
	public void displayWord(){
		System.out.println(this.word);
	}
}