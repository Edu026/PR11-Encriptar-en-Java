package cat.iesesteveterradas;
import java.security.*;
import java.io.*;

public class RSAKeyGenerator {
    public static void main(String[] args) throws Exception {
        // Genera un par de claves RSA de 2048 bits
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        // Guarda la clave privada en un archivo
        try (FileOutputStream fos = new FileOutputStream("private_key.der")) {
            fos.write(keyPair.getPrivate().getEncoded());
        }

        // Guarda la clave pública en un archivo
        try (FileOutputStream fos = new FileOutputStream("public_key.der")) {
            fos.write(keyPair.getPublic().getEncoded());
        }
    }
}
