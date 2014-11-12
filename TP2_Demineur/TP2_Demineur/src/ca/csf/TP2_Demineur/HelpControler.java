package ca.csf.TP2_Demineur;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import ca.csf.simpleFx.SimpleFXController;

public class HelpControler extends SimpleFXController{
	    @FXML 
	    private WebView webViewHelp;

	    @FXML
	    public void okClick(){
	    	getSimpleFxStage().close();
	    }
		@Override
		protected void onLoadedStage() {
			webViewHelp.getEngine().load(getClass().getResource("help.html").toExternalForm());
		}
}
