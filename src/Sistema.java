
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Sistema {

    public void salvarDadosClienteBin(){}
    public void salvarDadosPedidosBin(){}
    public void carregarDadosClienteBin(){}
    public void carregarDadosPedidosBin(){}



    public static void main(String[] args) throws Exception {
       Loja l = new Loja();
    //    public HashMap<String, Cliente> clientesMap;
    //    public HashMap<String, Jogo> jogosMap;
       l.clientesMap = new HashMap<String, Cliente>();
       l.jogosMap = new HashMap<String, Jogo>();
       Cliente c1 = new Cliente();
       Cliente c2 = new Cliente();
       
        Jogo j1 = new Jogo("j1", "titulo", 10, TipoJogo.PREMIUM);
        Jogo j2 = new Jogo("j2", "titulo", 10, TipoJogo.PREMIUM);
        Jogo j3 = new Jogo("j3", "titulo", 20, TipoJogo.PREMIUM);


        ArrayList<Jogo> jogosList = new ArrayList<>();
        ArrayList<Jogo> jogosList2 = new ArrayList<>();
        jogosList.add(j1);
        jogosList.add(j2);
        jogosList2.add(j3);
        jogosList2.add(j2);
        jogosList2.add(j2);
        for (Jogo jogo : jogosList2) {
            l.jogosMap.put(jogo.getCodigo(), jogo);
        }
        
        for (Jogo jogo : jogosList) {
            l.jogosMap.put(jogo.getCodigo(), jogo);
        }
        Calendar cc = Calendar.getInstance();
        cc.set(Calendar.YEAR, 2022);
        cc.set(Calendar.DATE, 13);
        cc.set(Calendar.MONTH, 2);
        
        Calendar cc2 = Calendar.getInstance();
        cc2.set(Calendar.YEAR, 2022);
        cc2.set(Calendar.DATE, 12);
        cc2.set(Calendar.MONTH, 3);

        c1.addVenda(new Venda(jogosList, cc2));
        c2.addVenda(new Venda(jogosList2,cc));

       l.clientesMap.put("1", c1);
       l.clientesMap.put("2", c2);

       System.out.println( l.ValorMensalVendido(2,2022));
       System.out.println( l.ValorMedioCompras());
       System.out.println(l.jogoMaisVendido());
       System.out.println(l.jogoMenosVendido());

    }
}
