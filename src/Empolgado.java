public class Empolgado implements IPagavel{

    private static final double VALOR_MENSALIDADE = 10.0;
    private static final double DESCONTO = 0.1;

    @Override
    public double calculaDesconto() {
        return DESCONTO;
    }

    @Override
    public double valorMensalidade() {
        return VALOR_MENSALIDADE;
    }
    
}
