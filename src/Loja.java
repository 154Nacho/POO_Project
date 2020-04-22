import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Loja {

    private String codLoja;
    private String nomeLoja;
    private GPS location;
    private List<Encomenda> encomendas;
    private int qtd_pessoas_fila;

    public Loja(){
        this.codLoja = new String();
        this.nomeLoja = new String();
        this.location = new GPS();
        this.qtd_pessoas_fila = 0;
        this.encomendas = new ArrayList<>();
    }

    public Loja(String cL, String nL, GPS l, int qt, List<Encomenda> ec){
        this.codLoja = cL;
        this.nomeLoja = nL;
        this.location = l;
        this.qtd_pessoas_fila = qt;
        setEncomendas(ec);
    }

    public Loja(Loja l){
        this.codLoja = l.getCodLoja();
        this.nomeLoja = l.getNomeLoja();
        this.location = l.getLocation();
        this.qtd_pessoas_fila = l.getQtd_pessoas_fila();
        setEncomendas(l.getEncomendas());
    }
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
        for(Encomenda ec : e){
            this.encomendas.add(ec.clone());
        }
    }
}