package ca.csf.TP2_Demineur;

public class Cell {
	
	private int nbMinesNear = 0;
	private boolean isAMine =  false;
	
	public void setAMine() {
		this.isAMine = true;
	}

	public boolean isAMine() {
		return isAMine;
	}

	public void addNbMinesNear() {
		this.nbMinesNear++;
	}
	
	
}
