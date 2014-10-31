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

	private Cell[][] cells;
	
	public Game(GameEventHandler gameEventHandler) {

		this.newGame(Difficulty.DEBUTANT);
		gameEventList.add(gameEventHandler);
	}

	public void newGame(Difficulty difficulty) {

		//set difficulty and creates a new board
		this.height = difficulty.height();
		this.width = difficulty.width();
		this.nbMines = difficulty.nbMine();

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

			if (nbMine > 0) {
				createMine(nbMine - 1);
			}
		}

	}

	private void assignNearbyMineValue(int mineLocX, int mineLocY) {
		for (int i = mineLocX + 1; i < mineLocX - 1 || i < 0; i--) {
			for (int j = mineLocY + 1; j < mineLocY - 1 || j < 0; j--) {
				if (j < height) {
					if (i < width) {
						cells[i][j].addNbMinesNear();
					}
				}
			}
		}

	}

	public void updateRightClick(int cellPosX, int cellPosY){
		
		if(!(cells[cellPosX][cellPosY].getIsRevealed())){
			cells[cellPosX][cellPosY].setCellState();
			if(cells[cellPosX][cellPosY].getCellState() == 1){
				for (GameEventHandler gameEvent:gameEventList){
					gameEvent.buttonRightClicked(cellPosX, cellPosY, ButtonImage.FLAG);
				}
				nbFlags++;
			} else if(cells[cellPosX][cellPosY].getCellState() == 2){
				for (GameEventHandler gameEvent:gameEventList){
					gameEvent.buttonRightClicked(cellPosX, cellPosY, ButtonImage.QUESTION_MARK);
				}
				nbFlags--;
			} else {
				for (GameEventHandler gameEvent:gameEventList){
					gameEvent.buttonRightClicked(cellPosX, cellPosY, ButtonImage.EMPTY);
				}
				
			}
		
			for (GameEventHandler gameEvent:gameEventList){
				gameEvent.updateFlag(nbFlags);
			}
		}
	}

	public void updateLeftClick(int cellPosX, int cellPosY) {

		if (!(cells[cellPosX][cellPosY].getIsRevealed() || cells[cellPosX][cellPosY]
				.getCellState() == 1)) {
			if (cells[cellPosX][cellPosY].getIsAMine()) {
				this.gameOver();
				return;

			} else if (cells[cellPosX][cellPosY].getNbMinesNear() == 0) {
				// call cells nearby.isRevealed 
				for (int i = cellPosX + 1; i < cellPosX - 1 || i < 0; i--) {
					for (int j = cellPosY + 1; j < cellPosY - 1 || j < 0; j--) {
						if (j < height) {
							if (i < width) {
								this.updateLeftClick(i, j);
							}
						}
					}
				}

			} else {
				// show number of mines nearby i.e. image de 8
				// number mines vs number of cells left. if 0 then win.
			}
			nbCellsLeft--;
			this.victory();
		}
	}

	public void gameOver() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (cells[i][j].getIsAMine()) {
					if (cells[i][j].getCellState() == 1) {
						for (GameEventHandler gameEvent:gameEventList){
							gameEvent.buttonRightClicked(i, j, ButtonImage.MINE_FLAG);
						}
					} else {
						for (GameEventHandler gameEvent:gameEventList){
							gameEvent.buttonRightClicked(i, j, ButtonImage.MINE_NORMAL);
						}
					}
				}
			}
		}
		

		for (GameEventHandler gameEvent:gameEventList){
			gameEvent.gameOver();
		}
	}

	public void victory() {
		if (nbCellsLeft == nbMines) {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					if (cells[i][j].getIsAMine()) {
						for (GameEventHandler gameEvent:gameEventList){
							gameEvent.buttonRightClicked(i, j, ButtonImage.FLAG);
						}
					}
				}
			}
			for (GameEventHandler gameEvent:gameEventList){
				gameEvent.victory();
			}
		}
	}
}
