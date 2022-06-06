public class Fanatico implements IPagavel{

    private static final double VALOR_MENSALIDADE = 25.0;
    private static final double DESCONTO = 0.3;

    @Override
    public double calculaDesconto() {
        return DESCONTO; 
    }

    @Override
    public double valorMensalidade() {
        return VALOR_MENSALIDADE;
    }
    
}
