package ca.csf.TP2_Demineur;

import java.util.ArrayList;
import ca.csf.TP2_Demineur.EventHandler.ButtonEventHandler;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class MineButton extends ToggleButton implements
		EventHandler<MouseEvent> {

	private ArrayList<ButtonEventHandler> buttonEventList = new ArrayList<ButtonEventHandler>();

	private final int xPos;
	private final int yPos;

	public MineButton(int x, int y, ButtonEventHandler buttonEvent) {
		xPos = x;
		yPos = y;
		setMinWidth(25);
		setMinHeight(25);
		setMaxWidth(25);
		setMaxHeight(25);

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
		if (isSelected()) {
			if (event.getButton() == MouseButton.PRIMARY) {
				if (getGraphic() == null) {
					for (ButtonEventHandler buttonEvent : buttonEventList) {
						buttonEvent.onLeftClick(xPos, yPos);
						buttonEvent.onFirstClick();
					}
				}else{
					setSelected(false);
				}
			}
		} else if (event.getButton() == MouseButton.SECONDARY) {
			for (ButtonEventHandler buttonEvent : buttonEventList) {
				buttonEvent.onRightClick(xPos, yPos);
			}

		} else {
			setSelected(true);
		}
		event.consume();

	}

	public void clickButton() {
		for (ButtonEventHandler buttonEvent : buttonEventList) {
			buttonEvent.onLeftClick(xPos, yPos);
		}
		setSelected(true);

	}

}
