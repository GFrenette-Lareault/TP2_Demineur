package ca.csf.TP2_Demineur.clock;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ClockSceneController implements ClockEventHandler{

	@FXML
	private Label timeLabel;
	
	private Clock clock;
	
	public void initialize(Clock clock) {
		this.clock = clock;
		this.clock.start();
		clock.addClockEventhandler(this);
	}
	

	//private void updateTime(int timeInMiliseconds) {
	//	int timeInSeconds = timeInMiliseconds / 1000;
	//	int hours = timeInSeconds / 3600;
	//	int minutes = timeInSeconds / 60 - hours * 60;
	//	int seconds = timeInSeconds % 60;
	//	timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
	//}

	@Override
	public void onTimeChanged(int timeInMilliseconds) {
		int timeInSeconds = timeInMilliseconds / 1000;
		//int hours = timeInSeconds / 3600;
		//int minutes = timeInSeconds / 60 - hours * 60;
		int seconds = timeInSeconds % 999;
		timeLabel.setText(String.format("%03d", seconds));
		
	}
}
