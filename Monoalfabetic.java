import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Monoalfabetic {
    
    public static final char[] abc = {'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z'};

    public static char[] abcPermut;

    public static void main(String[] args) {
        abcPermut = permutaAlfabet(abc);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escribe el texto que quieres tratar");
        String cadena = scanner.nextLine(); 
        System.out.println("¿Quieres codificar o decodificar el texto? (c - d)");
        String accion = scanner.nextLine().toLowerCase();
        if (accion.equals("c")) {
            System.out.println("Su codificación es: " + xifraMonoAlfa(cadena));
        } else if (accion.equals("d")) {
            System.out.println("Su decodificación es: " + desxifraMonoAlfa(cadena));
        } 
        scanner.close(); 
    }

    public static char[] permutaAlfabet(char[] abc) {
        List<Character> abcList = new ArrayList<>();
        for (char letra : abc) { abcList.add(letra); }
        Collections.shuffle(abcList);
        char[] abcShuffle = new char[abcList.size()];
        for (int i = 0; i < abcList.size(); i++) { abcShuffle[i] = abcList.get(i); }
        return abcShuffle;
    }

    public static String xifraMonoAlfa(String cadena) {
        StringBuilder cadena2 = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
            boolean mayus = Character.isUpperCase(letra);
            letra = Character.toLowerCase(letra);
            boolean found = false;
            for (int j = 0; j < abc.length; j++) {
                if (abc[j] == letra) {
                    char letraCifr = abcPermut[j];
                    cadena2.append(mayus ? Character.toUpperCase(letraCifr) : letraCifr);
                    found = true;
                    break;
                }
            }
            if (!found) {
                cadena2.append(letra);
            }
        }
        return cadena2.toString();
    }
    
    public static String desxifraMonoAlfa(String cadena) {
        StringBuilder cadena3 = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
            boolean mayus = Character.isUpperCase(letra);
            letra = Character.toLowerCase(letra);
            boolean found = false;
            for (int j = 0; j < abc.length; j++) {
                if (abcPermut[j] == letra) {
                    char letraDesci = abc[j];
                    cadena3.append(mayus ? Character.toUpperCase(letraDesci) : letraDesci);
                    found = true;
                    break;
                }
            }
            if (!found) {
                cadena3.append(letra);
            }
        }
        return cadena3.toString();
    }
}