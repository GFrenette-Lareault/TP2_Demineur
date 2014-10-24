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
		game.newGame();

		gameBoard = new MineButton[difficulty.width()][difficulty.height()];

		for (int i = 0; i < difficulty.width(); i++) {

			for (int j = 0; j < difficulty.height(); j++) {
				gameBoard[i][j] = new MineButton(i, j);
				gridPane.add(gameBoard[i][j], i, j);
			}
		}
		this.getSimpleFxStage().sizeToScene();
	}

}
