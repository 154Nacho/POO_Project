import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        return this.codEncomenda;
    }

    public void setCodEncomenda(String codEncomenda) {
        this.codEncomenda = codEncomenda;
    }

    public String getCodUtilizador() {
        return this.codUtilizador;
    }

    public void setCodUtilizador(String codUtilizador) {
        this.codUtilizador = codUtilizador;
    }

    public String getCodLoja() {
        return this.codLoja;
    }

    public void setCodLoja(String codLoja) {
        this.codLoja = codLoja;
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Map<String,LinhaEncomenda> getEncomendas() {
        return (this.encomendas.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone())));
    }

    public void setEncomendas(Map<String,LinhaEncomenda> encomendas) {
        this.encomendas = new HashMap<>();
        encomendas.entrySet().forEach(e -> this.encomendas.put(e.getKey(),e.getValue().clone()));
    }

    // toString
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.codEncomenda).append(",").append(this.codUtilizador).append(",").append(this.codLoja).append(",").append(this.peso).append(",").append(this.encomendas);
        return sb.toString();
    }

    // Equals
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Encomenda e = (Encomenda) o;
        return (e.getCodEncomenda().equals(this.codEncomenda) &&
                e.getCodUtilizador().equals(this.codUtilizador) &&
                e.getCodLoja().equals(this.codLoja) &&
                e.getPeso() == (this.peso) &&
                e.getEncomendas().equals(this.encomendas));
    }

    // Clone
    public Encomenda clone(){
        return new Encomenda(this);
    }

    // Adicionar uma linha de encomenda
    public void addLinhaEnc(LinhaEncomenda l) {
        this.encomendas.put(l.getCodProduto(),l.clone());
    }



}