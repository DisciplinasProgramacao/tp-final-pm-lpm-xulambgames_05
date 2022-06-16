
import java.util.HashMap;

public class Sistema {

    public void salvarDadosClienteBin(){}
    public void salvarDadosPedidosBin(){}
    public void carregarDadosClienteBin(){}
    public void carregarDadosPedidosBin(){}

    public HashMap<String, Cliente> clientesMap;
    public HashMap<String, Jogo> jogosMap;


    public static void main(String[] args) throws Exception {
       Loja l = new Loja();
       l.clientesMap = new HashMap<String, Cliente>();
       l.jogosMap = new HashMap<String, Jogo>();
       Cliente c1 = new Cliente("codigo", "nome", "senha", "username", "email", TipoCliente.EMPOLGADO);
       Cliente c2 = new Cliente("codigo", "nome", "senha", "username", "email", TipoCliente.FANATICO);

    }
}
