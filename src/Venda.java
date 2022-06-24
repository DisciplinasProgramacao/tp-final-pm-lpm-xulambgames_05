import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Venda implements Serializable{
    private ArrayList<Jogo> jogosList;
    private LocalDate dataVenda;
    private IPromocao promoAtiva = null;
    private double valorPago;

    public double getValorPago() {
        return valorPago;
    }

    public ArrayList<Jogo> getJogosList() {
        return jogosList;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Venda(ArrayList<Jogo> jogosList, LocalDate dataVenda, Cliente clienteComprador) {
        this.jogosList = jogosList;
        this.dataVenda = dataVenda;
        this.verificarDescontoCliente(clienteComprador);
    }

    private double somaValorJogos(){
        double valor = 0.0;
        for (Jogo jogo : jogosList) {
            valor += jogo.calcularValor();
        }
        return valor;
    }

    public double calcularValor(){
        double valor = somaValorJogos();
        valor -= calcularDesconto();
        return valor;
    }

    private void verificarPromocoes(){
        if (Promocao10.isAptoDesconto(this)) {
            this.promoAtiva = Promocao10.getInstance();
        }
        if (Promocao20.isAptoDesconto(this)) {
            this.promoAtiva = Promocao20.getInstance();
        }
    }

    public double calcularDesconto() {
        verificarPromocoes();
        double valorDesconto = 0;
        double desconto = 0;
        if (this.promoAtiva != null) {
            desconto = this.promoAtiva.percDesconto();
        }

        valorDesconto = desconto * somaValorJogos();
        BigDecimal bd = BigDecimal.valueOf(valorDesconto);
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        return valorDesconto;
    }

    public void verificarDescontoCliente(Cliente cliente){
        valorPago = calcularValor();
        this.valorPago -= (calcularValor() * cliente.percDesconto());
    }

    @Override
    public String toString(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = this.dataVenda.format(formatter);

        String s = dataFormatada.toString() + ": {\n\n";
        
        for (Jogo j : this.jogosList) {
            s+= "\t" + j.toString() + "\n";
        }
        s = s + String.format("\n\tvalor total: R$%.2f", this.valorPago);
        s +="\n}";
        return s;
    }
    
}

