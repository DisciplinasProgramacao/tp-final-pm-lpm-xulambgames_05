import java.util.ArrayList;
import java.util.HashMap;

public class Loja {
    
    public HashMap<String, Cliente> clientesMap;
    public HashMap<String, Jogo> jogosMap;
    
    public double ValorMensalVendido(int mes, int ano){
        ArrayList<Venda> vendas = new ArrayList<Venda>();
        for (Cliente c : clientesMap.values()) {
            vendas.addAll(c.getVendasList());
        }

        double arrecadacaoMes = vendas.stream()
            .filter((v) -> v.getDataVenda().YEAR ==  ano)
            .filter((v) ->v.getDataVenda().MONTH == mes)
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
    
    // public void registrarVenda(String matriculaCliente,Venda venda){}
    
    public void cadastrarCliente(String codigo, Cliente cliente){
        this.clientesMap.put(codigo, cliente);
    }

    public void cadastrarJogo(String codigo, Jogo jogo){
        this.jogosMap.put(codigo, jogo);
    }

    public void cadastrarVenda(String codigoCliente, Venda venda) throws Exception{
        
        Cliente c = this.clientesMap.get(codigoCliente);
        if (c == null) {
            throw new Exception("A matricula informada nao esta cadastrada no sistema!");
        }
        c.addVenda(venda);

    }


}
