import java.util.HashMap;

public class Loja {
    
    private HashMap<String, Cliente> clientesMap;
    private HashMap<String, Jogo> jogosList;
    
    // public double ValorMensal(int ano, int mes){}
    // public double ValorMedioCompras(){}
    // public Jogo jogoMaisVendido(){}
    // public Jogo jogoMenosVendido(){}
    // public void registrarVenda(String matriculaCliente,Venda venda){}
    
    public void cadastrarCliente(String codigo, Cliente cliente){
        this.clientesMap.put(codigo, cliente);
    }

    public void cadastrarJogo(String codigo, Jogo jogo){
        this.jogosList.put(codigo, jogo);
    }

    public void cadastrarVenda(String codigoCliente, Venda venda) throws Exception{
        
        Cliente c = this.clientesMap.get(codigoCliente);
        if (c == null) {
            throw new Exception("A matricula informada nao esta cadastrada no sistema!");
        }
        c.addVenda(venda);

    }


}
