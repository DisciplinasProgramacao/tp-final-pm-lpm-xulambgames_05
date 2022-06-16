
public enum TipoCliente {
    CADASTRADO(0, 0.0, 0.0),
    EMPOLGADO(1, 10.0, 0.0),
    FANATICO(2, 25.0, 0.3);

    private int tipo;
    private double mensalidade;
    private double desconto;

    TipoCliente(int tipo, double mensalidade, double desconto) {
        this.tipo = tipo;
        this.mensalidade = mensalidade;
        this.desconto = desconto;
    }



}
