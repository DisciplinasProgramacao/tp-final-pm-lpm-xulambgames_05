import java.security.InvalidParameterException;

public class JogoRegular extends JogoComDesconto{
    
    private static final double DESCONTO_MIN = 0.0;
    private static final double  DESCONTO_MAX = 0.3;
    private double descontoAplicado;

    
    public JogoRegular(String codigo, String titulo, double precoBase, double descontoAplicado){
        super(codigo, titulo, precoBase, descontoAplicado);     
    }
    
    public static double getDescontoMax() {
        return JogoRegular.DESCONTO_MAX;
    }   
    public static double getDescontoMin() {
        return JogoRegular.DESCONTO_MIN;
    }

    private boolean isDescontoValido(double desconto){
        if(desconto > JogoRegular.DESCONTO_MIN && desconto < JogoRegular.DESCONTO_MAX){
            return true;
        }
        return false;
    }

    public void setDescontoAplicado(double desconto) {
        if (!isDescontoValido(desconto)) {
            throw new InvalidParameterException("O valor de desconto informado é inválido!");
        }
        this.descontoAplicado = desconto;    
    }    
    public double getDescontoAplicado() {
        return descontoAplicado;
    }

}