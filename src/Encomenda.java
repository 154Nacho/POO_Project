import java.util.Map;
import java.util.TreeMap;

public class Encomenda {

    // Variáveis de instância
    private String codEncomenda;
    private String codUtilizador;
    private String codLoja;
    private double peso;
    private Map<String,LinhaEncomenda> encomendas;

    // Contrutor por omissão
    public Encomenda() {
        this.codEncomenda = "";
        this.codUtilizador = "";
        this.codLoja = "";
        this.peso = 0;
        this.encomendas = new TreeMap<>();
    }

    // Construtor parametrizado
    public Encomenda(String codEncomenda, String codUtilizador, String codLoja, Double peso, Map<String,LinhaEncomenda> encomendas) {
        this.codEncomenda = codEncomenda;
        this.codUtilizador = codUtilizador;
        this.codLoja = codLoja;
        this.peso = peso;
        setEncomendas(encomendas);
    }

    // Construtor de cópia
    public Encomenda(Encomenda e) {
        this.codEncomenda = e.getCodEncomenda();
        this.codUtilizador = e.getCodUtilizador();
        this.codLoja = e.getCodLoja();
        this.peso = e.getPeso();
        setEncomendas(e.getEncomendas());
    }

    // Sets e Gets
    public String getCodEncomenda() {
        return codEncomenda;
    }

    public void setCodEncomenda(String codEncomenda) {
        this.codEncomenda = codEncomenda;
    }

    public String getCodUtilizador() {
        return codUtilizador;
    }

    public void setCodUtilizador(String codUtilizador) {
        this.codUtilizador = codUtilizador;
    }

    public String getCodLoja() {
        return codLoja;
    }

    public void setCodLoja(String codLoja) {
        this.codLoja = codLoja;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Map<String, LinhaEncomenda> getEncomendas() {
        return encomendas;
    }

    public void setEncomendas(Map<String, LinhaEncomenda> encomendas) {
        this.encomendas = encomendas;
    }

    // Clone
    public Encomenda clone(){
        return new Encomenda(this);
    }



}