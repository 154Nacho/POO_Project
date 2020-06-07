package Users;

import Geral.GPS;
import Stock.Encomenda;
import Stock.EncomendaRealizadaTransportadora;
import Stock.EncomendaRealizadaVoluntario;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Transportadora extends User implements  Serializable {

    private String nome;
    private GPS gps;
    private String nif;
    private double raio;
    private double precoPorKM;
    private boolean disponivel;
    private int encomendas_maximas;
    private List<EncomendaRealizadaTransportadora> register;
    private List<Encomenda> on_hold;
    private double classificação;
    private int total_aval;
    private int total_entregas;
    private int km_total;

    /**
     * Construtor por omissão
     */
    public Transportadora() {
        super();
        this.nif = new String();
        this.raio = 0;
        this.precoPorKM = 0;
        this.disponivel = true;
        this.encomendas_maximas = 0;
        this.register = new ArrayList<>();
        this.on_hold = new ArrayList<>();
        this.classificação = 0;
        this.total_entregas = 0;
        this.total_aval = 0;
        this.km_total = 0;
    }

    /**
     * Construtor com passagem de argumentos
     */
    public Transportadora(String cE, String nE,String password, GPS l, String n, double r, double ppk, int em) {
        super(cE, password);
        this.nome = nE;
        this.gps = l;
        this.nif = n;
        this.raio = r;
        this.precoPorKM = ppk;
        this.disponivel = true;
        this.encomendas_maximas = em;
        this.register = new ArrayList<>();
        this.on_hold = new ArrayList<>();
        this.classificação = 0;
        this.total_entregas = 0;
        this.total_aval = 0;
        this.km_total = 0;
    }

    /**
     * Construtor por clonagem
     */
    public Transportadora(Transportadora t) {
        super(t);
        this.nome = t.getNome();
        this.on_hold = t.getOnHold();
        this.gps = t.getGPS();
        this.nif = t.getNIF();
        this.raio = t.getRaio();
        this.precoPorKM = t.getPPK();
        this.disponivel = t.getDisponibilidade();
        this.encomendas_maximas = t.getNMaximo();
        this.register = t.getRegisto();
        this.classificação = t.getClassificação();
        this.total_entregas = t.getTotal_entregas();
        this.total_aval = t.getTotalAval();
        this.total_aval = t.getTotalKM();
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
    public List<EncomendaRealizadaTransportadora> getRegisto() {
        return this.register.stream().map(EncomendaRealizadaTransportadora::clone).collect(Collectors.toList());
    }

    /**
     *   Setter das encomendas já realizadas pela Empresa
     */
    public void setRegisto(List<EncomendaRealizadaTransportadora> l) {
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


    public int getTotalAval() {
        return total_aval;
    }

    public void setTotalAval(int total_aval) {
        this.total_aval = total_aval;
    }

    public void updateClassificacao(int classifica){
        this.total_aval++;
        this.classificação = (this.classificação + classifica)/this.total_aval;
    }

    public double calculaFat(LocalDateTime i, LocalDateTime f){
        double aux = 0;
        for(EncomendaRealizadaTransportadora e : this.register){
            if(e.getData_entrega().isAfter(i) && e.getData_entrega().isBefore(f)){
                aux += e.getCusto_transporte();
            }
        }
        return aux;
    }

    /**
     * Método que devolve uma lista com encomendas à espera de aceitação do utilizador.
     * @return Lista com as encomendas.
     */
    public List<Encomenda> getOnHold(){
        return this.on_hold.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    /**
     * Método que define a lista de encomendas a espera de resposta do utilizador.
     * @param a Lista com as encomendas.
     */
    public void setOnHold(List<Encomenda> a){
        a.stream().map(Encomenda::clone).forEach(v -> this.on_hold.add(v));
    }


    /**
     * Método que adiciona uma enconenda realizada ao registo.
     * @param c_enc que é o código da encomenda.
     * @param c_util que é o código do utilizador que comprou.
     * @param loja que é a loja a quem comprou.
     * @param te que é o tempo que demorou a realizar a entregar.
     */
    public void addEncomendaRealizada(String c_enc, String c_util, String loja, double te,double peso, double preco,double dist){
        EncomendaRealizadaTransportadora nova = new EncomendaRealizadaTransportadora(c_enc,c_util,loja,te,peso*0.23+dist*this.precoPorKM,preco + dist*this.precoPorKM + peso*0.23,LocalDateTime.now());
        this.km_total += dist;
        this.register.add(nova);
        this.total_entregas++;
    }

    /**
     * Método que adiciona uma encomenda a lista de espera por aceitação de um utilizador.
     * @param e Encomenda.
     */
    public void addEncomendaParaAceitar(Encomenda e){
        this.on_hold.add(e.clone());
    }


    /**
     * Método que devolve o total de quilómetros que uma Transportadora percorreu.
     * @return Total de quilóemtros.
     */
    public int getTotalKM() {
        return km_total;
    }

    /**
     * Métodon que define o total de quilómetros percorridos por uma Transportadora.
     * @param km_total que é o tatal de quilóemtros percorridos.
     */
    public void setKm_total(int km_total) {
        this.km_total = km_total;
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
        sb.append("\nTaxa de transporte: ").append(this.precoPorKM);
        sb.append("\nEncomendas realizadas:\n").append(this.register);
        return sb.toString();
    }

    public String toString(){
        return "Transportadora:"+this.getCode()+","+this.getNome()+","+this.getGPS().toString()+","+this.getNIF()+","+this.getRaio()+","+this.getPPK();
    }
}
