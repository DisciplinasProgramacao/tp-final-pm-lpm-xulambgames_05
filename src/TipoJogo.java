public enum TipoJogo {
    LANCAMENTO(0, "Lancamento"),
    PREMIUM(1, "Premium"),
    REGULAR(2, "Regular"),
    PROMOCAO(3, "Promocao");
    int tipo;
    String descricao;
    
    TipoJogo(int tipo, String descricao){
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public int getTipo(){
        return this.tipo;
    }


}
