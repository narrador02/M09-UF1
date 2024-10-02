/* 
 * Comentarios generados mediante Inteligencia Artificial
 * Autor del código: Jairo Vigil, DAM2A
 */
import java.util.Scanner;

public class RotX {
    // Arrays de caracteres para el alfabeto en minúsculas y mayúsculas
    public static char[] abc = {'a','á','b','c','ç','d','e','é','è','f','g','h','i','í','j','k','l','m','n','ñ','o','ó','p','q','r','s','t','u','ú','ü','v','w','x','y','z'};
    public static char[] ABC = {'A','Á','B','C','Ç','D','E','É','È','F','G','H','I','Í','J','K','L','M','N','Ñ','O','Ó','P','Q','R','S','T','U','Ú','Ü','V','W','X','Y','Z'};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Crear objeto Scanner para leer la entrada del usuario
        System.out.println("Escribe el texto que quieres tratar");
        String texto = scanner.nextLine(); // Leer texto de entrada
        System.out.println("¿Quieres codificar, decodificar o forzar el texto? (c - d - f)");
        String accion = scanner.nextLine().toLowerCase(); // Leer acción del usuario y convertir a minúsculas
        System.out.println("¿Cuantos desplazamientos quieres? (Número entero positivo)");
        int desplaçament = Integer.parseInt(scanner.nextLine());
        if (accion.equals("c")) {
            System.out.println("Su codificación es: " + xifraRotX(texto, desplaçament)); // Codificar texto
        } 
        if (accion.equals("d")) {
            System.out.println("Su decodificación es: " + desxifraRotX(texto, desplaçament)); // Decodificar texto
        } 
        if (accion.equals("f")) {
            System.out.println("El resultado de forzar la decodificación es: " + "\n" + forcaBrutaRotX(xifraRotX(texto, desplaçament)));
        }
        scanner.close(); // Cerrar el objeto Scanner
    }
    
    public static final String xifraRotX(String cadena, int desplaçament) {
        String cadenaRot = "";
        for (int i = 0; i < cadena.length(); i++) {
            if (Character.isLetter(cadena.charAt(i))) { // Verificar si el carácter es una letra
                if (Character.isUpperCase(cadena.charAt(i))) { // Verificar si es mayúscula
                    for (int x = 0; x < ABC.length; x++) {
                        if (cadena.charAt(i) == ABC[x]) {
                            if (x + desplaçament >= ABC.length) {
                                cadenaRot = cadenaRot + ABC[x + desplaçament - ABC.length]; // Rotar X posiciones
                            } else {
                                cadenaRot = cadenaRot + ABC[x + desplaçament];
                            }
                            break; // Salir del bucle una vez encontrado el carácter
                        }
                    }
                } else {
                    for (int x = 0; x < abc.length; x++) {
                        if (cadena.charAt(i) == abc[x]) {
                            if (x + desplaçament >= abc.length) {
                                cadenaRot = cadenaRot + abc[x + desplaçament - abc.length]; // Rotar X posiciones
                            } else {
                                cadenaRot = cadenaRot + abc[x + desplaçament];
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

    public static final String desxifraRotX(String cadena, int desplaçament) {
        String cadenaRot = "";
        for (int i = 0; i < cadena.length(); i++) {
            if (Character.isLetter(cadena.charAt(i))) { // Verificar si el carácter es una letra
                if (Character.isUpperCase(cadena.charAt(i))) { // Verificar si es mayúscula
                    for (int x = 0; x < ABC.length; x++) {
                        if (cadena.charAt(i) == ABC[x]) {
                            if (x - desplaçament < 0) {
                                cadenaRot = cadenaRot + ABC[x - desplaçament + ABC.length]; // Rotar X posiciones
                            } else {
                                cadenaRot = cadenaRot + ABC[x - desplaçament];
                            }
                            break; // Salir del bucle una vez encontrado el carácter
                        }
                    }
                } else {
                    for (int x = 0; x < abc.length; x++) {
                        if (cadena.charAt(i) == abc[x]) {
                            if (x - desplaçament < 0) {
                                cadenaRot = cadenaRot + abc[x - desplaçament + abc.length]; // Rotar X posiciones
                            } else {
                                cadenaRot = cadenaRot + abc[x - desplaçament];
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
    public static final String forcaBrutaRotX(String cadenaXifrada) {
        String resultados = "";
        for (int desplaçament = 1; desplaçament < abc.length; desplaçament++) {
            String resultado = desxifraRotX(cadenaXifrada, desplaçament);
            resultados += "Desplazamiento " + desplaçament + ": " + resultado + "\n";
        }
        return resultados;
    }
}