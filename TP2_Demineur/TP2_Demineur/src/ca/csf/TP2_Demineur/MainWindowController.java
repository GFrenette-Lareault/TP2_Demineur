package ca.csf.TP2_Demineur;

import javafx.fxml.FXML;
import ca.csf.simpleFx.SimpleFXController;

public class MainWindowController extends SimpleFXController {

	private Game game;
	private MineButton gameBoard[][];

	public MainWindowController() {
		game = new Game();
	}

	@FXML
	public void NewGame() {
		game.newGame();
		
		gameBoard = new MineButton[9][9] ;
		for (int i = 0; i < 9 ;i++) {
			for (int j = 0; j < 9 ;j++) {
				gameBoard[i][j] = new MineButton(i, j);
			}
		}
	}

}
