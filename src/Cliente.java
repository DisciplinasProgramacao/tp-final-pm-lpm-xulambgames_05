import java.util.ArrayList;

public class Cliente {

    private String codigo;
    private String nome;
    private String senha;
    private String username;
    private String email;
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

    public String getCodigo() {
        return this.codigo;
    }

    public ArrayList<Venda> getVendasList(){
        return this.vendasList;
    }

    public boolean equals(Cliente outro){
        if (this.getCodigo().equals(outro.getCodigo())){
            return true;
        }
        else return false;
    }

    public void addVenda(Venda venda) {
        this.vendasList.add(venda);
    }

    public String historicoCompras(){
        String s = "-----\tHistorico de vendas do cliente: " + this.nome + "\t-----\n\n" ;
        if (this.vendasList.size() == 0) {
            s += "Nenhuma venda foi realizada por esse cliente";
        }

        for (Venda v : this.vendasList) {
            s+= v.toString();
            s+= "\n";
        }
        
        s += "\n----- Fim Historico -----";
        
        return s;
    }



} 
