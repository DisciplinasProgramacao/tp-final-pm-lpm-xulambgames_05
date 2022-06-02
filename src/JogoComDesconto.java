public abstract class JogoComDesconto extends Jogo implements IDescontavel{

    
    private static  final double DESCONTO_MIN =0;
    private static  final double DESCONTO_MAX =1;
    private double descontoAplicado;
    
    public JogoComDesconto(String codigo, String titulo, double precoBase, double descontoAplicado){
        super(codigo, titulo, precoBase);
        this.descontoAplicado = descontoAplicado;
    }

    @Override
    public double calcularDesconto(){
        return getPrecoBase() * this.descontoAplicado;
    }
    
    public double calcularValor(){
        return getPrecoBase() - calcularDesconto();
    }

}