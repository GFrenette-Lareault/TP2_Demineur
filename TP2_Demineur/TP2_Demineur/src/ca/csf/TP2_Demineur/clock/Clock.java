package ca.csf.TP2_Demineur.clock;

import java.util.ArrayList;
import java.util.List;





import ca.csf.exercice_15.ClockEventHandler;
import ca.csf.exercice_15.MainSceneController;
import ca.csf.exercice_15.clock.ClockBase;


public class Clock extends ClockBase {

	private List<ClockEventHandler> clockEventList = new ArrayList<ClockEventHandler>();

	public Clock(int startTimeInMiliseconds, int tickDelayInMiliseconds, ClockSceneController clockSceneController) {
		super(startTimeInMiliseconds, tickDelayInMiliseconds);
	}
	
	protected void tick() {
		for (ClockEventHandler clockEventHandler : clockEventList) {
			clockEventHandler.onTimeChanged(super.getTimeInMiliseconds());
		}
	}

	public void addClockEventhandler(ClockEventHandler args) {
		clockEventList.add(args);
	}
}
