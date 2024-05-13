package cat.iesesteveterradas;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class ControllerEncryptView {

    static String publicKeyFile = "C:\\Users\\PC\\Documents\\GitHub\\PR11-Encriptar-en-Java\\src\\main\\resources\\assets\\public_key.der";
    static String inputFile = "C:\\Users\\PC\\Documents\\GitHub\\PR11-Encriptar-en-Java\\src\\main\\resources\\assets\\exercici1.pdf"; 
    static String outputFile = "C:\\Users\\PC\\Documents\\GitHub\\PR11-Encriptar-en-Java\\src\\main\\resources\\assets\\encrypted_exercici1"; 

    String filePath;

    @FXML
    Button btn_to_MainView;
    TextField txt_public_Key = new TextField();

    @FXML
    public void animateToMainView(ActionEvent event){
        UtilsViews.setViewAnimating("MainView");
    }
    
    @FXML
    public void selectFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        
        // Mostrar el diálogo de selección de archivo
        File selectedFile = fileChooser.showOpenDialog(null);
        
        // Si el usuario selecciona un archivo, obtener la ruta del archivo
        if (selectedFile != null) {
            filePath = selectedFile.getAbsolutePath();
            System.out.println("Archivo seleccionado: " + filePath);
            txt_public_Key.setText(filePath); // Actualiza el TextField con la ruta del archivo seleccionado
        }
    }
    
    @FXML
    public void encryptFile(ActionEvent event) {
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
        } catch (Exception e) {
            System.out.println("Error al encriptar el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
