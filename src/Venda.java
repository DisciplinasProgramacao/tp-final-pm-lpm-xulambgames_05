import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.HashMap;

public class Venda implements Serializable{
    private ArrayList<Jogo> jogosList;
    private LocalDate dataVenda;
    
    
    public ArrayList<Jogo> getJogosList() {
        return jogosList;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }


    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }


    public Venda(ArrayList<Jogo> jogosList, LocalDate dataVenda) {
        this.jogosList = jogosList;
        this.dataVenda = dataVenda;
    }


    public double calcularValor(){
        double valor = 0.0;
        for (Jogo jogo : jogosList) {
            valor += jogo.calcularValor();
        }
        valor -= (valor * calcularDesconto());
        return valor;
    }


    public double calcularDesconto() {
        double desconto = 0;
        HashMap<TipoJogo,Integer> mapaJogos = new HashMap<TipoJogo, Integer>();

        /*  LANCAMENTO(0),
            PREMIUM(1),
            REGULAR(2),
            PROMOCAO(3); */

            // Teste
        mapaJogos.put(TipoJogo.LANCAMENTO,  (int) jogosList.stream().filter(j -> j.getTipoJogo() == TipoJogo.LANCAMENTO).count());
        mapaJogos.put(TipoJogo.PREMIUM,     (int) jogosList.stream().filter(j -> j.getTipoJogo() == TipoJogo.PREMIUM).count());
        mapaJogos.put(TipoJogo.REGULAR,     (int) jogosList.stream().filter(j -> j.getTipoJogo() == TipoJogo.REGULAR).count());
        mapaJogos.put(TipoJogo.PROMOCAO,    (int) jogosList.stream().filter(j -> j.getTipoJogo() == TipoJogo.PROMOCAO).count());


        // PROMOCOES
        if (mapaJogos.get(TipoJogo.PREMIUM) >= 2 || mapaJogos.get(TipoJogo.REGULAR) >= 4) {
            desconto = 0.1;
        }

        if (mapaJogos.get(TipoJogo.LANCAMENTO)>=2 || 
            mapaJogos.get(TipoJogo.PREMIUM)>=3 || 
            mapaJogos.get(TipoJogo.REGULAR)>=5 ||
            (mapaJogos.get(TipoJogo.PREMIUM)>=2 && jogosList.size() >=3) ||
            (mapaJogos.get(TipoJogo.REGULAR)>=3 && (mapaJogos.get((TipoJogo.LANCAMENTO)) >=1 || mapaJogos.get((TipoJogo.PREMIUM)) >=1))
            ) {
            desconto = 0.2;
        }

        
        return desconto;
    }

    @Override
    public String toString(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = this.dataVenda.format(formatter);

        String s = dataFormatada.toString() + ": {\n\n";
        
        for (Jogo j : this.jogosList) {
            s+= "\t" + j.toString() + "\n";
        }
        s = s + "\n\tvalor total: R$" + this.calcularValor();
        s +="\n}";
        return s;
    }
    
}

