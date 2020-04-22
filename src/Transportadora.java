import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Transportadora {

    private String codEmpresa;
    private String nomeEmpresa;
    private GPS location;
    private String nif;
    private double raio;
    private double precoPorKM;
    private double taxa_transporte;
    private double custo_transporte;
    private boolean disponivel;
    private LocalDateTime tempo_entrega;
    private int encomendas_maximas;
    private List<Encomenda> register;

    /**
     * @brief Construtor por omissão
     */
    public Transportadora(){
        this.codEmpresa = new String();
        this.nomeEmpresa = new String();
        this.location = new GPS();
        this.nif = new String();
        this.raio = 0;
        this.precoPorKM = 0;
        this.taxa_transporte = 0;
        this.custo_transporte = 0;
        this.disponivel = true;
        this.tempo_entrega = null;
        this.encomendas_maximas = 0;
        this.register = new ArrayList<>();
    }

    /**
     * @brief Construtor com passagem de argumentos
     */
    public Transportadora(String cE, String nE, GPS l, String n, double r, double ppk, double tt, double ct, boolean d, int em, List<Encomenda> reg){
        this.codEmpresa = cE;
        this.nomeEmpresa = nE;
        this.location = l;
        this.nif = n;
        this.raio = r;
        this.precoPorKM = ppk;
        this.taxa_transporte = tt;
        this.custo_transporte = ct;
        this.disponivel = d;
        this.tempo_entrega = null;
        this.encomendas_maximas = em;
        setRegisto(reg);
    }

    /**
     * @brief Construtor por clonagem
     */
    public Transportadora(Transportadora t) {
        this.codEmpresa = t.getCodEmpresa();
        this.nomeEmpresa = t.getNomeEmpresa();
        this.location = t.getGPS();
        this.nif = t.getNIF();
        this.raio = t.getRaio();
        this.precoPorKM = t.getPPK();
        this.taxa_transporte = t.getTT();
        this.custo_transporte = t.getCT();
        this.disponivel = t.getDisponibilidade();
        this.tempo_entrega = t.getTempoEntrega();
        this.encomendas_maximas = t.getNMaximo();
        setRegisto(t.getRegisto());
    }

    //Getters

    /**
     * @brief Getter do código da Empresa
     */
    public String getCodEmpresa() {
        return codEmpresa;
    }
    /**
     * @brief Getter do nome da Empresa
     */
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }
    /**
     * @brief Getter do NIF da Empresa
     */
    public String getNIF() {
        return nif;
    }
    /**
     * @brief Getter do localização da Empresa
     */
    public GPS getGPS() {
        return location;
    }
    /**
     * @brief Getter do raio de admissão de encomendas da Empresa
     */
    public double getRaio() {
        return raio;
    }
    /**
     * @brief Getter do preço por quilómetro de transporte da Empresa
     */
    public double getPPK() {
        return precoPorKM;
    }
    /**
     * @brief Getter do custo de transporte da Empresa
     */
    public double getCT() {
        return custo_transporte;
    }
    /**
     * @brief Getter do número máximo de encomendas que a Empresa suporta
     */
    public int getNMaximo() {
        return encomendas_maximas;
    }
    /**
     * @brief Getter da disponibilidade da Empresa para novas encomendas
     */
    public boolean getDisponibilidade() {
        return disponivel;
    }
    /**
     * @brief Getter do tempo de entrega da encomenda
     */
    public LocalDateTime getTempoEntrega() {
        return tempo_entrega;
    }
    /**
     * @brief Getter da taxa de transporte da Empresa
     */
    public double getTT() {
        return taxa_transporte;
    }
    /**
     * @brief Getter das encomendas já realizadas pela Empresa
     */
    public List<Encomenda> getRegisto(){
        return this.register.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    //Setters
    /**
     * @brief Setter do código da Empresa
     */
    public void setCodEmpresa(String cod){
        this.codEmpresa = cod;
    }
    /**
     * @brief Setter do nome da Empresa
     */
    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }
    /**
     * @brief Setter do localização da Empresa
     */
    public void setLocation(GPS location) {
        this.location = location;
    }
    /**
     * @brief Setter do raio de admissão de encomendas da Empresa
     */
    public void setRaio(double raio) {
        this.raio = raio;
    }
    /**
     * @brief Setter do preço por quilómetro de transporte da Empresa
     */
    public void setPPK(double precoPorKM) {
        this.precoPorKM = precoPorKM;
    }
    /**
     * @brief Setter da taxa de transporte da Empresa
     */
    public void setTT(double taxa_transporte) {
        this.taxa_transporte = taxa_transporte;
    }
    /**
     * @brief Setter do custo de transporte da Empresa
     */
    public void setCT(double custo_transporte) {
        this.custo_transporte = custo_transporte;
    }
    /**
     * @brief Setter da disponibilidade da Empresa para novas encomendas
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    /**
     * @brief Setter do tempo de entrega da encomenda
     */
    public void setTempoEntrega(LocalDateTime tempo_entrega) {
        this.tempo_entrega = tempo_entrega;
    }
    /**
     * @brief Setter do número máximo de encomendas que a Empresa suporta
     */
    public void setNMaximo(int encomendas_maximas) {
        this.encomendas_maximas = encomendas_maximas;
    }
    /**
     * @brief Setter do NIF da Empresa
     */
    public void setNif(String nif) {
        this.nif = nif;
    }
    /**
     * @brief Setter das encomendas já realizadas pela Empresa
     */
    public void setRegisto(List<Encomenda> l){
        this.register = new ArrayList<>();
        l.forEach( e -> this.register.add(e.clone()));
    }

    //Equals, toString , clone

    /**
     * @brief Cloner da Transportadora
     */
    public Transportadora clone(){
        return new Transportadora(this);
    }

    /**
     * @brief Conversor para String das informações da Transportadora
     */
    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        Transportadora t = (Transportadora) obj;
        return ( this.codEmpresa.equals(t.getCodEmpresa())
                && this.nomeEmpresa.equals(t.getNomeEmpresa())
                && this.nif.equals(t.getNIF())
                && this.location.equals(t.getGPS())
                && this.precoPorKM == t.getPPK()
                && this.raio == t.getRaio()
                && this.taxa_transporte == t.getTT()
                && this.custo_transporte == t.getCT()
                && this.encomendas_maximas == t.getNMaximo()
                && this.disponivel == t.getDisponibilidade()
                && this.tempo_entrega.isEqual(t.getTempoEntrega())
                && this.register.equals(t.getRegisto()));
    }

    /**
     * @brief Comparador de igualdade
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nEmpresa: ").append(this.nomeEmpresa).append("\nCódigo: ").append(this.codEmpresa).append("\nNIF: ").append(this.nif);
        sb.append("\nLocalização: ").append(this.location);
        sb.append("\nRaio de transporte: ").append(this.raio);
        sb.append("\nCustos:\n");
        sb.append("\nCusto do transporte: ").append(this.custo_transporte);
        sb.append("\nTaxa de transporte: ").append(this.taxa_transporte);
        sb.append("\nEncomendas realizadas:\n").append(this.register);
        return sb.toString();
    }
}
