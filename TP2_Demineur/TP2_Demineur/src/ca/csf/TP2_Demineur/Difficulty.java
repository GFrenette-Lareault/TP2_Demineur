package ca.csf.TP2_Demineur;

enum Difficulty {
	DEBUTANT(9, 9, 10), INTERMEDIAIRE(16, 16, 40), EXPERT(16, 30, 99);

	private final int height;
	private final int width;
	private final int nbMine;

	private Difficulty(int height, int width, int nbMine) {
		this.height = height;
		this.width = width;
		this.nbMine = nbMine;
	}

	public int height() {
		return height;
	}
	
	public int width() {
		return width;
	}
	
	public int nbMine() {
		return nbMine;
	}
}