package ca.csf.TP2_Demineur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ca.csf.simpleFx.SimpleFXController;
import ca.csf.simpleFx.SimpleFXStage;
import ca.csf.simpleFx.dialogs.SimpleFXDialogChoiceSet;
import ca.csf.simpleFx.dialogs.SimpleFXDialogIcon;
import ca.csf.simpleFx.dialogs.SimpleFXDialogResult;
import ca.csf.simpleFx.dialogs.SimpleFXDialogs;

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
	String textFileText;
	SimpleFXStage stageParent;
	
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
	
	public void checkScore(Difficulty difficulty, int score, SimpleFXStage stage) throws IOException {
		
		stageParent = stage;
		switch (difficulty) {
		case DEBUTANT:
			if (score < Integer.parseInt(textScoreLines[1])) {
				setHighScore(difficulty, score);
			}	
			break;
		case EXPERT:
			break;
		case INTERMEDIAIRE:
			break;
		default:
			break;
			
		}
	}
	
	private void setHighScore(Difficulty difficulty, int newHighScore) throws IOException {
		FileWriter fileWriter = new FileWriter(RESSOURCE_PATH + "Scores.txt");
		BufferedWriter buffWriter = new BufferedWriter(fileWriter);
		String name = SimpleFXDialogs.showInputBox("Nouveau meilleur score!", "Veuillez entrer votre nom : ", stageParent);

		StringBuilder fileContent = new StringBuilder();
		
		switch (difficulty) {
		case DEBUTANT:
			textScoreLines[1] = String.valueOf(newHighScore);
			textScoreLines[2] = name;
			break;
		case INTERMEDIAIRE:
			textScoreLines[4] = String.valueOf(newHighScore);
			textScoreLines[5] = name;
			break;
		case EXPERT:
			textScoreLines[7] = String.valueOf(newHighScore);
			textScoreLines[8] = name;
			break;
		}
		
		fileContent.append(textScoreLines[0] + "\r" + "\n" + 
				textScoreLines[1] + "\r" + "\n" + textScoreLines[2] + "\r" + "\n" + 
				textScoreLines[3] + "\r" + "\n" + textScoreLines[4] + "\r" + "\n" + 
				textScoreLines[5] + "\r" + "\n" + textScoreLines[6] + "\r" + "\n" + 
				textScoreLines[7] + "\r" + "\n" + textScoreLines[8]);
		buffWriter.write(fileContent.toString());
		buffWriter.close();
		SimpleFXDialogs.showMessageBox("Bravo", "Votre nom a été ajouté aux meilleurs score!", SimpleFXDialogIcon.WARNING, SimpleFXDialogChoiceSet.OK, SimpleFXDialogResult.OK, stageParent);
	}
	
}