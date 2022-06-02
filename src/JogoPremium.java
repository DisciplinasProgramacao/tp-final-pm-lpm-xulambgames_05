public class JogoPremium extends Jogo{


    public JogoPremium(String codigo, String titulo, double precoBase){
        super(codigo, titulo, precoBase);        
    }

    public double calcularValor(){
        return this.getPrecoBase();
    }
    
}