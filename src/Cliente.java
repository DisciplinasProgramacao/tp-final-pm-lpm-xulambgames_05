import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cliente implements Serializable{

    private String codigo;
    private String nome;
    private String senha;
    private String email;
    private String username;

    private ArrayList<Venda> vendasList;
    private TipoCliente tipoCliente;
    
    public Cliente(String codigo, String nome, String senha, String username, String email, TipoCliente tipoCliente) {
        this.codigo = codigo;
        this.nome = nome;
        this.senha = senha;
        this.username = username;
        this.email = email;
        this.vendasList = new ArrayList<Venda>();
        this.tipoCliente = tipoCliente;
    }
    
    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }
    public String getSenha() {
        return senha;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getCodigo() {
        return this.codigo;
    }

    public double percDesconto(){
        return tipoCliente.getDesconto();
    }
    
    public ArrayList<Venda> getVendasList(){
        return this.vendasList;
    }

    public void addVenda(Venda venda) {
        this.vendasList.add(venda);
    }

    public String historicoCompras(Scanner teclado, boolean filtrarData, boolean filtrarTipoJogo ){
        LocalDate dataIni = LocalDate.MIN;
        LocalDate dataFim = LocalDate.MAX;
        ArrayList<Venda> vendas = this.vendasList;

        while (filtrarData) {
            try {
                System.out.println("\nData inicio historico (formato: dd/MM/yyyy, exemplo: 23/06/2022): ");
                String inicio = teclado.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                dataIni = LocalDate.parse(inicio, formatter);
                System.out.println("\nData fim historico (formato: dd/MM/yyyy, exemplo: 23/06/2022): ");
                String fim = teclado.nextLine();
                dataFim = LocalDate.parse(fim, formatter);
                filtrarData = false;
            } catch (Exception e) {
                System.out.println("Nao foi possivel filtrar a data");
            }
            
        }
         



        

        if(filtrarTipoJogo) {
            System.out.println("\nDigite uma das opções para o tipo do jogo: ");
            int tipo = 0;
            TipoJogo tipoJogoFiltro = null;
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
                        tipoJogoFiltro = TipoJogo.LANCAMENTO;
                            break;
                        case 2:
                        tipoJogoFiltro = TipoJogo.PREMIUM;
                            break;
                        case 3:
                        tipoJogoFiltro = TipoJogo.REGULAR;
                            break;
                        case 4:
                        tipoJogoFiltro = TipoJogo.PROMOCAO;
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
            ArrayList<Venda> vendasFiltrado = new ArrayList<Venda>();
            for(Venda v: vendas ){
                boolean contemTipoJogo = false;
                for (Jogo j : v.getJogosList()) {
                    if(j.getTipoJogo().name().equals(tipoJogoFiltro.name())){
                        contemTipoJogo = true;
                        break;
                    }
                }
                if (contemTipoJogo) {
                    vendasFiltrado.add(v);
                }
            }
            vendas = vendasFiltrado;
        }

        String s = "-----\tHistorico de vendas do cliente de codigo = "+ this.getCodigo() +"\t-----\n\n" ;
        if (this.vendasList.size() == 0) {
            s += "Nenhuma venda foi realizada por esse cliente\n";
        }

        for (Venda v : vendas) {
            if (!(v.getDataVenda().isAfter(dataFim)) && !(v.getDataVenda().isBefore(dataIni))) {
                
                s+= v.toString();
                s+= "\n";
            }
        }
        
        s += "\n----- Fim Historico -----";
        
        return s;
    }
    
    
    @Override
    public String toString(){
        return this.codigo +" - " + this.nome + " - " + this.tipoCliente.name().toUpperCase(); 
    }

} 
