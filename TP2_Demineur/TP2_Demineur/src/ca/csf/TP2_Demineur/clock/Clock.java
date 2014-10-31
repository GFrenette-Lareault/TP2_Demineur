package ca.csf.TP2_Demineur.clock;

import java.util.ArrayList;
import java.util.List;

import ca.csf.TP2_Demineur.EventHandler.ClockEventHandler;


public class Clock extends ClockBase {

	private List<ClockEventHandler> clockEventList = new ArrayList<ClockEventHandler>();

	public Clock(int startTimeInMiliseconds, int tickDelayInMiliseconds) {
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
