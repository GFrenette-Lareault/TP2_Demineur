package ca.csf.TP2_Demineur;

import java.lang.Math;

public class Game {

	public Game() {
		
	}
	
	private int height;
	private int width;
	private int nbMine;
	private Cell[][] cell;
	
	public void newGame(Difficulty difficulty){
		
		cell = new Cell[height][width];
	}
	
	private void createMine(int nbMine) {
		int mineLocX = (int) (Math.random() * height);
		int mineLocY = (int) (Math.random() * width);
		
		if(cell[mineLocX][mineLocY].isAMine()){
			createMine(nbMine);
		} else {
			cell[mineLocX][mineLocY].setAMine();
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
}
