import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    // Variable de clase para contar contraseñas probadas
    private int npass = 0;

    // Variable para almacenar la contraseña encontrada
    private String passwordFound = null;

    public String getSHA512AmbSalt(String pw, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());
            byte[] hash = md.digest(pw.getBytes());
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar el hash SHA-512", e);
        }
    }

    public String getPBKDF2AmbSalt(String pw, String salt) {
        try {
            byte[] saltBytes = salt.getBytes();
            int iteraciones = 5000; 
            PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), saltBytes, iteraciones, 256);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return HexFormat.of().formatHex(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el hash PBKDF2", e);
        }
    }

    public String forcaBruta(String alg, String hash, String salt) {
        String charset = "abcdefABCDEF1234567890!";
        char[] attempt = new char[6]; // Contraseña máxima de longitud 6
        npass = 0;
        passwordFound = null; // Reinicia la variable para cada ejecución

        for (int length = 1; length <= 6; length++) { // Probar contraseñas de 1 a 6 caracteres
            if (generateCombinations(0, length, charset, attempt, alg, hash, salt)) {
                return passwordFound; // Retorna la contraseña encontrada
            }
        }
        return null; // Contraseña no encontrada
    }

    private boolean generateCombinations(int position, int length, String charset, char[] attempt, String alg, String hash, String salt) {
        if (position == length) {
            String attemptStr = new String(attempt, 0, length);
            npass++;
            String generatedHash = alg.equals("SHA-512")
                    ? getSHA512AmbSalt(attemptStr, salt)
                    : getPBKDF2AmbSalt(attemptStr, salt);

            if (generatedHash.equals(hash)) {
                passwordFound = attemptStr; // Guarda la contraseña encontrada
                return true; // Detener fuerza bruta
            }
            return false;
        }

        for (int i = 0; i < charset.length(); i++) {
            attempt[position] = charset.charAt(i);
            if (generateCombinations(position + 1, length, charset, attempt, alg, hash, salt)) {
                return true; // Detener si se encontró
            }
        }
        return false;
    }

    public String getInterval(long t1, long t2) {
        long diff = t2 - t1;
        long millis = diff % 1000;
        long seconds = (diff / 1000) % 60;
        long minutes = (diff / (1000 * 60)) % 60;
        long hours = (diff / (1000 * 60 * 60)) % 24;
        long days = diff / (1000 * 60 * 60 * 24);
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", days, hours, minutes, seconds, millis);
    }

    public static void main(String[] args) {
        try {
            String salt = "qpoweiruañslkdfjz";
            String pw = "aaabF!";
            Hashes h = new Hashes();

            String[] aHashes = { h.getSHA512AmbSalt(pw, salt), h.getPBKDF2AmbSalt(pw, salt) };
            String pwTrobat = null;
            String[] algorismes = { "SHA-512", "PBKDF2" };

            for (int i = 0; i < aHashes.length; i++) {
                System.out.printf("===========================\n");
                System.out.printf("Algorisme: %s\n", algorismes[i]);
                System.out.printf("Hash: %s\n", aHashes[i]);
                System.out.printf("---------------------------\n");
                System.out.printf("-- Inici de força bruta ---\n");
                long t1 = System.currentTimeMillis();
                pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
                long t2 = System.currentTimeMillis();
                System.out.printf("Pass   : %s\n", pwTrobat);
                System.out.printf("Provats: %d\n", h.npass);
                System.out.printf("Temps  : %s\n", h.getInterval(t1, t2));
                System.out.printf("---------------------------\n\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
