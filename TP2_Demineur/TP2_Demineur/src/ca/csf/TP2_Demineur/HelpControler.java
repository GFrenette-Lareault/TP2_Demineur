package ca.csf.TP2_Demineur;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import ca.csf.simpleFx.SimpleFXController;

public class HelpControler extends SimpleFXController{
	    @FXML 
	    private WebView webViewHelp;

	    @FXML
	    public void okClick(){
	    	
	    }

		@Override
		protected void onLoadedScene() {
			webViewHelp.getEngine().load(getClass().getResource("ressource/help.html").toExternalForm());
		}
}
