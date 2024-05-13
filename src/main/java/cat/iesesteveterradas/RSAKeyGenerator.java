package cat.iesesteveterradas;
import java.security.*;
import java.io.*;

public class RSAKeyGenerator {
    public static void main(String[] args) throws Exception {
        // Genera un par de claves RSA de 2048 bits
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024, random);
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
