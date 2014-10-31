package ca.csf.TP2_Demineur;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

public class MineButton extends ToggleButton implements EventHandler<MouseEvent> {

	private ArrayList<ButtonEventHandler> buttonEventList = new ArrayList<ButtonEventHandler>();

	private final int xPos;
	private final int yPos;

	public MineButton(int x, int y, ButtonEventHandler buttonEvent) {
		xPos = x;
		yPos = y;
		setMinWidth(30);
		setMinHeight(30);
		setMaxWidth(30);
		setMaxHeight(30);
		addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		buttonEventList.add(buttonEvent);
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
			for (ButtonEventHandler buttonEvent:buttonEventList) {
				buttonEvent.onLeftClick(xPos, yPos);
			}
		} else if (event.isSecondaryButtonDown()) {
			for (ButtonEventHandler buttonEvent:buttonEventList) {
				buttonEvent.onRightClick(xPos, yPos);
			}
		}

	}

}
