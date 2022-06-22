import java.time.LocalDate;
import java.util.ArrayList;


public class AppTeste {
    
    public static void main(String[] args) throws Exception {
        
        Loja l = new Loja();
        Jogo j = new Jogo("codigo", "tit_promocao", 60, TipoJogo.PROMOCAO, 0.6);
        Jogo j2 = new Jogo("codig", "tit_regular", 100, TipoJogo.REGULAR, 0.3);
        Cliente cliente = new Cliente("codigoCliente", "nome", "senha", "username", "email", TipoCliente.EMPOLGADO);
        Cliente cliente2 = new Cliente("codigoCliente2", "nome", "senha", "username", "email", TipoCliente.EMPOLGADO);
        
        ArrayList<Jogo> jogosList = new ArrayList<>();
        jogosList.add(j);
        jogosList.add(j2);
 
        ArrayList<Jogo> jogosList2 = new ArrayList<>();
        jogosList2.add(j2);

        l.cadastrarCliente(cliente2);
        l.registrarVenda("codigoCliente2", new Venda(jogosList2, LocalDate.now()));
        
        l.cadastrarCliente(cliente);
        l.registrarVenda("codigoCliente", new Venda(jogosList2, LocalDate.now()));
        l.registrarVenda("codigoCliente", new Venda(jogosList, LocalDate.now().minusDays(2)));
        System.out.println(cliente.historicoCompras());
    }
}
