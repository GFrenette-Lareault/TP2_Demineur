package ca.csf.TP2_Demineur;

public class Cell {
	
	private int nbMinesNear = 0;
	private int cellState = 0;
	private boolean isAMine =  false;
	private boolean isRevealed = false;
	
	
	public void setIsAMine() {
		this.isAMine = true;
	}

	public boolean getIsAMine() {
		return isAMine;
	}

	public void addNbMinesNear() {
		this.nbMinesNear++;
	}
	
	public int getNbMinesNear(){
		return this.nbMinesNear;
	}
	
	public int getCellState(){
		return this.cellState;
	}
	
	public void setCellState(){
		if(this.cellState >= 2){
			this.cellState = 0;
		} else {
			this.cellState++;
		}
	}
	
	public void setRevealed() {
		this.isRevealed = true;
	}

	public boolean getIsRevealed() {
		return isRevealed;
	}
	
	
}
