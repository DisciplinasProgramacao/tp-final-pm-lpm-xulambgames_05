import java.util.ArrayList;
import java.util.Date;

public class Venda implements IDescontavel{
    private ArrayList<Jogo> jogosList;
    private Date dataVenda;
    private double descontoAplicado;



    public double calcularValor(){
        double valor = 0.0;
        for (Jogo jogo : jogosList) {
            valor += jogo.calcularValor();
        }
        valor -= calcularDesconto();
        return valor;
    }

    @Override
    public double calcularDesconto() {

    }



}
