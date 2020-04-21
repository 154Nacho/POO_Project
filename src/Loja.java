import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Loja {

    private String codLoja;
    private String nomeLoja;
    private GPS location;
    private List<Encomenda> encomendas;
    private int qtd_pessoas_fila;

    //Getters

    public String getCodLoja() {
        return codLoja;
    }
    public String getNomeLoja() {
        return nomeLoja;
    }
    public GPS getLocation() {
        return location;
    }
    public int getQtd_pessoas_fila() {
        return qtd_pessoas_fila;
    }
    public List<Encomenda> getEncomendas(){
        return this.encomendas.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    //Setters

    public void setNomeLoja(String nome) {
        this.nomeLoja = nome;
    }
    public void setLocation(GPS location) {
        this.location = location;
    }
    public void setCodLoja(String codLoja) {
        this.codLoja = codLoja;
    }
    public void setQtd_pessoas_fila(int qtd) {
        this.qtd_pessoas_fila = qtd;
    }
    public void setEncomendas(List<Encomenda> e){
        this.encomendas = new ArrayList<>();
        e.forEach(this.encomendas.add(e.clone()));
    }
}