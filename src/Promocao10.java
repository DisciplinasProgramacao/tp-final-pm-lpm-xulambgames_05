import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Promocao10 implements IPromocao, Serializable {

    private static final double PERC_DESCONTO = 0.1;
    private static Promocao10 instance;

    private Promocao10(){}

    public static Promocao10 getInstance(){
        if(instance == null){
            instance= new Promocao10();
        }
        return instance;
    }

    public static boolean isAptoDesconto(Venda venda) {
        ArrayList<Jogo> jogosList = venda.getJogosList(); 
        HashMap<TipoJogo,Integer> mapaJogos = new HashMap<TipoJogo, Integer>();

        mapaJogos.put(TipoJogo.LANCAMENTO,  (int) jogosList.stream().filter(j -> j.getTipoJogo() == TipoJogo.LANCAMENTO).count());
        mapaJogos.put(TipoJogo.PREMIUM,     (int) jogosList.stream().filter(j -> j.getTipoJogo() == TipoJogo.PREMIUM).count());
        mapaJogos.put(TipoJogo.REGULAR,     (int) jogosList.stream().filter(j -> j.getTipoJogo() == TipoJogo.REGULAR).count());
        mapaJogos.put(TipoJogo.PROMOCAO,    (int) jogosList.stream().filter(j -> j.getTipoJogo() == TipoJogo.PROMOCAO).count());

        if (mapaJogos.get(TipoJogo.PREMIUM) >= 2 || mapaJogos.get(TipoJogo.REGULAR) >= 4) {
            return true;
        }
        return false;
    }

    @Override
    public double percDesconto() {
        return PERC_DESCONTO;
    }
    
}
