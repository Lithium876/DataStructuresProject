package classes;

public class Data {

	private String Word;
	private String Part_Of_Speeech;
	private String Meaning;
	
	public Data()
	{
		this.Word="";
		this.Part_Of_Speeech="";
		this.Meaning="";
		
	}
	public Data(String w,String p,String m)
	{
		this.Word=w;
		this.Part_Of_Speeech=p;
		this.Meaning=m;
		
	}
	
	public String getWord() {
		return Word;
	}
	public void setWord(String word) {
		Word = word;
	}
	public String getPart_Of_Speeech() {
		return Part_Of_Speeech;
	}
	public void setPart_Of_Speeech(String part_Of_Speeech) {
		Part_Of_Speeech = part_Of_Speeech;
	}
	public String getMeaning() {
		return Meaning;
	}
	public void setMeaning(String meaning) {
		Meaning = meaning;
	}
	
	
	
}
