package cat.iesesteveterradas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerDecryptView {

    static String privateKeyFile = "C:\\Users\\PC\\Documents\\GitHub\\PR11-Encriptar-en-Java\\src\\main\\resources\\assets\\private_key.der";
    static String publicKeyFile = "C:\\Users\\PC\\Documents\\GitHub\\PR11-Encriptar-en-Java\\src\\main\\resources\\assets\\public_key.der"; 
    static String inputFile = "C:\\Users\\PC\\Documents\\GitHub\\PR11-Encriptar-en-Java\\src\\main\\resources\\assets\\encrypted_exercici1"; 
    static String outputFile = "C:\\Users\\PC\\Documents\\GitHub\\PR11-Encriptar-en-Java\\src\\main\\resources\\assets\\decriptedFile"; 
    
    @FXML
    Button btn_to_MainView;

    @FXML
    public void animateToMainView(ActionEvent event){
        UtilsViews.setViewAnimating("MainView");
    }

    @FXML
    public void decryptFile(ActionEvent event) {
        System.out.println(System.getProperty("user.dir"));

        if (publicKeyFile.isEmpty()) {
            System.out.println("Por favor selecciona un archivo de clave pública.");
            return;
        }
        
        try {

            DecryptorUtils.decrypt(privateKeyFile,inputFile,outputFile);
            
            System.out.println("Archivo desencriptado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al desencriptar el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
