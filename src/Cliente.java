import java.io.Serializable;
import java.util.ArrayList;

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

    public String historicoCompras(){
        String s = "-----\tHistorico de vendas do cliente de codigo = "+ this.getCodigo() +"\t-----\n\n" ;
        if (this.vendasList.size() == 0) {
            s += "Nenhuma venda foi realizada por esse cliente\n";
        }

        for (Venda v : this.vendasList) {
            s+= v.toString();
            s+= "\n";
        }
        
        s += "\n----- Fim Historico -----";
        
        return s;
    }

    @Override
    public String toString(){
        return this.codigo +" - " + this.nome + " - " + this.tipoCliente.name().toUpperCase(); 
    }

} 
