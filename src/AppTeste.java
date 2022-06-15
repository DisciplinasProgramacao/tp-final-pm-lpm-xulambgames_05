import java.util.HashMap;

public class AppTeste {
    
    public static void main(String[] args) {
        HashMap<String, Jogo> jogosMap = new HashMap<String, Jogo>();
        System.out.println(TipoJogo.PROMOCAO);
        Jogo j = new Jogo("codigo", "titulo", 60, TipoJogo.PROMOCAO);
        System.out.println(j.getTipoJogo().getTipo());
    }
}
