package cat.iesesteveterradas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerDecryptView {
    
    @FXML
    Button btn_to_MainView;

    @FXML
    public void animateToMainView(ActionEvent event){
        UtilsViews.setViewAnimating("MainView");
    }
}
