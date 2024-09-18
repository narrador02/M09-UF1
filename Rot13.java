public class Rot13 {
    public static char[] abc = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z'};
    public static char[] ABC = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    public static final String xifraRot13(String cadena) {
        String cadenaRot = "";
        for (int i=0;i<cadena.length(); i++) {
            if (Character.isLetter(cadena.charAt(i))) {
                if (Character.isUpperCase(cadena.charAt(i))) {
                    for (int x=0;x<ABC.length;x++) {
                        if (cadena.charAt(i) == ABC[x]) {
                            if (x+13>ABC.length) {
                                cadenaRot = cadena.substring(0,i-1) + ABC[x + 13 - ABC.length] + cadena.substring(i+1, cadena.length());
                            } else {
                                cadenaRot = cadena.substring(0,i-1) + ABC[x + 13] + cadena.substring(i+1, cadena.length());
                            }
                        }
                    }
                } else {
                    for (int x=0;x<abc.length;x++) {
                        if (cadena.charAt(i) == abc[x]) {
                            if (x+13>abc.length) {
                                cadenaRot = cadena.substring(0,i-1) + abc[x + 13 - abc.length] + cadena.substring(i+1, cadena.length());
                            } else {
                                cadenaRot = cadena.substring(0,i-1) + abc[x + 13] + cadena.substring(i+1, cadena.length());
                            }
                        }
                    }
                }
            }
        }
        return cadenaRot;
    }
    public static final String desxifraRot13(String cadena) {
        String cadenaRot = "";
        for (int i=0;i<cadena.length(); i++) {
            if (Character.isLetter(cadena.charAt(i))) {
                if (Character.isUpperCase(cadena.charAt(i))) {
                    for (int x=0;x<ABC.length;x++) {
                        if (cadena.charAt(i) == ABC[x]) {
                            if (x-13<ABC.length) {
                                cadenaRot = cadena.substring(0,i-1) + ABC[x - 13 + ABC.length] + cadena.substring(i+1, cadena.length());
                            } else {
                                cadenaRot = cadena.substring(0,i-1) + ABC[x - 13] + cadena.substring(i+1, cadena.length());
                            }
                        }
                    }
                } else {
                    for (int x=0;x<abc.length;x++) {
                        if (cadena.charAt(i) == abc[x]) {
                            if (x-13<abc.length) {
                                cadenaRot = cadena.substring(0,i-1) + abc[x - 13 + abc.length] + cadena.substring(i+1, cadena.length());
                            } else {
                                cadenaRot = cadena.substring(0,i-1) + abc[x - 13] + cadena.substring(i+1, cadena.length());
                            }
                        }
                    }
                }
            }
        }
        return cadenaRot;
    }
}