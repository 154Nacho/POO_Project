import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Loja {

    private String codLoja;
    private String nomeLoja;
    private GPS location;
    private List<Encomenda> encomendas;
    private int qtd_pessoas_fila;

    /**
     * @brief Construtor por omissão
     */
    public Loja(){
        this.codLoja = new String();
        this.nomeLoja = new String();
        this.location = new GPS();
        this.qtd_pessoas_fila = 0;
        this.encomendas = new ArrayList<>();
    }
    /**
     * @brief Construtor com passagem de argumentos
     */
    public Loja(String cL, String nL, GPS l, int qt, List<Encomenda> ec){
        this.codLoja = cL;
        this.nomeLoja = nL;
        this.location = l;
        this.qtd_pessoas_fila = qt;
        setEncomendas(ec);
    }

    /**
     * @brief Construtor por clonagem
     */
    public Loja(Loja l){
        this.codLoja = l.getCodLoja();
        this.nomeLoja = l.getNomeLoja();
        this.location = l.getLocation();
        this.qtd_pessoas_fila = l.getQtd_pessoas_fila();
        setEncomendas(l.getEncomendas());
    }
    //Getters

    /**
     * @brief Getter do código da Loja
     */
    public String getCodLoja() {
        return codLoja;
    }
    /**
     * @brief Getter do nome da Loja
     */
    public String getNomeLoja() {
        return nomeLoja;
    }
    /**
     * @brief Getter da localização Loja
     */
    public GPS getLocation() {
        return location;
    }
    /**
     * @brief Getter da quantidade de pessoas na fila da Loja
     */
    public int getQtd_pessoas_fila() {
        return qtd_pessoas_fila;
    }
    /**
     * @brief Getter das encomendas já realizadas pela Loja
     */
    public List<Encomenda> getEncomendas(){
        return this.encomendas.stream().map(Encomenda::clone).collect(Collectors.toList());
    }


    //Setters
    /**
     * @brief Setter do código da Loja
     */
    public void setCodLoja(String codLoja) {
        this.codLoja = codLoja;
    }
    /**
     * @brief Setter do nome da Loja
     */
    public void setNomeLoja(String nome) {
        this.nomeLoja = nome;
    }
    /**
     * @brief Setter da localização Loja
     */
    public void setLocation(GPS location) {
        this.location = location;
    }
    /**
     * @brief setter da quantidade de pessoas na fila da Loja
     */
    public void setQtd_pessoas_fila(int qtd) {
        this.qtd_pessoas_fila = qtd;
    }
    /**
     * @brief Setter das encomendas já realizadas pela Loja
     */
    public void setEncomendas(List<Encomenda> e){
        this.encomendas = new ArrayList<>();
        for(Encomenda ec : e){
            this.encomendas.add(ec.clone());
        }
    }

    //Clone, toString, equals

    /**
     * @brief Cloner da Loja
     */
    public Loja clone(){
        return new Loja(this);
    }

    /**
     * @brief Conversor para String das informações da Loja
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nNome da Loja: ").append(this.nomeLoja);
        sb.append("\nCódigo da Loja: ").append(this.codLoja);
        sb.append("\nLocalização da Loja: ").append(this.location);
        sb.append("\nEncomendas: ").append(this.encomendas);
        return sb.toString();
    }

    /**
     * @brief Comparador de igualdade
     */
    public boolean equals(Object obj){
        if(obj==this) return true;
        if(obj==null || obj.getClass() != this.getClass()) return false;
        Loja l = (Loja) obj;
        return (this.codLoja.equals(l.getCodLoja())
                && this.nomeLoja.equals(l.getNomeLoja())
                && this.location.equals(l.getLocation())
                && this.qtd_pessoas_fila == l.getQtd_pessoas_fila()
                && this.encomendas.equals(l.getEncomendas()));
    }
}