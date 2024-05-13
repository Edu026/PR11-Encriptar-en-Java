package cat.iesesteveterradas;

import java.io.File;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class ControllerDecryptView {

    static String privateKeyFile = "";
    static String publicKeyFile = "/home/super/PR11-Encriptar-en-Java/public_key.der"; 
    static String inputFile = ""; 
    static String outputFile = "/home/super/PR11-Encriptar-en-Java/src/main/resources/assets/"; 
    
    @FXML
    Button btn_to_MainView;

    @FXML
    TextField txt_private_key = new TextField();
    @FXML
    TextField txt_arxiu = new TextField();
    @FXML
    TextField txt_desti = new TextField();

    @FXML
    public void animateToMainView(ActionEvent event){
        UtilsViews.setViewAnimating("MainView");
    }

    @FXML
    public void initialize() {
        // Agregar eventos de clic a los TextFields
        txt_private_key.setOnMouseClicked(event -> selectFile(txt_private_key));
        txt_arxiu.setOnMouseClicked(event -> selectFile(txt_arxiu));
        //txt_desti.setOnMouseClicked(event -> selectDirectory(txt_desti));
    }

    @FXML
    public void decryptFile(ActionEvent event) {
        outputFile = outputFile + txt_desti.getText();
        System.out.println(System.getProperty("user.dir"));

        
        try {

            DecryptorUtils.decrypt(privateKeyFile,inputFile,outputFile);
            
            System.out.println("Archivo desencriptado con éxito.");
            outputFile = "/home/super/PR11-Encriptar-en-Java/src/main/resources/assets/";
            showSuccessDialog();
        } catch (Exception e) {
            System.out.println("Error al desencriptar el archivo: " + e.getMessage());
            e.printStackTrace();
            showErrorDialog(inputFile);
        }
    }

    @FXML
    public void selectFile(TextField textField) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        
        System.out.println(textField);
        // Mostrar el diálogo de selección de archivo
        File selectedFile = fileChooser.showOpenDialog(null);
        
        // Si el usuario selecciona un archivo, obtener el nombre del archivo
        if (selectedFile != null) {
            String fileName = selectedFile.getName();
            String selectedFilePath = selectedFile.getPath();
            System.out.println("Archivo seleccionado: " + fileName);
            System.out.println("Text Field Id : " + textField.getId());


            if (textField.getId().equals("txt_private_key")){
                privateKeyFile = selectedFilePath;
            }
            else{
                inputFile = selectedFilePath;
            }
            // Actualizar el TextField correspondiente con el nombre del archivo seleccionado
            Platform.runLater(() -> textField.setText(fileName));
        }

    }

        private void showSuccessDialog() {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Éxito");
    alert.setHeaderText(null);
    alert.setContentText("El archivo se ha desencriptado con éxito.");

    alert.showAndWait();
}

    private void showErrorDialog(String errorMessage) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Error al desencriptar el archivo: " + errorMessage);

        alert.showAndWait();
    }
}
