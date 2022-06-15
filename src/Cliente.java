import java.util.ArrayList;

public class Cliente {

    private ArrayList<Venda> vendasList;

    public Cliente(){
        this.vendasList = new ArrayList<Venda>();
    }

    public void addVenda(Venda venda) {
        this.vendasList.add(venda);
    }

    public ArrayList<Venda> getVendasList(){
        return this.vendasList;
    }
}
