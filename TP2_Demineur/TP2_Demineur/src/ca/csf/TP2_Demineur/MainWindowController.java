package ca.csf.TP2_Demineur;

import ca.csf.simpleFx.SimpleFXController;

public class MainWindowController extends SimpleFXController{
	
	private Game game;
	private Difficulte diffuculte;
	
	public MainWindowController(){
		game = new Game();
		diffuculte= Difficulte.DEBUTANT;
	}
	
}
