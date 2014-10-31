package ca.csf.TP2_Demineur;

public enum ButtonImage {
EMPTY(0,null), ONE(1,""), FLAG(9,""), QUESTION_MARK(10,""), MINE_FLAG(11,""), MINE_NORMAL(12,"");
	
private final int index;
private final String URL;

	private ButtonImage(int index,String URL){
		this.index = index;
		this.URL = URL;
	}
	
	public int index(){
		return index;
	}
	
	public String URL(){
		return URL;
	}
}
