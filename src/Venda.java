import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Venda{
    private ArrayList<Jogo> jogosList;
    private Calendar dataVenda;

    

    public Calendar getDataVenda() {
        return dataVenda;
    }


    public void setDataVenda(Calendar dataVenda) {
        this.dataVenda = dataVenda;
    }


    public Venda(ArrayList<Jogo> jogosList, Calendar dataVenda) {
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

        mapaJogos.put(0, 0);
        mapaJogos.put(1, 0);
        mapaJogos.put(2, 0);
        mapaJogos.put(3, 0);

        for (Jogo jogo : jogosList) {
            if (jogo.getTipoJogo().getTipo() == 0) {
                mapaJogos.put(0,  mapaJogos.get(0) + 1);
            }else{
                if (jogo.getTipoJogo().getTipo() == 1) {
                    mapaJogos.put(1, mapaJogos.get(1) + 1);
                }else{
                    if (jogo.getTipoJogo().getTipo() == 2) {
                        mapaJogos.put(2, mapaJogos.get(2) + 1);
                    }else{
                        if (jogo.getTipoJogo().getTipo() == 3) {
                            mapaJogos.put(3, mapaJogos.get(3) + 1);
                        }
                    }
                }
            }
            
            
        }
        
        if (mapaJogos.get(1) >= 2 || mapaJogos.get(2) >= 4) {
            desconto = 0.1;
        }
        
        /*    
            LANCAMENTO(0),
            PREMIUM(1),
            REGULAR(2),
            PROMOCAO(3);
         
        */
        

        if (mapaJogos.get(0)>=2 || 
            mapaJogos.get(1)>=3 || 
            mapaJogos.get(2)>=5 ||
            (mapaJogos.get(1)>=2 && jogosList.size() >=3) ||
            (mapaJogos.get(2)>=3 && (mapaJogos.get(0) >=1 || mapaJogos.get(1) >=1))
            ) {
            desconto = 0.2;
        }

        
        return desconto;
    }
    
}

