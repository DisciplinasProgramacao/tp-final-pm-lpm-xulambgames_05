public enum TipoJogo {
    LANCAMENTO(-0.1, -0.1),
    PREMIUM(0, 0),
    REGULAR(0.0, 0.3),
    PROMOCAO(0.5, 0.7);
    

    private double descontoMin;
    private double descontoMax;    
    
    TipoJogo( double descontoMin, double descontoMax){
        this.descontoMax = descontoMax;
        this.descontoMin = descontoMin;
    }

    public double getDescontoMin() {
        return descontoMin;
    }
    
    public double getDescontoMax() {
        return descontoMax;
    }

    public boolean isDescontoValido(double percDesconto){
        if (this.descontoMin <= percDesconto && percDesconto <= this.descontoMax) {
            return true;
        }
        return false;
    }


}
