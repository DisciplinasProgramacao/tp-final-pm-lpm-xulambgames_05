public class JogoLancamento extends Jogo{

    private static final double ADICIONAL = 0.1;

    public JogoLancamento(String codigo, String titulo, double precoBase){
        super(codigo, titulo, precoBase);        
    }

    public double calcularValor(){
        return getPrecoBase() + calcularAdicional();
    }

    private double calcularAdicional(){
        return getPrecoBase() * JogoLancamento.getAdicional();
    }

    public static double getAdicional(){
        return JogoLancamento.ADICIONAL;
    }
}