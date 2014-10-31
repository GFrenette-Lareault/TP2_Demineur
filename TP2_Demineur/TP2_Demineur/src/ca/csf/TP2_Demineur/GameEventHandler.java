package ca.csf.TP2_Demineur;

public interface GameEventHandler {

	public void victory();
	
	public void gameOver();
	
	public void buttonRightClicked(int x, int y, ButtonImage image);
	
	public void buttonLeftClicked(int x, int y, ButtonImage image);
}
