public enum TipoCliente {
    CADASTRADO(0.0, 0.0),
    EMPOLGADO( 10.0, 0.1),
    FANATICO( 25.0, 0.3);

    private double mensalidade;
    
    private double desconto;
    
    TipoCliente(double mensalidade, double desconto) {
        this.mensalidade = mensalidade;
        this.desconto = desconto;
    }
    public double getMensalidade() {
        return mensalidade;
    }
    
    public double getDesconto() {
        return desconto;
    }
    
    

}
