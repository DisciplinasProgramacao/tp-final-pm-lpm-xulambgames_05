public class Sistema {

    public void salvarDadosClienteBin(){}
    public void salvarDadosPedidosBin(){}
    public void carregarDadosClienteBin(){}
    public void carregarDadosPedidosBin(){}



    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        JogoPromocao j = new JogoPromocao("codigo", "titulo", 1.0, 0.6);
        System.out.println(j.getDescontoAplicado());
        j.setDescontoAplicado(0.3);
        System.out.println(j.getDescontoAplicado());
    }
}
