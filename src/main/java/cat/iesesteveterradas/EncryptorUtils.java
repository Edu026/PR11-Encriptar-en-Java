package cat.iesesteveterradas;

import java.io.*;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.*;

public class EncryptorUtils {
 

    public static void encrypt(String publicKeyPath, String inputFile, String outputFile){

        try {
            // Lee la clave pública
            FileInputStream fis = new FileInputStream(publicKeyPath);
            byte[] publicKeyBytes = new byte[fis.available()];
            fis.read(publicKeyBytes);
            fis.close();

            // Inicializa Cipher con la clave pública
            PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyBytes));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            // Lee el archivo a encriptar
            FileInputStream inFile = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inFile.read(inputBytes);
            inFile.close();

            // Encripta el archivo
            byte[] encryptedBytes = cipher.doFinal(inputBytes);

            // Escribe el archivo encriptado
            FileOutputStream outFile = new FileOutputStream(outputFile);
            outFile.write(encryptedBytes);
            outFile.close();

            System.out.println("¡Archivo encriptado exitosamente!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
