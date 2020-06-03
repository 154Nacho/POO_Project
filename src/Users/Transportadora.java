package Users;

import Geral.GPS;
import Stock.EncomendaRealizada;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Transportadora extends User {

    private String nome;
    private GPS gps;
    private String nif;
    private double raio;
    private double precoPorKM;
    private double custo_transporte;
    private boolean disponivel;
    private int encomendas_maximas;
    private List<EncomendaRealizada> register;
    private double classificação;
    private int total_entregas;

    /**
     * Construtor por omissão
     */
    public Transportadora() {
        super();
        this.nif = new String();
        this.raio = 0;
        this.precoPorKM = 0;
        this.custo_transporte = 0;
        this.disponivel = true;
        this.encomendas_maximas = 0;
        this.register = new ArrayList<>();
        this.classificação = 0;
        this.total_entregas = 0;
    }

    /**
     * Construtor com passagem de argumentos
     */
    public Transportadora(String cE, String nE, GPS l, String password, String n, double r, double ppk, double ct, boolean d, int em, List<EncomendaRealizada> reg, double cf, int qtd) {
        super(cE, password);
        this.nome = nE;
        this.gps = l;
        this.nif = n;
        this.raio = r;
        this.precoPorKM = ppk;
        this.custo_transporte = ct;
        this.disponivel = d;
        this.encomendas_maximas = em;
        setRegisto(reg);
        this.classificação = cf;
        this.total_entregas = qtd;
    }

    /**
     * Construtor por clonagem
     */
    public Transportadora(Transportadora t) {
        super(t);
        this.nome = t.getNome();
        this.gps = t.getGPS();
        this.nif = t.getNIF();
        this.raio = t.getRaio();
        this.precoPorKM = t.getPPK();
        this.custo_transporte = t.getCT();
        this.disponivel = t.getDisponibilidade();
        this.encomendas_maximas = t.getNMaximo();
        this.register = t.getRegisto();
        this.classificação = t.getClassificação();
        this.total_entregas = t.getTotal_entregas();
    }

    //Getters


    public void setCodigo(String code){
        super.setCode(code);
    }
    /**
     *   Getter do NIF da Empresa
     */
    public String getNIF() {
        return nif;
    }


    public String getNome(){
        return this.nome;
    }

    public GPS getGPS(){
        return this.gps.clone();
    }

    /**
     *   Getter do raio de admissão de encomendas da Empresa
     */
    public double getRaio() {
        return raio;
    }

    /**
     *   Setter do raio de admissão de encomendas da Empresa
     */
    public void setRaio(double raio) {
        this.raio = raio;
    }

    /**
     *   Getter do preço por quilómetro de transporte da Empresa
     */
    public double getPPK() {
        return precoPorKM;
    }

    /**
     *   Setter do preço por quilómetro de transporte da Empresa
     */
    public void setPPK(double precoPorKM) {
        this.precoPorKM = precoPorKM;
    }

    /**
     *   Getter do custo de transporte da Empresa
     */
    public double getCT() {
        return custo_transporte;
    }

    /**
     *   Setter do custo de transporte da Empresa
     */
    public void setCT(double custo_transporte) {
        this.custo_transporte = custo_transporte;
    }

    /**
     *   Getter do número máximo de encomendas que a Empresa suporta
     */
    public int getNMaximo() {
        return encomendas_maximas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGps(GPS gps) {
        this.gps = gps.clone();
    }

    /**
     *   Setter do número máximo de encomendas que a Empresa suporta
     */
    public void setNMaximo(int encomendas_maximas) {
        this.encomendas_maximas = encomendas_maximas;
    }

    //Setters

    /**
     *   Getter da disponibilidade da Empresa para novas encomendas
     */
    public boolean getDisponibilidade() {
        return disponivel;
    }

    /**
     *   Getter das encomendas já realizadas pela Empresa
     */
    public List<EncomendaRealizada> getRegisto() {
        return this.register.stream().map(EncomendaRealizada::clone).collect(Collectors.toList());
    }

    /**
     *   Setter das encomendas já realizadas pela Empresa
     */
    public void setRegisto(List<EncomendaRealizada> l) {
        this.register = new ArrayList<>();
        l.forEach(e -> this.register.add(e.clone()));
    }

    /**
     *   Setter da disponibilidade da Empresa para novas encomendas
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     *   Setter do NIF da Empresa
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Método que devolve a classificação de uma Empresa Users.Transportadora.
     * @return Classifcação.
     */
    public double getClassificação() {
        return classificação;
    }

    /**
     * Método que define a classificação de uma Empresa Users.Transportadora.
     * @param classificação que é a classificação.
     */
    public void setClassificação(double classificação) {
        this.classificação = classificação;
    }

    /**
     * Método que devolve o total de entregas realizadas por uma Empresa Users.Transportadora.
     * @return Total de entregas.
     */
    public int getTotal_entregas() {
        return total_entregas;
    }

    /**
     * Método que define o total de vendas realizadas por uma Empresa Users.Transportadora.
     * @param total_entregas que é o número total de entregas.
     */
    public void setTotal_entregas(int total_entregas) {
        this.total_entregas = total_entregas;
    }


    public double calculaFat(LocalDateTime i, LocalDateTime f){
        double aux = 0;
        for(EncomendaRealizada e : this.register){
            if(e.getData_entrega().isAfter(i) && e.getData_entrega().isBefore(f)){
                aux += e.getCusto_transporte();
            }
        }
        return aux;
    }

    public void calculaCustoTransporte(GPS loja, double peso){
        this.custo_transporte = this.gps.distanceTo(loja)*this.precoPorKM + peso*0.05;
    }


    //Equals, toString , clone

    /**
     *   Cloner da Users.Transportadora
     */
    public Transportadora clone() {
        return new Transportadora(this);
    }

    /**
     *   Comparador de igualdade
     */
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Transportadora t = (Transportadora) obj;
        return (this.getCode().equals(t.getCode())
                && this.getNome().equals(t.getNome())
                && this.nif.equals(t.getNIF())
                && this.getGPS().equals(t.getGPS())
                && this.precoPorKM == t.getPPK()
                && this.raio == t.getRaio()
                && this.custo_transporte == t.getCT()
                && this.encomendas_maximas == t.getNMaximo()
                && this.disponivel == t.getDisponibilidade()
                && this.register.equals(t.getRegisto())
                && this.classificação == t.getClassificação()
                && this.total_entregas == t.getTotal_entregas());
    }

    /**
     *   Conversor para String das informações da Users.Transportadora
     */
    public String toStringShow() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nEmpresa: ").append(this.getNome()).append("\nCódigo: ").append(this.getCode()).append("\nNIF: ").append(this.nif);
        sb.append("\nLocalização: ").append(this.getGPS());
        sb.append("\nRaio de transporte: ").append(this.raio);
        sb.append("\nCustos:\n");
        sb.append("\nCusto do transporte: ").append(this.custo_transporte);
        sb.append("\nTaxa de transporte: ").append(this.precoPorKM);
        sb.append("\nEncomendas realizadas:\n").append(this.register);
        return sb.toString();
    }

    public String toString(){
        return "Transportadora:"+this.getCode()+","+this.getNome()+","+this.getGPS().toString()+","+this.getNIF()+","+this.getRaio()+","+this.getPPK();
    }
}
