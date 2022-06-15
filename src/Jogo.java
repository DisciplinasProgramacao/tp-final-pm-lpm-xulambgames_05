public class Jogo{


    private String codigo;
    private String titulo;
    private double precoBase;
    private TipoJogo tipoJogo = null;


    public Jogo(String codigo, String titulo, double precoBase, TipoJogo tipoJogo){
        this.codigo = codigo;
        this.titulo = titulo;
        
        if (precoBase < 0){
            precoBase = 0;
        }
        
        this.precoBase = precoBase;
        this.tipoJogo = tipoJogo;
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

    public TipoJogo getTipoJogo() {
        return this.tipoJogo;
    }

    public boolean equals(Jogo outro){
        if (outro.getCodigo().equals(this.getCodigo())) {
            return true;
        }
        return false;
    }
}