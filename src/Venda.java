import java.time.LocalDate;
import java.util.ArrayList;

import java.util.HashMap;

public class Venda{
    private ArrayList<Jogo> jogosList;
    private LocalDate dataVenda;

    

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
        HashMap<Integer,Integer> mapaJogos = new HashMap<Integer, Integer>();

            
        /*  LANCAMENTO(0),
            PREMIUM(1),
            REGULAR(2),
            PROMOCAO(3); */

        for (Jogo jogo : jogosList) {
            if (jogo.getTipoJogo().getTipo() == 0) {
                mapaJogos.put(0,  mapaJogos.getOrDefault(0,0) + 1);
            }else{
                if (jogo.getTipoJogo().getTipo() == 1) {
                    mapaJogos.put(1, mapaJogos.getOrDefault(1,0)  + 1);
                }else{
                    if (jogo.getTipoJogo().getTipo() == 2) {
                        mapaJogos.put(2, mapaJogos.getOrDefault(2,0)  + 1);
                    }else{
                        if (jogo.getTipoJogo().getTipo() == 3) {
                            mapaJogos.put(3, mapaJogos.getOrDefault(3,0)  + 1);
                        }
                    }
                }
            }
        }
        
        if (mapaJogos.getOrDefault(1,0) >= 2 || mapaJogos.getOrDefault(2,0) >= 4) {
            desconto = 0.1;
        }

        if (mapaJogos.getOrDefault(0,0)>=2 || 
            mapaJogos.getOrDefault(1,0)>=3 || 
            mapaJogos.getOrDefault(2,0)>=5 ||
            (mapaJogos.getOrDefault(1,0)>=2 && jogosList.size() >=3) ||
            (mapaJogos.getOrDefault(2,0)>=3 && (mapaJogos.getOrDefault(0,0) >=1 || mapaJogos.getOrDefault(1,0) >=1))
            ) {
            desconto = 0.2;
        }

        
        return desconto;
    }

    @Override
    public String toString(){

        String s = this.dataVenda.toString() + ": {\n";
        for (Jogo j : this.jogosList) {
            s+= "\t" + j.toString() + "\n";
        }
        s = s + "\tvalor total: R$" + this.calcularValor();
        s +="\n}";
        return s;
    }
    
}

