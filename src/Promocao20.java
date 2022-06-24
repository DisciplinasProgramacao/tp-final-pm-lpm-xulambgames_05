import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Promocao20 implements IPromocao, Serializable {

    private static final double PERC_DESCONTO = 0.2;
    private static Promocao20 instance;

    private Promocao20(){}

    public static Promocao20 getInstance(){
        if(instance == null){
            instance= new Promocao20();
        }
        return instance;
    }

    public static boolean isAptoDesconto(Venda venda) {
        ArrayList<Jogo> jogosList = venda.getJogosList();
        HashMap<TipoJogo,Integer> mapaJogos = new HashMap<TipoJogo, Integer>();

        /*  LANCAMENTO(0),
            PREMIUM(1),
            REGULAR(2),
            PROMOCAO(3); */

        mapaJogos.put(TipoJogo.LANCAMENTO,  (int) jogosList.stream().filter(j -> j.getTipoJogo() == TipoJogo.LANCAMENTO).count());
        mapaJogos.put(TipoJogo.PREMIUM,     (int) jogosList.stream().filter(j -> j.getTipoJogo() == TipoJogo.PREMIUM).count());
        mapaJogos.put(TipoJogo.REGULAR,     (int) jogosList.stream().filter(j -> j.getTipoJogo() == TipoJogo.REGULAR).count());
        mapaJogos.put(TipoJogo.PROMOCAO,    (int) jogosList.stream().filter(j -> j.getTipoJogo() == TipoJogo.PROMOCAO).count());


        if (mapaJogos.get(TipoJogo.LANCAMENTO)>=2 || 
            mapaJogos.get(TipoJogo.PREMIUM)>=3 || 
            mapaJogos.get(TipoJogo.REGULAR)>=5 ||
            (mapaJogos.get(TipoJogo.PREMIUM)>=2 && jogosList.size() >=3) ||
            (mapaJogos.get(TipoJogo.REGULAR)>=3 && (mapaJogos.get((TipoJogo.LANCAMENTO)) >=1 || mapaJogos.get((TipoJogo.PREMIUM)) >=1)))
            {
            return true;
        }

        return false;
    }

    @Override
    public double percDesconto() {
        return PERC_DESCONTO;
    }
    
}
