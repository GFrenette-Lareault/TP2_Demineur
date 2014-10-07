package ca.csf.TP2_Demineur;

import javafx.fxml.FXML;
import ca.csf.simpleFx.SimpleFXController;

public class MainWindowController extends SimpleFXController {

	private Game game;

	public MainWindowController() {
		game = new Game();
	}

	public void UpdateGame(){
		//rederer(game.)
	}
	
	@FXML
	public void NewGame(){
		
	}
}
