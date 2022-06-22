import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Sistema {

    static String path_vendas_bin = "binario/livros.bin";
    static String path_clientes_bin = "binario/clientes.bin";
    static String path_jogos_bin = "binario/clientes.bin";

    public static void salvarClientesBin(){}
    public static void salvarVendasBin(){}
    public static void salvarJogosBin(){}

    public static void carregarJogosBin(){}
    public static void carregarClienteBin(){}
    public static void carregarVendasBin(){}

    public static void menu(){
        Loja loja = new Loja();
        ArrayList<Cliente> clientes = null;
        ArrayList<Jogo> jogos = null;
        ArrayList<Venda> vendas = null;
        
        try {
            clientes = carregarClienteBin(path_clientes_bin);
            jogos = carregarJogosBin(path_livros_bin);
            vendas = carregarVendasBin();
        } catch (Exception e) {
            clientes =  carregarClientesTexto(path_clientes_txt);
            jogos =  carregarJogosTexto(path_livros_txt);
        }

        
        salvarClientesBin(clientes, path_clientes_bin);
        salvarVendasBin(jogos, path_livros_bin);
        salvarJogosBin(jogos, path_livros_bin);


        Scanner teclado = new Scanner(System.in);
        int opcao = 0;        
        do{
            System.out.println("\n\n\nDigite a opcao desejada: ");
            System.out.println("1 - Cadastrar novo cliente");
            System.out.println("2 - Cadastrar novo jogo");
            System.out.println("3 - Realizar nova venda");
            System.out.println("4 - Jogo mais vendido");
            System.out.println("5 - Jogo menos vendido");
            System.out.println("6 - Valor medio compras");
            System.out.println("7 - Valor mensal vendido");

            System.out.println("0 - Sair");
            try{
                opcao = teclado.nextInt();
                teclado.nextLine();
                
            }catch(InputMismatchException ex){
                teclado.nextLine();
                System.out.println("Entrada invalida");
            }


            switch(opcao){
                case 1: 
                    //cadastrar novos clientes
                    try {
                        Cliente novoCliente = loja.cadastrarCliente(teclado);
                        System.out.println("\nCliente cadastrado com sucesso!\n" + novoCliente.toString() + "\n");
                    } catch (Exception e) {
                        System.out.println("\nNao foi possivel cadastrar o cliente! \n" + e.getMessage());
                        
                    }

                    break;
                case 2:
                    //cadastrar novo jogo
                    try {
                        
                        Jogo novoJogo = loja.cadastrarJogo(teclado);
                        System.out.println("\nCliente cadastrado com sucesso!\n" + novoJogo.toString() + "\n" );
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                
                
                case 3:
                    //  Realizar nova venda
                    try {
                        Optional<Venda> novaVenda = Optional.ofNullable(loja.criarVenda(teclado));
                        if (novaVenda.isPresent()) {
                            System.out.println("\nVenda registrada com sucesso!\n" + novaVenda.get() + "\n" );
                        }else{
                            System.out.println("\nOperação cancelada...");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                
                case 4:
                    // Jogo mais vendido
                        try {
                            ArrayList<Jogo> maisVendidos = loja.jogoMaisVendido();
                            if (maisVendidos.isEmpty()) {
                                System.out.println("\nAinda não existe nenhuma venda cadastrada!");
                            }else{
                                
                                System.out.println("\nJogo(s) mais vendido(s) :\n");   
                                for (Jogo j : maisVendidos) {
                                    System.out.println(j);
                                }      
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                case 5:
                    // Jogo menos vendido
                    try {
                        ArrayList<Jogo> menosVendido = loja.jogoMenosVendido();
                        if (menosVendido.isEmpty()) {
                            System.out.println("\nAinda não existe nenhuma venda cadastrada!");
                        }else{
                            System.out.println("\nJogo(s) menos vendido(s) :\n");   
                                for (Jogo j : menosVendido) {
                                    System.out.println(j + "\n");
                                }                                
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 6:
                    // Valor medio compras
                    try {
                        double valor = loja.ValorMedioCompras();     
                        if (valor == -1) {
                            System.out.println("\nAinda não existe nenhuma venda cadastrada!");
                        }
                        System.out.println("\nO valor medio de compras é de R$" + valor);                       
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                        
                case 7:
                    // Valor mensal vendido
                    try {
                        double valor = loja.ValorMensalVendido(teclado);     
                        System.out.println("\nO valor mensal vendido no mes informado foi de R$" + valor);                                  
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                        
                default:
                    System.out.println("Opcao invalida. Selecione um valor dentre os apresentados no menu!");
                    break;

                case 0:
                    teclado.close();
                    System.out.println("Encerrando sistema...");
                    break;
                }
        }while(opcao!=0);
    }
    


    public static void main(String[] args) throws Exception {

        menu();
    }
}
