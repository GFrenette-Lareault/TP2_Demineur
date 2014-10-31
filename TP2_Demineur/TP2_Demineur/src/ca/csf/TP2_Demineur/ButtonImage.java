package ca.csf.TP2_Demineur;

public enum ButtonImage {
EMPTY(0,null), ONE(1,""), FLAG(9,""), QUESTIONMARK(10,"");
	
private final int index;
private final String URL;

	private ButtonImage(int index,String URL){
		this.index = index;
		this.URL = URL;
	}
}
