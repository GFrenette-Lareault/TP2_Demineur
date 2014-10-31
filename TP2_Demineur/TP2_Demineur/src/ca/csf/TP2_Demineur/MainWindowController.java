package ca.csf.TP2_Demineur;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import ca.csf.TP2_Demineur.EventHandler.ButtonEventHandler;
import ca.csf.TP2_Demineur.EventHandler.ClockEventHandler;
import ca.csf.TP2_Demineur.EventHandler.GameEventHandler;
import ca.csf.TP2_Demineur.clock.Clock;
import ca.csf.simpleFx.SimpleFXController;

public class MainWindowController extends SimpleFXController implements
		ClockEventHandler, ButtonEventHandler, GameEventHandler {

	private Game game;
	private Difficulty difficulty;
	private MineButton gameBoard[][];
	@FXML
	private Label timeLabel;
	private Clock clock;

	@FXML
	private GridPane gridPane;

	public MainWindowController() {
		game = new Game(this);
		difficulty = Difficulty.DEBUTANT;
		initialize(new Clock(0, 1000));
	}

	// Clock
	public void initialize(Clock clock) {
		this.clock = clock;
		clock.addClockEventhandler(this);
	}

	@Override
	public void onTimeChanged(int timeInMilliseconds) {
		int timeInSeconds = timeInMilliseconds / 1000;
		int seconds = timeInSeconds % 999;
		timeLabel.setText(String.format("%03d", seconds));

	}

	@FXML
	public void newGame() {
		game.newGame(difficulty);
		gridPane.getChildren().clear();
		clock.reset();
		gameBoard = new MineButton[difficulty.width()][difficulty.height()];

		for (int i = 0; i < difficulty.width(); i++) {

			for (int j = 0; j < difficulty.height(); j++) {
				gameBoard[i][j] = new MineButton(i, j, this);
				gridPane.add(gameBoard[i][j], i, j);
			}
		}
		this.getSimpleFxStage().sizeToScene();
	}

	@FXML
	public void setEasy() {
		difficulty = Difficulty.DEBUTANT;
		newGame();
	}

	@FXML
	public void setMedium() {
		difficulty = Difficulty.INTERMEDIAIRE;
		newGame();
	}

	@FXML
	public void setHard() {
		difficulty = Difficulty.EXPERT;
		newGame();
	}

	public void onRightClick(int x, int y) {
		game.updateRightClick(x, y);
	}

	public void onLeftClick(int x, int y) {
		game.updateLeftClick(x, y);
	}

	public void victory() {
		
	}

	public void gameOver() {
		
	}

	public void buttonRightClicked(int x, int y, ButtonImage image) {
		
	}

	public void buttonLeftClicked(int x, int y, ButtonImage image) {
		
	}

	public void onFirstClick() {
		if (clock.getTimeInMiliseconds() == 0) {
			clock.start();
		}
		
	}
}
