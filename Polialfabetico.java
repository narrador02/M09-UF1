import java.util.Random;

public class Polialfabetico {
    static char[] alfabetoOriginal = "aàbcçdeèéfghiíjklmnoòópqrstuúvwxyzñ".toCharArray(); // Alfabeto original

    // Genera una permutación del alfabeto
    public static char[] permutarAlfabeto() {
        char[] alfabetoPermutado = alfabetoOriginal.clone();  // Hacemos una copia del alfabeto
        Random rand = new Random();
        for (int i = 0; i < alfabetoPermutado.length; i++) {
            int randomIndex = rand.nextInt(alfabetoPermutado.length);
            char temp = alfabetoPermutado[i];
            alfabetoPermutado[i] = alfabetoPermutado[randomIndex];
            alfabetoPermutado[randomIndex] = temp;
        }
        return alfabetoPermutado; // Devolvemos la permutación generada
    }

    // Inicializa el Random con la semilla
    public static void initRandom(String claveSecreta) {
        Random rand = new Random(claveSecreta.hashCode()); 
    }

    // Cifrado polialfabético
    public static String cifrarPoliAlfa(String mensaje, char[] alfabetoPermutado) {
        StringBuilder mensajeCifrado = new StringBuilder();
        
        for (int i = 0; i < mensaje.length(); i++) {
            char letraOriginal = mensaje.charAt(i);
            if (Character.isLetter(letraOriginal)) {
                int index = new String(alfabetoOriginal).indexOf(Character.toLowerCase(letraOriginal));
                if (index != -1) {
                    char letraCifrada = alfabetoPermutado[index];
                    if (Character.isUpperCase(letraOriginal)) {
                        letraCifrada = Character.toUpperCase(letraCifrada);
                    }
                    mensajeCifrado.append(letraCifrada);
                } else {
                    mensajeCifrado.append(letraOriginal);
                }
            } else {
                mensajeCifrado.append(letraOriginal); // Si no es una letra, no se cifra
            }
        }
        return mensajeCifrado.toString();
    }

    // Descifrado polialfabético
    public static String descifrarPoliAlfa(String mensajeCifrado, char[] alfabetoPermutado) {
        StringBuilder mensajeDescifrado = new StringBuilder();
        
        for (int i = 0; i < mensajeCifrado.length(); i++) {
            char letraCifrada = mensajeCifrado.charAt(i);
            if (Character.isLetter(letraCifrada)) {
                int index = new String(alfabetoPermutado).indexOf(Character.toLowerCase(letraCifrada));
                if (index != -1) {
                    char letraOriginal = alfabetoOriginal[index];
                    if (Character.isUpperCase(letraCifrada)) {
                        letraOriginal = Character.toUpperCase(letraOriginal);
                    }
                    mensajeDescifrado.append(letraOriginal);
                } else {
                    mensajeDescifrado.append(letraCifrada);
                }
            } else {
                mensajeDescifrado.append(letraCifrada); // Si no es una letra, no se descifra
            }
        }
        return mensajeDescifrado.toString();
    }

    // Método principal
    public static void main(String[] args) {
        String claveSecreta = "contraseña321"; // Llave usada para iniciar la aleatoriedad
        String mensajes[] = {
            "Test 01 árbitro, cojín, Perímetro",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Pieza, Órrius, Bóveda"
        };
        String mensajesCifrados[] = new String[mensajes.length];
        char[][] permutaciones = new char[mensajes.length][]; // Array para guardar las permutaciones de cada mensaje

        System.out.println("Cifrado:\n--------");
        for (int i = 0; i < mensajes.length; i++) {
            initRandom(claveSecreta); // Inicializamos el random con la misma clave
            permutaciones[i] = permutarAlfabeto(); // Generamos y guardamos la permutación para este mensaje
            mensajesCifrados[i] = cifrarPoliAlfa(mensajes[i], permutaciones[i]); // Ciframos usando la permutación
            System.out.printf("%-34s -> %s%n", mensajes[i], mensajesCifrados[i]);
        }

        System.out.println("Descifrado:\n-----------");
        for (int i = 0; i < mensajes.length; i++) {
            initRandom(claveSecreta); // Inicializamos el random con la misma clave para descifrar
            String mensajeDescifrado = descifrarPoliAlfa(mensajesCifrados[i], permutaciones[i]); // Desciframos usando la misma permutación
            System.out.printf("%-34s -> %s%n", mensajesCifrados[i], mensajeDescifrado);
        }
    }
}