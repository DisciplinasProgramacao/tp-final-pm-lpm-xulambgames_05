public enum TipoJogo {
    LANCAMENTO(0, "Lancamento", -0.1, -0.1),
    PREMIUM(1, "Premium", 0, 0),
    REGULAR(2, "Regular", 0.0, 0.3),
    PROMOCAO(3, "Promocao", 0.5, 0.7);
    
    private int tipo;
    private String descricao;
    private double descontoMin;
    private double descontoMax;    
    
    TipoJogo(int tipo, String descricao, double descontoMin, double descontoMax){
        this.tipo = tipo;
        this.descricao = descricao;
        this.descontoMax = descontoMax;
        this.descontoMin = descontoMin;
    }

    public double getDescontoMin() {
        return descontoMin;
    }
    
    public double getDescontoMax() {
        return descontoMax;
    }

    public int getTipo(){
        return this.tipo;
    }

    


}
