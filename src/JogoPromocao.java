import java.security.InvalidParameterException;

public class JogoPromocao extends JogoComDesconto{

    private static final double DESCONTO_MIN = 0.5;
    private static final double DESCONTO_MAX = 0.7;
    private double descontoAplicado;


    
    
    public JogoPromocao(String codigo, String titulo, double precoBase, double descontoAplicado) throws InvalidParameterException{
        super(codigo, titulo, precoBase, descontoAplicado);
        if (!isDescontoValido(descontoAplicado)){
            throw new InvalidParameterException("O valor de desconto informado é inválido!");
        }
        this.descontoAplicado = descontoAplicado;
    }


        private boolean isDescontoValido(double desconto){
            if(desconto > JogoPromocao.DESCONTO_MIN && desconto < JogoPromocao.DESCONTO_MAX){
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