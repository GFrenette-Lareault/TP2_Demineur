package ca.csf.TP2_Demineur;

import ca.csf.TP2_Demineur.EventHandler.GameEventHandler;
public class GameTest {
	private Game game;
	
	public void setUp() {
		game = new Game((GameEventHandler) this);
	}
	
	public void whenNewGame_thenParametersCreatedCorrectly() {
		game.newGame(Difficulty.DEBUTANT);
		assert(game.isNewGameOk() == true);
	}
	
	public void whenMinesAreCreated_thenMinesAreOnField() {
		Cell[][] cell = game.getCell();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (cell[i][j].getIsAMine() == true) {
					assert(true);
				}
			}
		}
		assert(false);
	}
}
