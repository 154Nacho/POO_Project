import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Encomenda {

    // Variáveis de instância
    private String codEncomenda;
    private String codUtilizador;
    private String codLoja;
    private double peso;
    private List<LinhaEncomenda> encomendas;

    // Contrutor por omissão
    public Encomenda() {
        this.codEncomenda = "";
        this.codUtilizador = "";
        this.codLoja = "";
        this.peso = 0;
        this.encomendas = new ArrayList<>();
    }

    // Construtor parametrizado
    public Encomenda(String codEncomenda, String codUtilizador, String codLoja, Double peso, List<LinhaEncomenda> encomendas) {
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

    public List<LinhaEncomenda> getEncomendas() {
        ArrayList<LinhaEncomenda> aux = new ArrayList<>();
        for( LinhaEncomenda l : this.encomendas)
            aux.add(l.clone());
        return aux;
    }

    public void setEncomendas(List<LinhaEncomenda> encomendas) {
        this.encomendas = new ArrayList<>();
        for(LinhaEncomenda l : encomendas)
            this.encomendas.add(l);

    }

    // toString
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Encomenda:").append(this.codEncomenda).append(",").append(this.codUtilizador).append(",").append(this.codLoja).append(",").append(this.peso);
        for(LinhaEncomenda l : this.encomendas)
            sb.append(",").append(l.toString());
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
        this.encomendas.add(l.clone());
    }



}