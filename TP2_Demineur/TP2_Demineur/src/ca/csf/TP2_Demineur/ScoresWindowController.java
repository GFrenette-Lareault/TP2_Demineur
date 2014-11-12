package ca.csf.TP2_Demineur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ca.csf.simpleFx.SimpleFXController;

public class ScoresWindowController extends SimpleFXController {
	
	@FXML
	private Label lblDebutant;
	@FXML
	private Label lblIntermediaire;
	@FXML
	private Label lblExpert;
	
	@FXML
	private Label lblScoreDebutant;
	@FXML
	private Label lblScoreIntermediaire;
	@FXML
	private Label lblScoreExpert;
	
	@FXML
	private Label lblNomDebutant;
	@FXML
	private Label lblNomIntermediaire;
	@FXML
	private Label lblNomExpert;
	
	private static final String RESSOURCE_PATH = "ressource/";
	
	int numberofLines = 9;
	String[] textScoreLines = new String[numberofLines];
	
	FileReader fileReader;
	BufferedReader textReader; 
	
	public ScoresWindowController() {
		try {
			fileReader = new FileReader(RESSOURCE_PATH + "Scores.txt");
			textReader = new BufferedReader(fileReader);
			for (int i = 0; i < numberofLines; i++) {
					textScoreLines[i] = textReader.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getScoresText() throws IOException {
		lblDebutant.setText(textScoreLines[0]);
		lblScoreDebutant.setText(textScoreLines[1] + "secondes");
		lblNomDebutant.setText(textScoreLines[2]);
		
		lblIntermediaire.setText(textScoreLines[3]);
		lblScoreIntermediaire.setText(textScoreLines[4] + "secondes");
		lblNomIntermediaire.setText(textScoreLines[5]);
		
		lblExpert.setText(textScoreLines[6]);
		lblScoreExpert.setText(textScoreLines[7] + "secondes");
		lblNomExpert.setText(textScoreLines[8]);
		
		textReader.close();
	}
	
	public void checkScore(Difficulty difficulty, int score) throws IOException {
		switch (difficulty) {
		case DEBUTANT:
			if (score > Integer.parseInt(textScoreLines[1])) {
				setHighScore(difficulty, score);
			}	
			break;

		default:
			break;
		}
	}
	
	private void setHighScore(Difficulty difficulty, int newHighScore) throws IOException {
		FileWriter fileWriter = new FileWriter(RESSOURCE_PATH + "Scores.txt");
		PrintWriter lineWriter = new PrintWriter(fileWriter);
		
		lineWriter.printf("%s" + "%n", "Fuck off");
		lineWriter.printf("%s" + "%n", "Fuck off");
		
		lineWriter.close();
	}
	
}