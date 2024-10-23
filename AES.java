/*
 * Autor: Jairo Vigil
 * M09-A05, DAM2A
 * Fecha: 23/10/2024
 * Comentarios generados por IA
 */

 import javax.crypto.Cipher;
 import javax.crypto.spec.IvParameterSpec;
 import javax.crypto.spec.SecretKeySpec;
 import java.security.MessageDigest;
 import java.security.SecureRandom;
 import java.util.Arrays;
 import java.util.Base64;
 
 public class AES {
 
     // Definiciones a nivel de clase
     public static final String ALGORITMO_CIFRADO = "AES";
     public static final String ALGORITMO_HASH = "SHA-256";
     public static final String FORMATO_AES = "AES/CBC/PKCS5Padding"; 
     private static final int TAMANO_IV = 16;
     private static final String CLAVE_SECRETA = "daniestep"; 
 
     public static String cifrarAES(String mensaje, String clave) throws Exception {
         // Obtener los bytes del mensaje a cifrar
         byte[] mensajeBytes = mensaje.getBytes();
 
         // Generar un IV (vector de inicialización) seguro de forma aleatoria
         byte[] iv = new byte[TAMANO_IV]; // Crear un array de bytes para el IV
         SecureRandom random = new SecureRandom();
         random.nextBytes(iv); // Llenamos el array 'iv' con valores aleatorios
         IvParameterSpec ivSpec = new IvParameterSpec(iv); // Creamos el IV para el cifrado
 
         // Generar un hash SHA-256 a partir de la clave
         MessageDigest sha = MessageDigest.getInstance(ALGORITMO_HASH);
         byte[] claveBytes = sha.digest(clave.getBytes("UTF-8")); // Convertir la clave en un hash
         claveBytes = Arrays.copyOf(claveBytes, 32); // Aseguramos que el tamaño de la clave sea de 256 bits
         SecretKeySpec keySpec = new SecretKeySpec(claveBytes, ALGORITMO_CIFRADO); // Creamos la clave para el cifrado
 
         // Configurar el cifrador para AES en modo CBC con relleno PKCS5
         Cipher cipher = Cipher.getInstance(FORMATO_AES);
         cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec); // Inicializamos el cifrador
 
         // Cifrar el mensaje
         byte[] mensajeCifrado = cipher.doFinal(mensajeBytes);
 
         // Concatenar el IV y el mensaje cifrado en un solo array de bytes
         byte[] ivYMensajeCifrado = new byte[TAMANO_IV + mensajeCifrado.length];
         System.arraycopy(iv, 0, ivYMensajeCifrado, 0, TAMANO_IV); // Insertamos el IV al inicio
         System.arraycopy(mensajeCifrado, 0, ivYMensajeCifrado, TAMANO_IV, mensajeCifrado.length); // Insertamos el mensaje cifrado
 
         // Retornar el mensaje cifrado en formato Base64 para facilitar su lectura
         return Base64.getEncoder().encodeToString(ivYMensajeCifrado);
     }

     public static String descifrarAES(String mensajeCifradoBase64, String clave) throws Exception {
         // Decodificar el mensaje desde Base64 a un array de bytes
         byte[] mensajeCifradoBytes = Base64.getDecoder().decode(mensajeCifradoBase64);
 
         // Extraer el IV del mensaje cifrado (primeros 16 bytes)
         byte[] iv = new byte[TAMANO_IV]; // Crear un array de bytes para el IV
         System.arraycopy(mensajeCifradoBytes, 0, iv, 0, TAMANO_IV); // Extraemos el IV
         IvParameterSpec ivSpec = new IvParameterSpec(iv); // Creamos el IV para el descifrado
 
         // Extraer la parte cifrada del mensaje (el resto después de los primeros 16 bytes)
         byte[] mensajeCifrado = new byte[mensajeCifradoBytes.length - TAMANO_IV];
         System.arraycopy(mensajeCifradoBytes, TAMANO_IV, mensajeCifrado, 0, mensajeCifrado.length);
 
         // Generar un hash SHA-256 a partir de la clave
         MessageDigest sha = MessageDigest.getInstance(ALGORITMO_HASH);
         byte[] claveBytes = sha.digest(clave.getBytes("UTF-8"));
         claveBytes = Arrays.copyOf(claveBytes, 32); // Aseguramos que el tamaño de la clave sea de 256 bits
         SecretKeySpec keySpec = new SecretKeySpec(claveBytes, ALGORITMO_CIFRADO); // Creamos la clave para el descifrado
 
         // Configurar el descifrador para AES en modo CBC con relleno PKCS5
         Cipher cipher = Cipher.getInstance(FORMATO_AES);
         cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec); // Inicializamos el descifrador
 
         // Descifrar el mensaje
         byte[] mensajeDescifrado = cipher.doFinal(mensajeCifrado);
 
         // Retornar el mensaje descifrado como un String
         return new String(mensajeDescifrado);
     }
 
     public static void main(String[] args) {
         // Array de mensajes a cifrar
         String[] mensajes = {
             "Lorem ipsum dicet",
             "Hola Andrés cómo está tu cuñado",
             "Àgora ïlla Ôtto"
         };
 
         // Probar el cifrado y descifrado para cada mensaje
         for (String mensaje : mensajes) {
             try {
                 // Cifrar el mensaje
                 String mensajeCifradoBase64 = cifrarAES(mensaje, CLAVE_SECRETA);
                 // Descifrar el mensaje cifrado
                 String mensajeDescifrado = descifrarAES(mensajeCifradoBase64, CLAVE_SECRETA);
 
                 // Mostrar los resultados
                 System.out.println("Mensaje original: " + mensaje);
                 System.out.println("Mensaje cifrado (Base64): " + mensajeCifradoBase64);
                 System.out.println("Mensaje descifrado: " + mensajeDescifrado);
                 System.out.println();
             } catch (Exception e) {
                 System.err.println("Error durante el proceso de cifrado/descifrado: " + e.getLocalizedMessage());
             }
         }
     }
 }
 