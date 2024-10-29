package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            int rot = Integer.parseInt(clau);
            if (rot < 0 || rot > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            StringBuilder xifratText = new StringBuilder();
            for (char c : msg.toCharArray()) {
                if (Character.isLetter(c) && c <= 127) {  // Solo procesa caracteres básicos
                    char base = Character.isUpperCase(c) ? 'A' : 'a';
                    c = (char) (base + (c - base + rot) % 26);
                }
                xifratText.append(c);
            }
            return new TextXifrat(xifratText.toString().getBytes());
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            int rot = Integer.parseInt(clau);
            String encryptedText = new String(xifrat.getBytes());
            StringBuilder desxifratText = new StringBuilder();
            for (char c : encryptedText.toCharArray()) {
                if (Character.isLetter(c) && c <= 127) {  // Solo procesa caracteres básicos
                    char base = Character.isUpperCase(c) ? 'A' : 'a';
                    c = (char) (base + (c - base - rot + 26) % 26);
                }
                desxifratText.append(c);
            }
            return desxifratText.toString();
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }
}
