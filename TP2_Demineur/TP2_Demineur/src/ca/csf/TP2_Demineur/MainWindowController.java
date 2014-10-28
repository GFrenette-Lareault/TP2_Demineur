package ca.csf.TP2_Demineur;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import ca.csf.simpleFx.SimpleFXController;

public class MainWindowController extends SimpleFXController {

	private Game game;
	private Difficulty difficulty;
	private MineButton gameBoard[][];
	@FXML
	private GridPane gridPane;

	public MainWindowController() {
		game = new Game();
		difficulty = Difficulty.DEBUTANT;
	}
	
	@FXML
	public void newGame() {
		game.newGame(difficulty);
		gridPane.getChildren().clear();
		
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
