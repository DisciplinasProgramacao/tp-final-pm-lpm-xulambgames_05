import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;

public class Jogo implements Serializable{


    private String codigo;
    private String titulo;
    private double precoBase;
    private TipoJogo tipoJogo;
    private double percDesconto;

    private void init(String codigo, String titulo, double precoBase, TipoJogo tipoJogo, double percDesconto){
        this.codigo = codigo;
        this.titulo = titulo;
        this.tipoJogo = tipoJogo;
        this.percDesconto = percDesconto;
        BigDecimal bd = BigDecimal.valueOf(precoBase);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        this.precoBase = bd.doubleValue();
    }
    
    public Jogo(String codigo, String titulo, double precoBase, TipoJogo tipoJogo, double percDesconto){

        if (!(tipoJogo.isDescontoValido(percDesconto))) {
            throw new InvalidParameterException("O desconto informado é inválido, verifique as regras do Tipo de jogo em questão!");                
        }
        
        if (precoBase < 0){
            precoBase = 0;
        }
        
        init(codigo, titulo, precoBase, tipoJogo, percDesconto);
    }

    public double getPrecoBase() {
        return precoBase;
    }
    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double calcularValor(){
        return this.precoBase - calcularDesconto();
    }

    public double calcularDesconto(){
        double valorDesconto = precoBase * this.percDesconto;
        BigDecimal bd = BigDecimal.valueOf(valorDesconto);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public TipoJogo getTipoJogo() {
        return this.tipoJogo;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(this.codigo + " - \"" + this.titulo);
        s.append(String.format("\" R$%.2f", this.calcularValor()));
        s.append(" "+ this.getTipoJogo().name().toUpperCase());
        return  s.toString();
    }
}