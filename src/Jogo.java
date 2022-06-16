import java.security.InvalidParameterException;

public class Jogo{


    private String codigo;
    private String titulo;
    private double precoBase;
    private TipoJogo tipoJogo;
    private double percDesconto;

    private void init(String codigo, String titulo, double precoBase, TipoJogo tipoJogo, double percDesconto){
        this.codigo = codigo;
        this.titulo = titulo;
        this.precoBase = precoBase;
        this.tipoJogo = tipoJogo;
        this.percDesconto = percDesconto;
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
        return precoBase * this.percDesconto;
    }

    public TipoJogo getTipoJogo() {
        return this.tipoJogo;
    }

    public boolean equals(Jogo outro){
        if (outro.getCodigo().equals(this.getCodigo())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return this.codigo + " - " + this.titulo + " R$" + this.calcularValor() +" "+ this.getTipoJogo().name().toUpperCase();
    }
}