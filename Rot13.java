/* 
 * Comentarios generados mediante Inteligencia Artificial
 * Autor del código: Jairo Vigil, DAM2A
 * Versión final
 */
import java.util.Scanner;

public class Rot13 {
    // Arrays de caracteres para el alfabeto en minúsculas y mayúsculas
    public static char[] abc = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z'};
    public static char[] ABC = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Crear objeto Scanner para leer la entrada del usuario
        System.out.println("Escribe el texto que quieres tratar");
        String texto = scanner.nextLine(); // Leer texto de entrada
        System.out.println("¿Quieres codificar o decodificar el texto?");
        String accion = scanner.nextLine().toLowerCase(); // Leer acción del usuario y convertir a minúsculas

        if (accion.equals("codificar")) {
            System.out.println("Su codificación es: " + xifraRot13(texto)); // Codificar texto
        }
        if (accion.equals("decodificar")) {
            System.out.println("Su decodificación es: " + desxifraRot13(texto)); // Decodificar texto
        }
        scanner.close(); // Cerrar el objeto Scanner
    }

    public static final String xifraRot13(String cadena) {
        String cadenaRot = "";
        for (int i = 0; i < cadena.length(); i++) {
            if (Character.isLetter(cadena.charAt(i))) { // Verificar si el carácter es una letra
                if (Character.isUpperCase(cadena.charAt(i))) { // Verificar si es mayúscula
                    for (int x = 0; x < ABC.length; x++) {
                        if (cadena.charAt(i) == ABC[x]) {
                            if (x + 13 >= ABC.length) {
                                cadenaRot = cadenaRot + ABC[x + 13 - ABC.length]; // Rotar 13 posiciones
                            } else {
                                cadenaRot = cadenaRot + ABC[x + 13];
                            }
                            break; // Salir del bucle una vez encontrado el carácter
                        }
                    }
                } else {
                    for (int x = 0; x < abc.length; x++) {
                        if (cadena.charAt(i) == abc[x]) {
                            if (x + 13 >= abc.length) {
                                cadenaRot = cadenaRot + abc[x + 13 - abc.length]; // Rotar 13 posiciones
                            } else {
                                cadenaRot = cadenaRot + abc[x + 13];
                            }
                            break; // Salir del bucle una vez encontrado el carácter
                        }
                    }
                }
            } else {
                cadenaRot = cadenaRot + cadena.charAt(i); // Añadir caracteres no alfabéticos sin cambios
            }
        }
        return cadenaRot;
    }

    public static final String desxifraRot13(String cadena) {
        String cadenaRot = "";
        for (int i = 0; i < cadena.length(); i++) {
            if (Character.isLetter(cadena.charAt(i))) { // Verificar si el carácter es una letra
                if (Character.isUpperCase(cadena.charAt(i))) { // Verificar si es mayúscula
                    for (int x = 0; x < ABC.length; x++) {
                        if (cadena.charAt(i) == ABC[x]) {
                            if (x - 13 < 0) {
                                cadenaRot = cadenaRot + ABC[x - 13 + ABC.length]; // Rotar 13 posiciones
                            } else {
                                cadenaRot = cadenaRot + ABC[x - 13];
                            }
                            break; // Salir del bucle una vez encontrado el carácter
                        }
                    }
                } else {
                    for (int x = 0; x < abc.length; x++) {
                        if (cadena.charAt(i) == abc[x]) {
                            if (x - 13 < 0) {
                                cadenaRot = cadenaRot + abc[x - 13 + abc.length]; // Rotar 13 posiciones
                            } else {
                                cadenaRot = cadenaRot + abc[x - 13];
                            }
                            break; // Salir del bucle una vez encontrado el carácter
                        }
                    }
                }
            } else {
                cadenaRot = cadenaRot + cadena.charAt(i); // Añadir caracteres no alfabéticos sin cambios
            }
        }
        return cadenaRot;
    }
}
