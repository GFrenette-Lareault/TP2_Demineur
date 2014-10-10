package ca.csf.TP2_Demineur;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class MineButton extends Button implements EventHandler<MouseEvent>{

	private final int xPos;
	private final int yPos;
	
	public MineButton(int x, int y){
		xPos = x;
		yPos = y;
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

	@Override
	public void handle(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

}
