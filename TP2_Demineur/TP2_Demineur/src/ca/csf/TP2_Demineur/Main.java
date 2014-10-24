package ca.csf.TP2_Demineur;

import ca.csf.simpleFx.*;
import javafx.stage.StageStyle;

public class Main extends SimpleFXApplication {

	public static void main(String[] args) {
		SimpleFXApplicationLauncher.startSimpleFXApplication(Main.class, args);
	}

	public void start() {
		try {
			SimpleFXScene simpleFXScene = new SimpleFXScene(
					MainWindowController.class.getResource("MainWindow.fxml"),
					MainWindowController.class.getResource("application.css"),
					new MainWindowController());
			SimpleFXStage simpleFXStage = new SimpleFXStage("My Application",
					StageStyle.DECORATED, simpleFXScene, this);
			simpleFXStage.setResizable(false);
			simpleFXStage.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
