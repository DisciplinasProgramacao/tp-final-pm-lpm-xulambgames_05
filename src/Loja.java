import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Loja {
    
    private HashMap<String, Cliente> clientesMap;
    private HashMap<String, Jogo> jogosMap;
    
    
    public Loja(ArrayList<Cliente> clientes, ArrayList<Jogo> jogos) {
        this.clientesMap = new HashMap<String, Cliente>();
        this.jogosMap = new HashMap<String, Jogo>();
        clientes.stream().forEach((c) -> clientesMap.put(c.getCodigo(), c));
        jogos.stream().forEach((j) -> jogosMap.put(j.getCodigo(), j));
        
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
                ano = teclado.nextInt();
                teclado.nextLine();
                System.out.println("\nDigite o valor referente ao mes (de 1 a 12): ");
                mes = teclado.nextInt();
                teclado.nextLine();
                if ((ano > 0 && (mes >0 && mes < 13))) {
                    dataValida = true;
                }else{
                    throw new InputMismatchException("\nEntrada invalida! Digite um valor de 1 a 12 para o mes e um valor > 0 para o ano");
                }
            }catch(InputMismatchException ex){
                System.out.println(ex.getMessage());
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
    
    public double ValorMedioCompras() throws Exception{
        ArrayList<Venda> vendas = new ArrayList<Venda>();
        for (Cliente c : clientesMap.values()) {
            vendas.addAll(c.getVendasList());
        }

        if (vendas.isEmpty()) {
            throw new Exception("Ainda n??o existe nenhuma venda cadastrada!");
        }

        double totalArrecadado = vendas
            .stream()
            .map(Venda::getValorPago)
            .reduce(0.0, (a, b) -> a + b);

        double valorMedio = totalArrecadado / vendas.size(); 
        BigDecimal bd = BigDecimal.valueOf(valorMedio);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
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
                .forEach((j) -> freqMap.put(j, freqMap.getOrDefault(j, 0) + 1));
        }
        
        int maxValue =  freqMap.values().stream().mapToInt(n -> n).max().orElse(0);
        ArrayList<Jogo> maisVendidos = new ArrayList<Jogo>();

        if (maxValue > 0){
            freqMap.keySet()
            .stream()
            .filter(j -> freqMap.get(j) == maxValue)
            .forEach(j -> maisVendidos.add(j));
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
                .forEach((j) -> freqMap.put(j, freqMap.getOrDefault(j, 0) + 1));
        }

        int minValue =  freqMap.values().stream().mapToInt(n -> n).min().orElse(0);
        ArrayList<Jogo> menosVendidos = new ArrayList<Jogo>();

        if (minValue > 0){
            freqMap.keySet()
            .stream()
            .filter(j -> freqMap.get(j) == minValue)
            .forEach(j -> menosVendidos.add(j));
        }
        
        return menosVendidos;
    }

    public Venda criarVenda(Scanner teclado) throws Exception {
        Venda novaVenda = null;
        if (this.jogosMap.isEmpty() || this.clientesMap.isEmpty()) {
            throw new Exception("?? necess??rio ao menos um cliente e um jogo cadastrados no sistema para se fazer uma venda!");
        }
        ArrayList<Jogo> jogos = criarListaJogos(teclado); 
        String codCliente = null;
        
        do {
            
            System.out.println(clientesCadastrados());

            System.out.println("\nDigite o codigo do cliente (ou \"sair\" para cancelar a opera????o): ");
            codCliente = teclado.nextLine();
            if (!(this.clientesMap.containsKey(codCliente)) && !(codCliente.equals("sair"))) {
                System.out.println("\nO codigo do cliente informado n??o est?? cadastrado!");
                
            }else if(!(codCliente.equals("sair"))){
                novaVenda = registrarVenda(this.clientesMap.get(codCliente), new Venda(jogos, LocalDate.now(), this.clientesMap.get(codCliente)));
                codCliente = "sair";
            }
            
        } while (!(codCliente.equals("sair")));
        
        return novaVenda;
    }
    
    private Venda registrarVenda(Cliente cliente,Venda venda){   
        venda.verificarDescontoCliente(cliente);
        cliente.addVenda(venda);
        return venda;
    }
    
    private ArrayList<Jogo> criarListaJogos(Scanner teclado){
        ArrayList<Jogo> jogos = new ArrayList<Jogo>();
        String codigo = "0";
        int qtd;
        
        do {
            System.out.println(jogosCadastrados());
            System.out.println("Digite o codigo do jogo que deseja (digite \"sair\" para finalizar o carrinho): ");
            codigo = teclado.nextLine();
            
            if ((!jogosMap.containsKey(codigo)) && !(codigo.equals("sair"))) {
                System.out.println("\nO Codigo informado n??o ?? v??lido!");
            }else if(!codigo.equals("sair")){
                // Add item na lista
                qtd = -1;
                while (!(qtd >= 0)) {
                    try {
                        System.out.println("\nDigite a quantidade desse jogo que deseja adquirir (digite 0 se nao deseja adicionar esse jogo): ");
                        qtd = teclado.nextInt();
                        teclado.nextLine();
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

    public Cliente cadastrarCliente(Scanner teclado) throws Exception{
        System.out.println("\nDigite o codigo do cliente: ");
        String codigo = teclado.nextLine();
        Cliente cliente = null;
        
        if (this.clientesMap.containsKey(codigo)) {
            throw new Exception("O codigo de cliente informado j?? foi cadastrado!");
        }

        System.out.println("\nDigite o nome do cliente: ");
        String nome = teclado.nextLine();
        System.out.println("\nDigite a senha do cliente: ");
        String senha = teclado.nextLine();
        System.out.println("\nDigite o username do cliente: ");
        String username = teclado.nextLine();
        System.out.println("\nDigite o email do cliente: ");
        String email = teclado.nextLine();
        System.out.println("\nDigite uma das op????es para o tipo do cliente: ");
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
        System.out.println("\nDigite o codigo do jogo ( ou \"sair\" para cancelar a operacao): ");
        String codigo = teclado.nextLine();
        Jogo jogo = null;
        
        if (codigo.equals("sair")){
            throw new Exception("Opera????o cancelada pelo cliente...");
        }
        if (this.jogosMap.containsKey(codigo)) {
            throw new Exception("O codigo de jogo informado j?? foi cadastrado!");
        }

        System.out.println("\nDigite uma das op????es para o tipo do jogo: ");

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

        System.out.println("\nDigite o titulo do jogo: ");
        String titulo = teclado.nextLine();
        Double precoBase = null;
        Double percDesconto = null;
        while (precoBase == null || percDesconto == null) {
            try {
                System.out.println("\nDigite a preco base do jogo: ");
                precoBase = teclado.nextDouble();
                teclado.nextLine();
                if (tipoJogo.getDescontoMin() != tipoJogo.getDescontoMax()) {
                    System.out.println("\nDigite o percentual de desconto do jogo (entre " + tipoJogo.getDescontoMin() +" e " + tipoJogo.getDescontoMax() + "): " );
                    percDesconto = teclado.nextDouble();
                    teclado.nextLine();
                }else{
                    percDesconto = tipoJogo.getDescontoMin();
                }

                if (!tipoJogo.isDescontoValido(percDesconto)) {
                    precoBase = null;
                    percDesconto = null;
                    System.out.println("O valor deve ser entre " + tipoJogo.getDescontoMin() +" e " + tipoJogo.getDescontoMax() + "!");
                }
            } catch(InputMismatchException ex){
                teclado.nextLine();
                System.out.println("\nEntrada invalida");
            }
        }

        
        jogo = new Jogo(codigo, titulo, precoBase, tipoJogo, percDesconto);
        this.jogosMap.put(jogo.getCodigo(), jogo);
        return jogo;
    }
    
    public String clientesCadastrados(){
        StringBuilder s = new StringBuilder();
        s.append("\n---   Clientes cadastrados no sistema   ---");
        for (Cliente cli : clientesList()) 
        {
            s.append("\n" + cli);
        }
        s.append("\n----------------------------------------\n");
        return s.toString();
    }

    public String jogosCadastrados(){
        StringBuilder s = new StringBuilder();
        s.append("\n---   Jogos cadastrados no sistema   ---");
        for (Jogo j : jogosList()) 
        {
            s.append("\n" + j);
        }
        s.append("\n----------------------------------------\n");
        return s.toString();
    }

    public String historicoCliente(Scanner teclado) throws Exception {
        System.out.println("\nDigite o codigo do cliente: ");
        String codigo = teclado.nextLine();

        if (!this.clientesMap.containsKey(codigo)) {
            throw new Exception("O codigo de cliente informado n??o est?? cadastrado!");
        }
        int opData = -1;
        int opTipo = -1;
        boolean filtrarData = false;
        boolean filtrarTipo = false;

        while ((opData != 0 && opData != 1) &&
                (opTipo != 0 && opTipo != 1)) {
            try {
                
           
                System.out.println("\nDeseja filtrar pela data? (1 para sim e 0 para nao): ");
                opData = teclado.nextInt();
                teclado.nextLine();
                
                switch (opData) {
                    case 1:
                            filtrarData = true;
                        break;
                    case 0:
                            filtrarData = false;
                        break;
                    
                    default:
                        System.out.println("\nOpcao invalida!");
                        break;
                }
                System.out.println("\nDeseja filtrar pelo tipo do jogo? (1 para sim e 0 para nao): ");
                opTipo = teclado.nextInt();
                teclado.nextLine();
                
                switch (opTipo) {
                    case 1:
                            filtrarTipo = true;
                        break;
                    case 0:
                            filtrarTipo = false;
                        break;
                    
                    default:
                        System.out.println("\nOpcao invalida!");
                        break;
                }
            }catch(InputMismatchException ex){
                teclado.nextLine();
                System.out.println("Entrada invalida");
            }
        }
        return this.clientesMap.get(codigo).historicoCompras(teclado, filtrarData, filtrarTipo);
    }

    public ArrayList<Cliente> clientesList(){
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        this.clientesMap.values().stream().forEach((c) -> clientes.add(c));
        return clientes;
    }

    public ArrayList<Jogo> jogosList(){
        ArrayList<Jogo> jogos = new ArrayList<Jogo>();
        this.jogosMap.values().stream().forEach((j) -> jogos.add(j));
        return jogos;
    }
}
