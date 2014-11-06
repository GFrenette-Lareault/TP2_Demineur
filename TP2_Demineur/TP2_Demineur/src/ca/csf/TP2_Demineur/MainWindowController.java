package ca.csf.TP2_Demineur;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import ca.csf.TP2_Demineur.EventHandler.ButtonEventHandler;
import ca.csf.TP2_Demineur.EventHandler.ClockEventHandler;
import ca.csf.TP2_Demineur.EventHandler.GameEventHandler;
import ca.csf.TP2_Demineur.clock.Clock;
import ca.csf.simpleFx.SimpleFXController;
import ca.csf.simpleFx.events.WindowFocusEvent;
import javafx.scene.control.RadioMenuItem;

public class MainWindowController extends SimpleFXController implements
		ClockEventHandler, ButtonEventHandler, GameEventHandler,
		EventHandler<WindowFocusEvent> {

	private Game game;
	private Difficulty difficulty;
	private MineButton gameBoard[][];
	@FXML
	private Label timeLabel;
	@FXML
	private Label flagsCounter;
	private int nbFlags;

	private Clock clock;

	@FXML
	private GridPane gridPane;
	@FXML 
	private RadioMenuItem cheatBtn;

	public MainWindowController() {
		game = new Game(this);
		difficulty = Difficulty.DEBUTANT;
		initialize(new Clock(0, 1000));
	}

	@Override
	protected void onLoadedStage() {
		getSimpleFxStage().setOnFocusChanged(this);
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
		gridPane.getChildren().clear();
		clock.reset();
		gameBoard = new MineButton[difficulty.width()][difficulty.height()];

		for (int i = 0; i < difficulty.width(); i++) {

			for (int j = 0; j < difficulty.height(); j++) {
				gameBoard[i][j] = new MineButton(i, j, this);
				gridPane.add(gameBoard[i][j], i, j);
			}
		}
		
		game.newGame(difficulty);
		
		nbFlags = difficulty.nbMine();

		flagsCounter.setText(String.valueOf(nbFlags));

		getSimpleFxStage().sizeToScene();
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
		clock.pause();
	}

	public void gameOver() {
		clock.pause();

	}
	@FXML
	public void cheat(){
		game.cheat();
	}

	public void buttonRightClicked(int x, int y, ButtonImage image) {

		ImageView imagev = new ImageView(new Image("file:ressource/"
				+ image.URL()));

		gameBoard[x][y].setGraphic(imagev);
	}

	public void buttonLeftClicked(int x, int y, ButtonImage image) {
		ImageView imagev = new ImageView(new Image("file:ressource/"
				+ image.URL()));

		gameBoard[x][y].setGraphic(imagev);
	}

	public void onFirstClick() {
		if (clock.getTimeInMiliseconds() == 0) {
			clock.start();
		}
	}

	public void updateFlag(int flags) {
		nbFlags = flags;
		flagsCounter.setText(String.valueOf(nbFlags));
	}

	public void buttonLeftClick(int x, int y) {
		gameBoard[x][y].clickButton();

	}

	@Override
	public void handle(WindowFocusEvent event) {

		if (event.getEventType() == WindowFocusEvent.GET_FOCUS) {
			if (clock.getTimeInMiliseconds() != 0) {
				clock.start();
			}
		} else if (event.getEventType() == WindowFocusEvent.LOOSE_FOCUS) {
			clock.pause();
		}
	}
}
