package cat.iesesteveterradas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.PrivateKey;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;

public class DecryptorUtils {

    public static void decrypt(String privateKeyPath, String inputFile, String outputFile) {

        try {
            // Lee la clave privada
            FileInputStream fis = new FileInputStream(privateKeyPath);
            byte[] privateKeyBytes = new byte[fis.available()];
            fis.read(privateKeyBytes);
            fis.close();

            // Inicializa Cipher con la clave privada
            PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            // Lee el archivo encriptado
            FileInputStream inFile = new FileInputStream(inputFile);
            byte[] encryptedBytes = new byte[(int) inputFile.length()];
            inFile.read(encryptedBytes);
            inFile.close();

            // Desencripta el archivo
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            // Escribe el archivo desencriptado
            FileOutputStream outFile = new FileOutputStream(outputFile);
            outFile.write(cipher.doFinal(decryptedBytes,));
            outFile.close();

            System.out.println("¡Archivo desencriptado exitosamente!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

