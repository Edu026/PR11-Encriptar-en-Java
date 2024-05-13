package cat.iesesteveterradas;

import java.io.File;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControllerEncryptView {

    static String publicKeyFile = "";
    static String inputFile = ""; 
    static String outputFile = "/home/super/PR11-Encriptar-en-Java/src/main/resources/assets/"; 

    String fileName;
    String filePath;

    @FXML
    Button btn_to_MainView;
    @FXML
    TextField txt_public_Key = new TextField();
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
        txt_public_Key.setOnMouseClicked(event -> selectFile(txt_public_Key));
        txt_arxiu.setOnMouseClicked(event -> selectFile(txt_arxiu));
        //txt_desti.setOnMouseClicked(event -> selectDirectory(txt_desti));
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
            if (textField.getId().equals("txt_public_Key")){
                publicKeyFile = selectedFilePath;
            }
            else{
                inputFile = selectedFilePath;
            }
            // Actualizar el TextField correspondiente con el nombre del archivo seleccionado
            Platform.runLater(() -> textField.setText(fileName));
        }

    }
/*     @FXML
     public void selectDirectory(TextField textField) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Seleccionar Directorio de Destino");

        // Obtener la ventana principal para que el diálogo de selección de directorio sea modal
        Stage stage = (Stage) txt_desti.getScene().getWindow();

        // Mostrar el diálogo de selección de directorio
        File selectedDirectory = directoryChooser.showDialog(stage);

        // Si el usuario selecciona un directorio, actualizar el campo de texto de destino
        if (selectedDirectory != null) {
            String directoryPath = selectedDirectory.getAbsolutePath();
            System.out.println("Directorio seleccionado: " + directoryPath);
            Platform.runLater(() -> textField.setText(directoryPath));
        }
    } */

    
    @FXML
    public void encryptFile(ActionEvent event) {

        outputFile = outputFile + txt_desti.getText();
        System.out.println(System.getProperty("user.dir"));
        // Obtener la ruta del archivo de clave pública
        // String publicKeyFile = txt_public_Key.getText();
        
        // Verificar si se ha seleccionado un archivo
        if (publicKeyFile.isEmpty()) {
            System.out.println("Por favor selecciona un archivo de clave pública.");
            return;
        }
        
        // Si se ha seleccionado un archivo, continuar con la encriptación
        try {
            // Establecer las rutas de entrada y salida para la encriptación
         //   String inputFile = filePath; // Utiliza la ruta del archivo seleccionado
         //   String outputFile = "ruta/de/salida/archivo_encriptado"; // Define la ruta de salida para el archivo encriptado
            
            // Ejecutar la encriptación del archivo utilizando la clase FileEncryptor
            EncryptorUtils.encrypt(publicKeyFile,inputFile,outputFile);
            
            System.out.println("Archivo encriptado con éxito.");
            outputFile = "/home/super/PR11-Encriptar-en-Java/src/main/resources/assets/";

            showSuccessDialog();
        } catch (Exception e) {
            System.out.println("Error al encriptar el archivo: " + e.getMessage());
            e.printStackTrace();
            showErrorDialog(fileName);
        }
    }

    private void showSuccessDialog() {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Éxito");
    alert.setHeaderText(null);
    alert.setContentText("El archivo se ha encriptado con éxito.");

    alert.showAndWait();
}

    private void showErrorDialog(String errorMessage) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Error al encriptar el archivo: " + errorMessage);

        alert.showAndWait();
    }

    
}
