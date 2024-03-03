import java.util.Random;

public class Codigos {
    public static String gerarCodigo() {
        String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int tamanho_codigo = 6;
        Random random = new Random();
        StringBuilder codigo = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            codigo.append(CARACTERES.charAt(random.nextInt(CARACTERES.length())));
        }
        return codigo.toString();
    }
}
