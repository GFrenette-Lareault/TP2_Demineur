package ca.csf.TP2_Demineur;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import ca.csf.TP2_Demineur.clock.Clock;
import ca.csf.TP2_Demineur.clock.ClockEventHandler;
import ca.csf.simpleFx.SimpleFXController;

public class MainWindowController extends SimpleFXController implements ClockEventHandler{

	private Game game;
	private Difficulty difficulty;
	private MineButton gameBoard[][];
	@FXML
	private Label timeLabel;
	private Clock clock;

	@FXML
	private GridPane gridPane;

	public MainWindowController() {
		game = new Game();
		difficulty = Difficulty.DEBUTANT;
		initialize(new Clock(0, 1000));
	}
	//Clock
	public void initialize(Clock clock) {
		this.clock = clock;
		this.clock.start();
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
				gameBoard[i][j] = new MineButton(i, j);
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
}
