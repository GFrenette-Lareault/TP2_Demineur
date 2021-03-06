package ca.csf.TP2_Demineur.EventHandler;

import ca.csf.TP2_Demineur.ButtonImage;

public interface GameEventHandler {

	public void victory();
	
	public void gameOver();
	
	public void buttonUpdate(int x, int y, ButtonImage image);
	
	public void buttonLeftClick(int x, int y);
	
	public void updateFlag(int flags);
}
