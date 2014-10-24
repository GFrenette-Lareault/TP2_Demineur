package ca.csf.TP2_Demineur;

import ca.csf.simpleFx.*;
import javafx.stage.StageStyle;

public class Main extends SimpleFXApplication {

	public static void main(String[] args) {
		SimpleFXApplicationLauncher.startSimpleFXApplication(Main.class, args);
	}

	public void start() {
		try {
			MainWindowController controller;
			SimpleFXScene simpleFXScene = new SimpleFXScene(
					MainWindowController.class.getResource("MainWindow.fxml"),
					MainWindowController.class.getResource("application.css"),
					controller = new MainWindowController());
			SimpleFXStage simpleFXStage = new SimpleFXStage("My Application",
					StageStyle.DECORATED, simpleFXScene, this);
			simpleFXStage.setResizable(false);
			controller.newGame();
			simpleFXStage.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
