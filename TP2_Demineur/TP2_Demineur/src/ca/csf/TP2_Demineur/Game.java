package ca.csf.TP2_Demineur;

import java.lang.Math;
import java.util.ArrayList;
import ca.csf.TP2_Demineur.EventHandler.GameEventHandler;

public class Game {

	private ArrayList<GameEventHandler> gameEventList = new ArrayList<GameEventHandler>();

	private int width;
	private int height;
	private int nbMines;
	private int nbFlags = 0;
	private int nbCellsLeft;
	private boolean isCheating = false;

	private Cell[][] cells;

	public Game(GameEventHandler gameEventHandler) {

		this.newGame(Difficulty.DEBUTANT);
		gameEventList.add(gameEventHandler);
	}

	public void newGame(Difficulty difficulty) {
		// set difficulty and creates a new board

		this.height = difficulty.height();
		this.width = difficulty.width();
		this.nbMines = difficulty.nbMine();
		this.nbFlags = 0;

		cells = new Cell[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				cells[i][j] = new Cell();
			}
		}
		nbCellsLeft = width * height;
		createMine(nbMines);

	}

	private void createMine(int nbMine) {

		int mineLocX = (int) (Math.random() * width);
		int mineLocY = (int) (Math.random() * height);

		if (cells[mineLocX][mineLocY].getIsAMine()) {
			createMine(nbMine);
		} else {
			cells[mineLocX][mineLocY].setIsAMine();
			assignNearbyMineValue(mineLocX, mineLocY);

			// Calls back createMine with the number of mines left
			if (nbMine > 1) {
				createMine(nbMine - 1);
			}
		}

	}

	private void assignNearbyMineValue(int mineLocX, int mineLocY) {
		// Assigns value to minLoc X & Y vars depending on location of the
		// selected cell on the board
		// Lowers the cells being verified accordingly to avoid out of bound
		// errors.
		int minLocX = 1;
		int minLocY = 1;

		if (mineLocX - 1 < 0) {
			minLocX = 0;
		}
		if (mineLocY - 1 < 0) {
			minLocY = 0;
		}

		for (int i = mineLocX - minLocX; i <= mineLocX + 1 && i < width; i++) {
			for (int j = mineLocY - minLocY; j <= mineLocY + 1 && j < height; j++) {
				cells[i][j].addNbMinesNear();
			}
		}
	}

	public void updateRightClick(int cellPosX, int cellPosY) {

		if (!(cells[cellPosX][cellPosY].getIsRevealed())) {
			cells[cellPosX][cellPosY].setCellState();
			if (cells[cellPosX][cellPosY].getCellState() == 1) {
				for (GameEventHandler gameEvent : gameEventList) {
					gameEvent.buttonRightClicked(cellPosX, cellPosY,
							ButtonImage.FLAG);
				}
				nbFlags++;
			} else if (cells[cellPosX][cellPosY].getCellState() == 2) {
				for (GameEventHandler gameEvent : gameEventList) {
					gameEvent.buttonRightClicked(cellPosX, cellPosY,
							ButtonImage.QUESTION_MARK);
				}
				nbFlags--;
			} else {
				for (GameEventHandler gameEvent : gameEventList) {
					gameEvent.buttonRightClicked(cellPosX, cellPosY,
							ButtonImage.EMPTY);
				}

			}

			for (GameEventHandler gameEvent : gameEventList) {
				gameEvent.updateFlag(nbMines - nbFlags);
			}
		}
	}

	public void updateLeftClick(int cellPosX, int cellPosY) {

		if (!(cells[cellPosX][cellPosY].getIsRevealed() || cells[cellPosX][cellPosY]
				.getCellState() == 1)) {
			cells[cellPosX][cellPosY].setRevealed();
			if (cells[cellPosX][cellPosY].getIsAMine()) {
				this.gameOver();
				return;

			} else if (cells[cellPosX][cellPosY].getNbMinesNear() == 0) {
				// Call cells nearby.isRevealed
				// Assigns value to maxLoc X & Y vars depending on location of
				// the selected cell on the board
				// Lowers the cells being verified accordingly to avoid out of
				// bound errors.
				int maxLocX = 1;
				int maxLocY = 1;

				if (cellPosX + 1 == width) {
					maxLocX = 0;
				}
				if (cellPosY + 1 == height) {
					maxLocY = 0;
				}

				for (int i = cellPosX + maxLocX; i >= cellPosX - 1 && i >= 0
						&& i < width; i--) {
					for (int j = cellPosY + maxLocY; j >= cellPosY - 1
							&& j >= 0 && j < height; j--) {
						if (!(cells[i][j].getCellState() == 1)) {
							for (GameEventHandler gameEvent : gameEventList) {
								gameEvent.buttonLeftClick(i, j);
							}
							for (GameEventHandler gameEvent : gameEventList) {
								gameEvent
										.buttonLeftClicked(
												cellPosX,
												cellPosY,
												ButtonImage
														.getTypeFromInt(cells[cellPosX][cellPosY]
																.getNbMinesNear()));
							}
						}
					}
				}

			} else if (cells[cellPosX][cellPosY].getNbMinesNear() > 0) {
				for (GameEventHandler gameEvent : gameEventList) {
					gameEvent.buttonLeftClicked(cellPosX, cellPosY, ButtonImage
							.getTypeFromInt(cells[cellPosX][cellPosY]
									.getNbMinesNear()));
				}

			}
			nbCellsLeft--;
			this.victory();
		}
	}

	public void gameOver() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (cells[i][j].getIsAMine()) {
					if (cells[i][j].getIsRevealed()) {
						for (GameEventHandler gameEvent : gameEventList) {
							gameEvent.buttonLeftClicked(i, j,
									ButtonImage.MINE_RED);
						}

					} else if (cells[i][j].getCellState() == 0) {
						for (GameEventHandler gameEvent : gameEventList) {
							gameEvent.buttonLeftClicked(i, j,
									ButtonImage.MINE_NORMAL);
						}
					}
				}
				if (cells[i][j].getCellState() == 1
						&& !cells[i][j].getIsAMine()) {
					for (GameEventHandler gameEvent : gameEventList) {
						gameEvent
								.buttonLeftClicked(i, j, ButtonImage.MINE_FLAG);
					}
				}
			}
		}

		for (GameEventHandler gameEvent : gameEventList) {
			gameEvent.gameOver();
		}
	}

	public void victory() {
		if (nbCellsLeft == nbMines) {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					if (cells[i][j].getIsAMine()) {
						for (GameEventHandler gameEvent : gameEventList) {
							gameEvent
									.buttonRightClicked(i, j, ButtonImage.FLAG);
						}
					}
				}
			}
			for (GameEventHandler gameEvent : gameEventList) {
				gameEvent.victory();
			}
		}
	}

	public void cheat() {
		if (!isCheating) {
			isCheating = true;
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					if (cells[i][j].getIsAMine()) {
						for (GameEventHandler gameEvent : gameEventList) {
							gameEvent.buttonLeftClicked(i, j,
									ButtonImage.MINE_NORMAL);
						}
					}
				}
			}
		}else {
			isCheating = false;
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					if (cells[i][j].getIsAMine()) {
						for (GameEventHandler gameEvent : gameEventList) {
							gameEvent.buttonLeftClicked(i, j,
									ButtonImage.EMPTY);
						}
					}
				}
			}
		}

	}
}
