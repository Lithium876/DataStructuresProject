
public class LinkedList {
	
	private Node HEAD;
	private int length;
	
	public LinkedList(){
		HEAD = null;
		length = 0;
	}
	
	@SuppressWarnings("unused")
	public boolean isFull(){
	    Node new_node = new Node("DAta");
	    if(new_node != null){
	        return false;
	    }
	    return true;
	}
	
	public int lengthOfList(){
		return length;
	}
	
	public boolean isEmpty(){
		if (HEAD == null){
			return true;
		}
		return false;
	}
	
	public void insertWordMeaning(String wordMeaning){
		//implements insertion sort
		if(isFull()){
			System.out.println("Error - Cannot insert the item");
		}else{
			Node newWordMeaning = new Node(wordMeaning);
			Node currPtr = new Node();
			Node temp = new Node();
			boolean insert = false;
			
			if(isEmpty()){
				this.HEAD = newWordMeaning;
			}
			else{
				if(this.HEAD.getWordMeaning().compareTo(wordMeaning)>0){
					newWordMeaning.setNextWordMeaing(this.HEAD);
					this.HEAD = newWordMeaning;
				}else{
					temp = this.HEAD;
					currPtr = this.HEAD.getNextWordMeaning();
					while(currPtr != null){
						if(temp.getWordMeaning().compareTo(wordMeaning)<0 && currPtr.getWordMeaning().compareTo(wordMeaning)>0){
							temp.setNextWordMeaing(newWordMeaning);
							newWordMeaning.setNextWordMeaing(currPtr);
							insert = true;
							break;
						}else{
							temp = currPtr;
							currPtr = currPtr.getNextWordMeaning();
						}
					}
				}
				if(insert==false){
						temp.setNextWordMeaing(newWordMeaning);
				}
			}
			length++;
		}
	}
	
	public void displayList(){
		Node currNode = HEAD;
		while(currNode != null){
			currNode.display();
			currNode = currNode.getNextWordMeaning();
		}
	}
}
