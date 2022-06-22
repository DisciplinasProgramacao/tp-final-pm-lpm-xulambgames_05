import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Loja {
    
    private HashMap<String, Cliente> clientesMap;
    public HashMap<String, Cliente> getClientesMap() {
        return clientesMap;
    }
    private HashMap<String, Jogo> jogosMap;
    
    public HashMap<String, Jogo> getJogosMap() {
        return jogosMap;
    }

    public Loja() {
        this.clientesMap = new HashMap<String, Cliente>();
        this.jogosMap = new HashMap<String, Jogo>();
    }

    public double ValorMensalVendido(Scanner teclado){
        int ano = -1;
        int mes = -1;
        boolean dataValida = false;
        while(!dataValida){
            try{
                System.out.println("\nDigite o ano: ");
                mes = teclado.nextInt();
                System.out.println("\nDigite o valor referente ao mes (de 1 a 12): ");
                ano = teclado.nextInt();
                if ((ano > 0 && (mes >0 && mes < 13))) {
                    dataValida = true;
                }else{
                    throw new InputMismatchException();
                }
            }catch(InputMismatchException ex){
                teclado.nextLine();
                System.out.println("\nEntrada invalida! Digite um valor de 1 a 12 para o mes e um valor > 0 para o ano");
            }
        }

        ArrayList<Venda> vendas = new ArrayList<Venda>();
        for (Cliente c : clientesMap.values()) {
            vendas.addAll(c.getVendasList());
        }

        int auxAno = ano;
        int auxMes = mes;
        double arrecadacaoMes = vendas
            .stream()
            .filter((v) -> v.getDataVenda().getYear() ==  auxAno)
            .filter((v) ->v.getDataVenda().getMonthValue() == auxMes)
            .map(Venda::calcularValor)
            .reduce(0.0, (a,b) -> a + b);

        
        return arrecadacaoMes;

    }
    
    /***
     * 
     * @return valor médio de compras(double) de todas as vendas registradas no sistema ou -1 caso não exista nenhuma compra registrada.
     */
    public double ValorMedioCompras(){
        ArrayList<Venda> vendas = new ArrayList<Venda>();
        for (Cliente c : clientesMap.values()) {
            vendas.addAll(c.getVendasList());
        }

        if (vendas.isEmpty()) {
            return -1;
        }

        double totalArrecadado = vendas
            .stream()
            .map(Venda::calcularValor)
            .reduce(0.0, (a, b) -> a + b);
        return totalArrecadado / vendas.size(); 
    }
    
    public ArrayList<Jogo> jogoMaisVendido(){
        ArrayList<Venda> vendas = new ArrayList<Venda>();
        for (Cliente c : clientesMap.values()) {
            vendas.addAll(c.getVendasList());
        }

        HashMap<Jogo, Integer> freqMap = new HashMap<Jogo, Integer>();
        for (Venda v : vendas) {
            v.getJogosList()
                .stream()
                .map((j) -> freqMap.put(j, freqMap.getOrDefault(j, 0) + 1));
        }

        int maxValue =  freqMap.values().stream().mapToInt(n -> n).max().orElse(0);
        ArrayList<Jogo> maisVendidos = new ArrayList<Jogo>();

        if (maxValue > 0){
            freqMap.keySet()
            .stream()
            .filter(j -> freqMap.get(j) == maxValue)
            .map(j -> maisVendidos.add(j));
        }

        return maisVendidos;
    }

    public ArrayList<Jogo> jogoMenosVendido(){
        
        ArrayList<Venda> vendas = new ArrayList<Venda>();
        for (Cliente c : clientesMap.values()) {
            vendas.addAll(c.getVendasList());
        }

        HashMap<Jogo, Integer> freqMap = new HashMap<Jogo, Integer>();
        for (Venda v : vendas) {
            v.getJogosList()
                .stream()
                .map((j) -> freqMap.put(j, freqMap.getOrDefault(j, 0) + 1));
        }

        int minValue =  freqMap.values().stream().mapToInt(n -> n).min().orElse(0);
        ArrayList<Jogo> menosVendidos = new ArrayList<Jogo>();

        if (minValue > 0){
            freqMap.keySet()
            .stream()
            .filter(j -> freqMap.get(j) == minValue)
            .map(j -> menosVendidos.add(j));
        }

        return menosVendidos;
    }
    
    private Venda registrarVenda(Cliente cliente,Venda venda){    
        cliente.addVenda(venda);
        return venda;
    }
    
    public Cliente cadastrarCliente(Scanner teclado) throws Exception{
        System.out.println("\nDigite o codigo do cliente: ");
        String codigo = teclado.nextLine();
        Cliente cliente = null;

        if (this.clientesMap.containsKey(codigo)) {
            throw new Exception("O codigo de cliente informado já foi cadastrado!");
        }

        System.out.println("\nDigite o nome do cliente: ");
        String nome = teclado.nextLine();
        System.out.println("\nDigite a senha do cliente: ");
        String senha = teclado.nextLine();
        System.out.println("\nDigite o username do cliente: ");
        String username = teclado.nextLine();
        System.out.println("\nDigite o email do cliente: ");
        String email = teclado.nextLine();
        System.out.println("\nDigite uma das opções para o tipo do cliente: ");
        int tipo = 0;
        TipoCliente tipoCliente = null;
        do {
            System.out.println("\n1 - Cadastrado");
            System.out.println("\n2 - Empolgado");
            System.out.println("\n3 - Fanatico\n");
            try{
                tipo = teclado.nextInt();
                teclado.nextLine();
                switch (tipo) {
                    case 1:
                    tipoCliente = TipoCliente.CADASTRADO;
                        break;
                    case 2:
                    tipoCliente = TipoCliente.EMPOLGADO;
                        break;
                    case 3:
                    tipoCliente = TipoCliente.FANATICO;
                        break;
                    default:
                        tipo = 0;
                        System.out.println("\nSelecione uma das opcoes apresentadas! ");
                        break;
                }
            }catch(InputMismatchException ex){
                teclado.nextLine();
                System.out.println("Entrada invalida");
            }

        } while (tipo == 0);

        cliente = new Cliente(codigo, nome, senha, username, email, tipoCliente);
        this.clientesMap.put(cliente.getCodigo(), cliente);
        return cliente;
    }

    public Jogo cadastrarJogo(Scanner teclado) throws Exception{
        System.out.println("\nDigite o codigo do jogo: ");
        String codigo = teclado.nextLine();
        Jogo jogo = null;
        
        if (this.jogosMap.containsKey(codigo) || codigo.equals("sair")) {
            throw new Exception("O codigo de jogo informado já foi cadastrado ou é inválido!");
        }

        System.out.println("\nDigite o titulo do jogo: ");
        String titulo = teclado.nextLine();
        Double precoBase = null;
        Double percDesconto = null;
        while (precoBase == null || percDesconto == null) {
            try {
                System.out.println("\nDigite a preco base do jogo: ");
                precoBase = teclado.nextDouble();
                System.out.println("\nDigite o percentual de desconto do jogo: ");
                percDesconto = teclado.nextDouble();
            } catch(InputMismatchException ex){
                teclado.nextLine();
                System.out.println("\nEntrada invalida");
            }
        }

        System.out.println("\nDigite uma das opções para o tipo do jogo: ");

        int tipo = 0;
        TipoJogo tipoJogo = null;
        do {
            System.out.println("\n1 - LANCAMENTO");
            System.out.println("\n2 - PREMIUM");
            System.out.println("\n3 - REGULAR");
            System.out.println("\n4 - PROMOCAO\n");
            try{
                tipo = teclado.nextInt();
                teclado.nextLine();
                switch (tipo) {
                    case 1:
                    tipoJogo = TipoJogo.LANCAMENTO;
                        break;
                    case 2:
                    tipoJogo = TipoJogo.PREMIUM;
                        break;
                    case 3:
                    tipoJogo = TipoJogo.REGULAR;
                        break;
                    case 4:
                    tipoJogo = TipoJogo.PROMOCAO;
                        break;
                    default:
                        tipo = 0;
                        System.out.println("\nSelecione uma das opcoes apresentadas! ");
                        break;
                }
            }catch(InputMismatchException ex){
                teclado.nextLine();
                System.out.println("Entrada invalida");
            }
        } while (tipo == 0);

        jogo = new Jogo(codigo, titulo, precoBase, tipoJogo, percDesconto);
        this.jogosMap.put(jogo.getCodigo(), jogo);
        return jogo;
    }
    
    private ArrayList<Jogo> criarListaJogos(Scanner teclado){
        ArrayList<Jogo> jogos = new ArrayList<Jogo>();
        String codigo = "0";
        int qtd;
        
        do {
            System.out.println("\n---   Jogos cadastrados no sistema   ---");
            for (Jogo j : jogosMap.values()) 
            {
                System.out.println("\n" + j);
            }
            System.out.println("\n----------------------------------------\n");
            System.out.println("Digite o codigo do jogo que deseja (digite \"sair\" para finalizar o carrinho): ");
            codigo = teclado.nextLine();
            
            if ((!jogosMap.containsKey(codigo)) && !(codigo.equals("sair"))) {
                System.out.println("\nO Codigo informado não é válido!");
            }else if(!codigo.equals("sair")){
                // Add item na lista
                qtd = -1;
                while (!(qtd >= 0)) {
                    try {
                        System.out.println("\nDigite a quantidade desse jogo que deseja adquirir (digite 0 se nao deseja adicionar esse jogo): ");
                        qtd = teclado.nextInt();
                        for (int i = 0; i < qtd; i++) {
                            jogos.add(this.jogosMap.get(codigo));
                        }
                    } catch(InputMismatchException ex){
                        qtd = -1;
                        System.out.println("\nEntrada invalida");
                    }
                }
            }
        } while (!codigo.equals("sair"));
            
        return jogos;
    }

    public Venda criarVenda(Scanner teclado) {
        Venda novaVenda = null;
        ArrayList<Jogo> jogos = criarListaJogos(teclado); 
        String codCliente = null;

        do {
            System.out.println("\nDigite o codigo do cliente: ");
            codCliente = teclado.nextLine();
            if (!(this.clientesMap.containsKey(codCliente)) && !(codCliente.equals("sair"))) {
                
                System.out.println("\nO codigo do cliente informado não está cadastrado!");
                
            }else if(!(codCliente.equals("sair"))){
                codCliente = "sair";
                novaVenda = registrarVenda(this.clientesMap.get(codCliente), new Venda(jogos, LocalDate.now()));
            }

        } while (!(codCliente.equals("sair")));
        
        return novaVenda;
    }

}
