package iticbcn.xifratge;

public class XifradorMonoalfabetic implements Xifrador {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        StringBuilder xifratText = new StringBuilder();
        for (char c : msg.toCharArray()) {
            if (ALPHABET.indexOf(c) != -1) {
                int pos = (ALPHABET.indexOf(c) + 13) % ALPHABET.length();
                xifratText.append(ALPHABET.charAt(pos));
            } else {
                xifratText.append(c);
            }
        }
        return new TextXifrat(xifratText.toString().getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        StringBuilder desxifratText = new StringBuilder();
        String encryptedText = new String(xifrat.getBytes());
        for (char c : encryptedText.toCharArray()) {
            if (ALPHABET.indexOf(c) != -1) {
                int pos = (ALPHABET.indexOf(c) - 13 + ALPHABET.length()) % ALPHABET.length();
                desxifratText.append(ALPHABET.charAt(pos));
            } else {
                desxifratText.append(c);
            }
        }
        return desxifratText.toString();
    }
}