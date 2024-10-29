package iticbcn.xifratge;

public class XifradorPolialfabetic implements Xifrador {
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            long key = Long.parseLong(clau);
            StringBuilder xifratText = new StringBuilder();
            for (int i = 0; i < msg.length(); i++) {
                char c = msg.charAt(i);
                if (Character.isLetter(c) && c <= 127) {  // Solo procesa caracteres básicos
                    int shift = (int) ((i + key) % 26);
                    char base = Character.isUpperCase(c) ? 'A' : 'a';
                    c = (char) (base + (c - base + shift) % 26);
                }
                xifratText.append(c);
            }
            return new TextXifrat(xifratText.toString().getBytes());
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            long key = Long.parseLong(clau);
            String encryptedText = new String(xifrat.getBytes());
            StringBuilder desxifratText = new StringBuilder();
            for (int i = 0; i < encryptedText.length(); i++) {
                char c = encryptedText.charAt(i);
                if (Character.isLetter(c) && c <= 127) {  // Solo procesa caracteres básicos
                    int shift = (int) ((i + key) % 26);
                    char base = Character.isUpperCase(c) ? 'A' : 'a';
                    c = (char) (base + (c - base - shift + 26) % 26);
                }
                desxifratText.append(c);
            }
            return desxifratText.toString();
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
    }
}
