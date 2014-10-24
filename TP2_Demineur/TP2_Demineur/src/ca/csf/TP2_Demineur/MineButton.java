package ca.csf.TP2_Demineur;

import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

public class MineButton extends ToggleButton implements EventHandler<MouseEvent> {

	private final int xPos;
	private final int yPos;

	public MineButton(int x, int y) {
		xPos = x;
		yPos = y;
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		this.setHeight(10);
		this.setWidth(10);
		
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

	@Override
	public void handle(MouseEvent event) {
		if (event.isPrimaryButtonDown()) {

		} else if (event.isSecondaryButtonDown()) {

		}

	}

}
