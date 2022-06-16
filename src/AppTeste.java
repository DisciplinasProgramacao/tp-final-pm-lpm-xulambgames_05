import java.time.LocalDate;
import java.util.ArrayList;


public class AppTeste {
    
    public static void main(String[] args) throws Exception {
        
        Loja l = new Loja();
        Jogo j = new Jogo("codigo", "tit_promocao", 60, TipoJogo.PROMOCAO, 0.6);
        Jogo j2 = new Jogo("codig", "tit_regular", 100, TipoJogo.REGULAR, 0.3);
        Cliente cliente = new Cliente("codigoCliente", "nome", "senha", "username", "email", TipoCliente.EMPOLGADO);
        
        ArrayList<Jogo> jogosList = new ArrayList<>();
        jogosList.add(j);
        jogosList.add(j2);
        l.cadastrarCliente(cliente);
        l.registrarVenda("codigoCliente", new Venda(jogosList, LocalDate.now()));
        System.out.println(cliente.historicoCompras());
    }
}
