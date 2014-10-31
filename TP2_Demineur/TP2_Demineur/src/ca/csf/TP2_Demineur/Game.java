package ca.csf.TP2_Demineur;

import java.lang.Math;
import java.util.ArrayList;

public class Game{

	private ArrayList<GameEventHandler> gameEventList = new ArrayList<GameEventHandler>();
	
	public Game(GameEventHandler gameEventHandler) {
		
		this.newGame(Difficulty.DEBUTANT);
		gameEventList.add(gameEventHandler);
	}
	
	private int height;
	private int width;
	private int nbMines;
	private int nbFlags = 0;
	private int nbCellsLeft;
	
	private Cell[][] cells;
	
	public void newGame(Difficulty difficulty){
		
		this.width = difficulty.width();
		this.height = difficulty.height();
		this.nbMines = difficulty.nbMine();
		
		cells = new Cell[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cells[i][j] = new Cell();
			}
		}
		nbCellsLeft = height * width;
		createMine(nbMines);
		
	}
	
	private void createMine(int nbMine) {
		int mineLocX = (int) (Math.random() * height);
		int mineLocY = (int) (Math.random() * width);
		
		if(cells[mineLocX][mineLocY].getIsAMine()){
			createMine(nbMine);
		} else {
			cells[mineLocX][mineLocY].setIsAMine();
			assignNearbyMineValue(mineLocX, mineLocY);
			
			if(nbMine > 0){
				createMine(nbMine - 1);
			}
		}
		
	}
	
	private void assignNearbyMineValue(int mineLocX, int mineLocY){
		for(int i = mineLocX + 1; i < mineLocX - 1 || i < 0; i--){
			for (int j = mineLocY + 1; j < mineLocY - 1 || j < 0; j--){
				if(j < width){
					if(i < height){
						cells[i][j].addNbMinesNear();
					}
				}
			}
		}
		
	}
	
	public void updateRightClick(int cellPosX, int cellPosY){
		
		cells[cellPosX][cellPosY].setCellState();
		if(cells[cellPosX][cellPosY].getCellState() == 1){
			nbFlags++;
		} else if(cells[cellPosX][cellPosY].getCellState() == 2){
			nbFlags--;
		}
		
		//call cossin a francois avec cell[][].currentCellState
		//Update Flag #. =>=>=>
	}
	
	public void updateLeftClick(int cellPosX, int cellPosY){
		
		if(!(cells[cellPosX][cellPosY].getIsRevealed() || 
				cells[cellPosX][cellPosY].getCellState() >= 1)){
			if(cells[cellPosX][cellPosY].getIsAMine()){
				this.gameOver();
				return;
				
			} else if(cells[cellPosX][cellPosY].getNbMinesNear() == 0){
				//call cells nearby.isRevealed
				for(int i = cellPosX + 1; i < cellPosX - 1 || i < 0; i--){
					for (int j = cellPosY + 1; j < cellPosY - 1 || j < 0; j--){
						if(j < width){
							if(i < height){
								this.updateLeftClick(i, j);
							}
						}
					}
				}
				
			} else {
				//show number of mines nearby i.e. image de 8
				//number mines vs number of cells left. if 0 then win.
			}
			nbCellsLeft--;
			this.victory();
		}
	}
	
	public void gameOver(){
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				if(cells[i][j].getIsAMine()){
					if(cells[i][j].getCellState() == 1){
						//button image = flag + mine
					} else {
						//button image = mine
					}
				}
			}
		}
		
		//main game button image = loss
	}
	
	public void victory(){
		if(nbCellsLeft == nbMines){
			for(int i = 0; i < height; i++){
				for(int j = 0; j < width; j++){
					if(cells[i][j].getIsAMine()){
						//button image flag
					}
				}
			}
			//Victory button image = sunshine smiley of OP
		}
	}
}
