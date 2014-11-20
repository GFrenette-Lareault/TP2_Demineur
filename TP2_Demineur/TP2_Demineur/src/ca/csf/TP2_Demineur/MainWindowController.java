package ca.csf.TP2_Demineur;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
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
import ca.csf.simpleFx.SimpleFXScene;
import ca.csf.simpleFx.SimpleFXStage;
import ca.csf.simpleFx.dialogs.SimpleFXDialogChoiceSet;
import ca.csf.simpleFx.dialogs.SimpleFXDialogIcon;
import ca.csf.simpleFx.dialogs.SimpleFXDialogResult;
import ca.csf.simpleFx.dialogs.SimpleFXDialogs;
import ca.csf.simpleFx.events.WindowFocusEvent;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.stage.StageStyle;

public class MainWindowController extends SimpleFXController implements
		ClockEventHandler, ButtonEventHandler, GameEventHandler,
		EventHandler<WindowFocusEvent> {

	private static final String RESSOURCE_PATH = "file:ressource/";
	private static final String ABOUT_TITLE = "À propos";
	private static final String ABOUT_TEXT = "Démineur \r \n Par \r\n Francois Chantal, \r\n Bruno Cyr, \r\n Gabriel Frenette-L.";

	private Game game;
	private Difficulty difficulty;
	private MineButton gameBoard[][];
	private ScoresWindowController controller = new ScoresWindowController();
	@FXML
	private Label timeLabel;
	@FXML
	private Label flagsCounter;
	private int nbFlags;

	private Clock clock;
	private boolean isGameOver;

	@FXML
	private GridPane gridPane;
	@FXML
	private RadioMenuItem cheatBtn;
	@FXML
	private Button btnNewGame;

	// inutiliser dans le code(que pour le FXML)
	@FXML
	private ToggleGroup difficultyGroup;

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
		isGameOver = false;
		gridPane.setDisable(false);
		ImageView imagev = new ImageView(new Image(RESSOURCE_PATH
				+ ButtonImage.SMILE.URL()));
		btnNewGame.setGraphic(imagev);
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
		getSimpleFxStage().centerOnScreen();
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
		try {
			controller.checkScore(this.difficulty, (clock.getTimeInMiliseconds()/1000), getSimpleFxStage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ImageView imagev = new ImageView(new Image(RESSOURCE_PATH
				+ ButtonImage.SMILE_HAPPY.URL()));
		btnNewGame.setGraphic(imagev);
		removeAllBtnEvent();
		
	}

	public void gameOver() {
		clock.pause();
		ImageView imagev = new ImageView(new Image(RESSOURCE_PATH
				+ ButtonImage.SMILE_DEAD.URL()));
		btnNewGame.setGraphic(imagev);
		isGameOver = true;
		removeAllBtnEvent();
	}

	private void removeAllBtnEvent() {
		EventHandler<MouseEvent> filter = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// pour filtrer les events et empecher d'appeler les autres
				event.consume();
			}
		};
		for (int i = 0; i < difficulty.width(); i++) {
			for (int j = 0; j < difficulty.height(); j++) {
				gameBoard[i][j].addEventFilter(MouseEvent.ANY, filter);
				;
			}
		}
	}

	@FXML
	public void cheat() {
		game.cheat();
	}

	public void buttonUpdate(int x, int y, ButtonImage image) {

		if (image != ButtonImage.EMPTY) {
			ImageView imagev = new ImageView(new Image(RESSOURCE_PATH
					+ image.URL()));

			gameBoard[x][y].setGraphic(imagev);
		} else {
			gameBoard[x][y].setGraphic(null);
		}
	}

	public void onFirstClick() {
		if (clock.getTimeInMiliseconds() == 0 && !isGameOver) {
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

	@FXML
	public void bestScore() {
		try {
			SimpleFXScene simpleFXScene = new SimpleFXScene(ScoresWindowController.class.getResource("Scores.fxml"),
					MainWindowController.class.getResource("application.css"), controller);
			SimpleFXStage simpleFXStage = new SimpleFXStage("Meilleurs scores", StageStyle.UTILITY, simpleFXScene, getSimpleFXApplication());
			simpleFXStage.setResizable(false);
			controller.getScoresText();
			simpleFXStage.showAndWait();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void help() {
		SimpleFXStage helpStage;
		SimpleFXScene helpScene;
		try {
			helpScene = new SimpleFXScene(getClass().getResource(
					"HelpWindow.fxml"), getClass().getResource("application.css"), new HelpControler());
			helpStage = new SimpleFXStage(ABOUT_TITLE, StageStyle.DECORATED, helpScene, getSimpleFXApplication());
			helpStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void about() {
		SimpleFXDialogs.showMessageBox(ABOUT_TITLE, ABOUT_TEXT,
				SimpleFXDialogIcon.INFORMATION, SimpleFXDialogChoiceSet.OK,
				SimpleFXDialogResult.OK, getSimpleFxStage());
	}
}
