import java.util.*;

public class Cliente {
    private String codigo;
    private String nome;
    private String userName;
    private String senha;
    private String email;
    private ArrayList<Venda> arrayList;
    private IPagavel nivel;

    public Cliente(String codigo, String nome, String userName, String senha, 
            String email, ArrayList<Venda> arrayList, IPagavel nivel){
        this.codigo = codigo;
        this.nome = nome;
        this.userName = userName;
        this.senha = senha;
        this.email = email;
        this.arrayList = arrayList;
        this.nivel = nivel;
    }  

    public Venda historicoCompras(){
        return new Venda();
    }

}
