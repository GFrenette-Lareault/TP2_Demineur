package ca.csf.TP2_Demineur;

import java.lang.Math;

public class Game {

	public Game() {
		this.newGame(Difficulty.DEBUTANT);
	}
	
	private int height;
	private int width;
	private int nbMines;
	private int nbFlags = 0;
	private int nbCellsLeft;
	
	private Cell[][] cell;
	
	public void newGame(Difficulty difficulty){
		
		this.width = difficulty.width();
		this.height = difficulty.height();
		this.nbMines = difficulty.nbMine();
		
		cell = new Cell[height][width];
		nbCellsLeft = height * width;
		createMine(nbMines);
		
	}
	
	private void createMine(int nbMine) {
		int mineLocX = (int) (Math.random() * height);
		int mineLocY = (int) (Math.random() * width);
		
		if(cell[mineLocX][mineLocY].getIsAMine()){
			createMine(nbMine);
		} else {
			cell[mineLocX][mineLocY].setIsAMine();
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
						cell[mineLocX][mineLocY].addNbMinesNear();
					}
				}
			}
		}
		
	}
	
	private void updateRightClick(int cellPosX, int cellPosY){
		
		cell[cellPosX][cellPosY].setCellState();
		if(cell[cellPosX][cellPosY].getCellState() == 1){
			nbFlags++;
		} else if(cell[cellPosX][cellPosY].getCellState() == 2){
			nbFlags--;
		}
		
		//call cossin a francois avec cell[][].currentCellState
		//Update Flag #.
	}
	
	private void updateLeftClick(int cellPosX, int cellPosY){
		
		if(!(cell[cellPosX][cellPosY].getIsRevealed() || 
				cell[cellPosX][cellPosY].getCellState() == 1)){
			if(cell[cellPosX][cellPosY].getIsAMine()){
				//gameover, reveal mines, change image button
				return;
				
			} else if(cell[cellPosX][cellPosY].getNbMinesNear() == 0){
				//call cells nearby.isRevealed
				//boucle infinie
				
			} else {
				//show number of mines nearby i.e. image de 8
				//number mines vs number of cells left. if 0 then win.
			}
			nbCellsLeft--;
		}
	}
	
	private void gameOver(){
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				if(cell[i][j].getIsAMine()){
					if(cell[i][j].getCellState() == 1){
						//button image = flag + mine
					} else {
						//button image = mine
					}
				}
			}
		}
		
		//main game button image = loss
	}
	
	private void victory(){
		if(nbCellsLeft == nbMines){
			for(int i = 0; i < height; i++){
				for(int j = 0; j < width; j++){
					if(cell[i][j].getIsAMine()){
						//button image flag
					}
				}
			}
			//Victory button image = sunshine smiley of OP
		}
	}
}
