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

    public String getCodEmpresa() {
        return codEmpresa;
    }
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }
    public String getNIF() {
        return nif;
    }
    public GPS getGPS() {
        return location;
    }
    public double getRaio() {
        return raio;
    }
    public double getPPK() {
        return precoPorKM;
    }
    public double getCT() {
        return custo_transporte;
    }
    public int getNMaximo() {
        return encomendas_maximas;
    }
    public boolean getDisponibilidade() {
        return disponivel;
    }
    public LocalDateTime getTempoEntrega() {
        return tempo_entrega;
    }
    public double getTT() {
        return taxa_transporte;
    }
    public List<Encomenda> getRegisto(){
        return this.register.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    //Setters

    public void setCodEmpresa(String cod){
        this.codEmpresa = cod;
    }
    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }
    public void setLocation(GPS location) {
        this.location = location;
    }
    public void setRaio(double raio) {
        this.raio = raio;
    }
    public void setPPK(double precoPorKM) {
        this.precoPorKM = precoPorKM;
    }
    public void setTT(double taxa_transporte) {
        this.taxa_transporte = taxa_transporte;
    }
    public void setCT(double custo_transporte) {
        this.custo_transporte = custo_transporte;
    }
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    public void setTempoEntrega(LocalDateTime tempo_entrega) {
        this.tempo_entrega = tempo_entrega;
    }
    public void setNMaximo(int encomendas_maximas) {
        this.encomendas_maximas = encomendas_maximas;
    }
    public void setNif(String nif) {
        this.nif = nif;
    }
    public void setRegisto(List<Encomenda> l){
        this.register = new ArrayList<>();
        l.forEach( e -> this.register.add(e.clone()));
    }

    //Equals, toString , clone

    public Transportadora clone(){
        return new Transportadora(this);
    }

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
