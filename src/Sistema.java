import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;


public class Sistema {

    static final String PATH_CLIENTES_BIN = "data_bin/clientes.bin";
    static final String PATH_JOGOS_BIN = "data_bin/jogos.bin";

    private static void salvarBin(Serializable data, String path)   throws Exception{
        File arquivo = new File(path);
        arquivo.getParentFile().mkdirs();
        arquivo.createNewFile();
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(path)));
        oos.writeObject(data);
    }

    private static Object carregarBin(String path) throws Exception{
        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(path)));
        return ois.readObject();
    }

    public static void salvarClientesBin(ArrayList<Cliente>  clientes) throws Exception{
        salvarBin(clientes, PATH_CLIENTES_BIN);
    }
    public static void salvarJogosBin(ArrayList<Jogo> jogos) throws Exception{
        salvarBin(jogos, PATH_JOGOS_BIN);
    }


    public static ArrayList<Jogo>  carregarJogosBin() throws Exception{
        return (ArrayList<Jogo>) carregarBin(PATH_JOGOS_BIN);
    }
    public static ArrayList<Cliente> carregarClienteBin() throws Exception{
        return (ArrayList<Cliente>) carregarBin(PATH_CLIENTES_BIN);
    }

    public static void menu(Loja loja){
        
        Scanner teclado = new Scanner(System.in);
        int opcao = 0;        
        do{
            System.out.println("\nDigite a opcao desejada: ");
            System.out.println("1  - Cadastrar novo cliente");
            System.out.println("2  - Cadastrar novo jogo");
            System.out.println("3  - Realizar nova venda");
            System.out.println("4  - Jogo mais vendido");
            System.out.println("5  - Jogo menos vendido");
            System.out.println("6  - Valor medio compras");
            System.out.println("7  - Valor mensal vendido");
            System.out.println("8  - Historico de venda de um cliente");
            System.out.println("9  - Visualizar clientes cadastrados");
            System.out.println("10 - Visualizar jogos cadastrados");

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
                        System.out.println("\nJogo cadastrado com sucesso!\n" + novoJogo.toString() + "\n" );
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        
                    }

                    break;
                
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

                case 8:
                    // Historico de compras de um cliente
                    try {
                        String historico = loja.historicoCliente(teclado);     
                        System.out.println(historico);                                  
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 9:
                    // Visualizar clientes cadastrados
                    try {
                        String cadastrados = loja.clientesCadastrados();     
                        System.out.println(cadastrados);                                  
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 10:
                    // Visualizar jogos cadastrados
                    try {
                        String cadastrados = loja.jogosCadastrados();     
                        System.out.println(cadastrados);                                   
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                        
                default:
                    System.out.println("\nOpcao invalida. Selecione um valor dentre os apresentados no menu!");
                    break;

                case 0:
                    teclado.close();
                    System.out.println("\nEncerrando sistema...");
                    break;
                }
        }while(opcao!=0);
    }
    


    public static void main(String[] args) throws Exception {
        Loja loja;
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        ArrayList<Jogo> jogos = new ArrayList<Jogo>();
        

        try {            
            clientes = carregarClienteBin();
            jogos = carregarJogosBin();
            loja =  new Loja(clientes, jogos);
            System.out.println("\nArquivos binarios carregados com sucesso");
        } catch (Exception e) {
            loja = new Loja();
            System.out.println("\nNao foi possivel carregar arquivos binarios");
        }
        
        menu(loja);

        try {
            // loja.initTest();
            salvarClientesBin(loja.clientesList());
            salvarJogosBin(loja.jogosList());
        } catch (Exception e) {
            System.out.println("Erro ao registrar os arquivos binarios!");
            System.out.println(e.getMessage());
        }
    }
}
