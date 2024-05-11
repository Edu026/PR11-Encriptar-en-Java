package cat.iesesteveterradas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerMainView {

    @FXML
    private Button btn_go_encrypt, btn_go_descrypt;

    @FXML
    public void animateToEncryptView(ActionEvent event){
        UtilsViews.setViewAnimating("EncryptView");
    }
    @FXML
    public void animateToDescryptView(ActionEvent event){
        UtilsViews.setViewAnimating("DecryptView");
    }

    
}