package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;

public class ClauPublica {

    // Método para generar un par de claves (pública y privada) utilizando el algoritmo RSA
    public KeyPair generaParellClausRSA() throws Exception {
        // Crea un generador de pares de claves usando el algoritmo RSA
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

        // Inicializa el generador para crear claves de 2048 bits (tamaño estándar para seguridad RSA)
        keyPairGen.initialize(2048);

        // Genera el par de claves (contiene la clave pública y la clave privada) y lo devuelve
        return keyPairGen.generateKeyPair();
    }

    // Método para cifrar un mensaje usando una clave pública RSA
    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        // Crea una instancia de Cipher para realizar operaciones de cifrado con RSA
        Cipher cipher = Cipher.getInstance("RSA");

        // Configura el Cipher en modo de cifrado (ENCRYPT_MODE) con la clave pública proporcionada
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);

        // Convierte el mensaje en un array de bytes y lo cifra usando el Cipher configurado
        // Devuelve el mensaje cifrado como un array de bytes
        return cipher.doFinal(msg.getBytes("UTF-8"));
    }

    // Método para descifrar un mensaje cifrado usando una clave privada RSA
    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception {
        // Crea una instancia de Cipher para realizar operaciones de descifrado con RSA
        Cipher cipher = Cipher.getInstance("RSA");

        // Configura el Cipher en modo de descifrado (DECRYPT_MODE) con la clave privada proporcionada
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada);

        // Descifra el mensaje cifrado (array de bytes) y convierte el resultado en un array de bytes
        byte[] decryptedBytes = cipher.doFinal(msgXifrat);

        // Convierte los bytes descifrados en un String usando UTF-8 y lo devuelve
        return new String(decryptedBytes, "UTF-8");
    }
}
