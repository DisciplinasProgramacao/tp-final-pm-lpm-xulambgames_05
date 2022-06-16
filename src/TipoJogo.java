public enum TipoJogo {
    LANCAMENTO(0,  -0.1, -0.1),
    PREMIUM(1, 0, 0),
    REGULAR(2,  0.0, 0.3),
    PROMOCAO(3,  0.5, 0.7);
    
    private int tipo;
    private double descontoMin;
    private double descontoMax;    
    
    TipoJogo(int tipo, double descontoMin, double descontoMax){
        this.tipo = tipo;
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

    public boolean isDescontoValido(double percDesconto){
        if (this.descontoMin <= percDesconto && percDesconto <= this.descontoMax) {
            return true;
        }
        return false;
    }


}
