package ca.csf.TP2_Demineur;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public abstract class ClockBase {

	private class ClockTickEventHanlder implements EventHandler<ActionEvent> {

		private AtomicInteger time;
		private int tickDelayInMiliseconds;
		private ClockBase clockBase;
		
		public ClockTickEventHanlder(int startTimeInMiliseconds, int tickDelayInMiliseconds,  ClockBase clockBase) {
			this.time = new AtomicInteger(startTimeInMiliseconds);
			this.tickDelayInMiliseconds = tickDelayInMiliseconds;
			this.clockBase = clockBase;
		}

		public int getTimeInMiliseconds() {
			return time.get();
		}
		
		public void setTimeInMiliseconds(int timeInMiliseconds) {
			time.set(timeInMiliseconds);
			clockBase.tick();
		}

		public void handle(ActionEvent actionEvent) {
			time.addAndGet(tickDelayInMiliseconds);
			clockBase.tick();
		}
	}

	private Timeline timeline;
	private ClockTickEventHanlder clockTickEventHanlder;

	public ClockBase(int startTimeInMiliseconds, int tickDelayInMiliseconds) {
		this.clockTickEventHanlder = new ClockTickEventHanlder(startTimeInMiliseconds, tickDelayInMiliseconds, this);
		this.timeline = new Timeline(new KeyFrame(Duration.millis(tickDelayInMiliseconds), clockTickEventHanlder));
		this.timeline.setCycleCount(Timeline.INDEFINITE);
	}

	public void start() {
		timeline.play();
	}

	public int getTimeInMiliseconds() {
		return clockTickEventHanlder.getTimeInMiliseconds();
	}

	public void setTimeInMiliseconds(int timeInMiliseconds) {
		clockTickEventHanlder.setTimeInMiliseconds(timeInMiliseconds);
	}

	public void stop() {
		timeline.stop();
	}
	
	protected abstract void tick();
	
}
