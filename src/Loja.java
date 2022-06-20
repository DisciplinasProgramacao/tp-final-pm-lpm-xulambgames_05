import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Loja {
    
    public HashMap<String, Cliente> clientesMap;
    public HashMap<String, Jogo> jogosMap;
    
    public Loja() {
        this.clientesMap = new HashMap<String, Cliente>();
        this.jogosMap = new HashMap<String, Jogo>();
    }

    public double ValorMensalVendido(int mes, int ano){
        ArrayList<Venda> vendas = new ArrayList<Venda>();
        for (Cliente c : clientesMap.values()) {
            vendas.addAll(c.getVendasList());
        }

        double arrecadacaoMes = vendas.stream()
            .filter((v) -> v.getDataVenda().getYear() ==  ano)
            .filter((v) ->v.getDataVenda().getMonthValue() == mes)
            .map(Venda::calcularValor)
            .reduce(0.0, (a,b) -> a + b);

        
        return arrecadacaoMes;

    }
    
    public double ValorMedioCompras(){
        ArrayList<Venda> vendas = new ArrayList<Venda>();
        for (Cliente c : clientesMap.values()) {
            vendas.addAll(c.getVendasList());
        }

        double totalArrecadado = vendas
            .stream()
            .map(Venda::calcularValor)
            .reduce(0.0, (a, b) -> a + b);
        return totalArrecadado / vendas.size(); 
    }
    
    public Jogo jogoMaisVendido(){
        Jogo maisVendido = null;
        Integer maxValue = 0;

        HashMap<Jogo, Integer> freqMap = new HashMap<Jogo, Integer>();
        for (Jogo j : jogosMap.values()) {
            freqMap.put(j, freqMap.getOrDefault(j, 0) + 1);
        }

        for (Jogo j : freqMap.keySet()) {
            if (maisVendido == null || maxValue < freqMap.get(j)) {
                maisVendido = j;
                maxValue = freqMap.get(j);
            }
        }

        return maisVendido;
    }

    public Jogo jogoMenosVendido(){
        Jogo menosVendido = null;
        Integer minValue = 0;

        HashMap<Jogo, Integer> freqMap = new HashMap<Jogo, Integer>();
        for (Jogo j : jogosMap.values()) {
            freqMap.put(j, freqMap.getOrDefault(j, 0) + 1);
        }

        for (Jogo j : freqMap.keySet()) {
            if (menosVendido == null || minValue > freqMap.get(j)) {
                menosVendido = j;
                minValue = freqMap.get(j);
            }
        }

        return menosVendido;
    }
    
    public void registrarVenda(String codigoCliente,Venda venda) throws Exception{
        Optional<Cliente> cliente = Optional.ofNullable(this.clientesMap.get(codigoCliente));
        if (!(cliente.isPresent())) {
            throw new Exception("O cliente buscado não está cadastrado");
        }
        cliente.get().addVenda(venda);
    }
    
    public void cadastrarCliente(Cliente cliente) throws Exception{
        Optional<Cliente> clienteOptional = Optional.ofNullable(this.clientesMap.get(cliente.getCodigo()));
        if (clienteOptional.isPresent()) {
            throw new Exception("O cliente buscado já está cadastrado");
        }
        this.clientesMap.put(cliente.getCodigo(), cliente);
    }

    public void cadastrarJogo(Jogo jogo){
        this.jogosMap.put(jogo.getCodigo(), jogo);
    }

}
