package iticbcn.xifratge;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

public class XifradorAES implements Xifrador {
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String ALGORITHM = "AES";

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            SecretKeySpec key = generarClave(clau);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            
            // Generar IV aleatorio de 16 bytes
            byte[] iv = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
            byte[] encrypted = cipher.doFinal(msg.getBytes(StandardCharsets.UTF_8));
            
            // Combinar IV y texto cifrado
            byte[] ivAndEncrypted = new byte[iv.length + encrypted.length];
            System.arraycopy(iv, 0, ivAndEncrypted, 0, iv.length);
            System.arraycopy(encrypted, 0, ivAndEncrypted, iv.length, encrypted.length);
            
            return new TextXifrat(ivAndEncrypted);
        } catch (Exception e) {
            System.err.println("Error en el cifrado AES: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            SecretKeySpec key = generarClave(clau);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            
            // Separar IV y texto cifrado
            byte[] ivAndEncrypted = xifrat.getBytes();
            byte[] iv = Arrays.copyOfRange(ivAndEncrypted, 0, 16);
            byte[] encrypted = Arrays.copyOfRange(ivAndEncrypted, 16, ivAndEncrypted.length);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            byte[] decrypted = cipher.doFinal(encrypted);
            
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.err.println("Error en el descifrado AES: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }

    private SecretKeySpec generarClave(String clau) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = digest.digest(clau.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(Arrays.copyOf(keyBytes, 16), ALGORITHM); // Clave de 16 bytes
    }
}