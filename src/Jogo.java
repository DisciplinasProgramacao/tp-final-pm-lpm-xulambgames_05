public abstract class Jogo{

    private String codigo;
    private String titulo;
    private double precoBase;


    public Jogo(String codigo, String titulo, double precoBase){
        this.codigo = codigo;
        this.titulo = titulo;
        
        if (precoBase < 0){
            precoBase = 0;
        }
        
        this.precoBase = precoBase;
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
        return this.precoBase;
    }

}