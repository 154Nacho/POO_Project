import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Voluntario extends Utilizador {

    private double raio;
    private boolean acceptmedical;
    private List<EncomendaRealizada> enc_done;
    private boolean disponivel;
    private double classificação;
    private int total_entregas;

    /**
     * Construtor padrão
     */
    public Voluntario() {
        super();
        this.raio = 0;
        this.acceptmedical = false;
        this.enc_done = new ArrayList<>();
        this.disponivel = true;
        this.classificação = 0;
        this.total_entregas = 0;
    }

    /**
     * Construtor por cópia
     *
     * @param v Voluntário
     */
    public Voluntario(Voluntario v) {
        super(v);
        this.raio = v.getRaio();
        this.acceptmedical = v.aceitoTransporteMedicamentos();
    }

    /**
     * Construtor por parâmetros
     *
     * @param codVoluntario  Código de Voluntário
     * @param nomeVoluntario Nome do Voluntário
     * @param password       Password do Voluntário
     * @param gps            Localização do Voluntário
     * @param raio           Raio de entrega do Voluntário
     */
    public Voluntario(String codVoluntario, String nomeVoluntario, String password, boolean state,GPS gps, double raio) {
        super(codVoluntario, nomeVoluntario, password, gps);
        this.raio = raio;
        this.acceptmedical = state;
    }
    /**
     * Construtor por parâmetros
     *
     * @param codVoluntario  Código de Voluntário
     * @param nomeVoluntario Nome do Voluntário
     * @param password       Password do Voluntário
     * @param gps            Localização do Voluntário
     * @param raio           Raio de entrega do Voluntário
     */
    public Voluntario(String codVoluntario, String nomeVoluntario, String password,GPS gps, double raio) {
        super(codVoluntario, nomeVoluntario, password, gps);
        this.raio = raio;
        this.acceptmedical = false;
    }


    public boolean aceitoTransporteMedicamentos() {
        return acceptmedical;
    }

    public void aceitaMedicamentos(boolean state) {
        this.acceptmedical = state;
    }

    /**
     * Obtém o raio de entrega do Voluntário
     *
     * @return double
     */
    public double getRaio() {
        return raio;
    }

    /**
     * Definir o raio do Voluntário
     *
     * @param raio de entrega do Voluntário
     */
    public void setRaio(double raio) {
        this.raio = raio;
    }

    /**
     * Método que verifica se um voluntário está disponível para realizar encomendas.
     * @return Disponibilidade do voluntário.
     */
    public boolean isDisponivel() {
        return disponivel;
    }

    /**
     * Método que define a disponibilidade de um voluntário.
     * @param disponivel que é o estado de disponibilidade.
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     * Método que retorna a classificação de um voluntário.
     * @return
     */
    public double getClassificação() {
        return classificação;
    }

    /**
     * Método que define a classficação de um voluntário.
     * @param classificação que é a classficação.
     */
    public void setClassificação(double classificação) {
        this.classificação = classificação;
    }

    /**
     * Método que retorna o total de entregas realizadas por um voluntário.
     * @return Total de entregas realizadas.
     */
    public int getTotal_entregas() {
        return total_entregas;
    }

    /**
     * Método que define o total de entregas realizadas por um voluntário.
     * @param total_entregas que é o número de entregas.
     */
    public void setTotal_entregas(int total_entregas) {
        this.total_entregas = total_entregas;
    }

    /**
     * Método que devolve as encomendas já realizadas por um voluntário.
     * @return Lista com as encomendas já realizadas.
     */
    public List<EncomendaRealizada> getEncomendasRealizadas(){
        List<EncomendaRealizada> aux = new ArrayList<>();
        this.enc_done.stream().forEach(v -> aux.add(v.clone()));
        return aux;
    }

    /**
     * Método que define as encomendas já realizadas por um voluntário.
     * @param e que contém as encomendas realizadas.
     */
    public void setEncomendasRealizadas(List<EncomendaRealizada> e){
        e.stream().map(EncomendaRealizada::clone).forEach(v -> this.enc_done.add(v));
    }

    /**
     * Método que adiciona uma enconenda realizada ao registo.
     * @param c_enc que é o código da encomenda.
     * @param c_util que é o código do utilizador que comprou.
     * @param loja que é a loja a quem comprou.
     * @param te que é o tempo que demorou a realizar a entregar.
     */
    public void addEncomendaRealizada(String c_enc, String c_util, String loja, double te){
        EncomendaRealizada nova = new EncomendaRealizada(c_enc,c_util,loja,te,0);
        this.enc_done.add(nova);
    }

    /**
     * Converte um Voluntário numa String
     *
     * @return String
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Voluntario:").append(this.getCodigo()).append(",").append(this.getNome()).
                append(",").append(this.getGps().getLatitude()).append(",").append(this.getGps().getLongitude()).
                append(",").append(this.getRaio());
        return sb.toString();
    }

    public String toStringShow(){
        return "Voluntário:\n{"+" Código: "+this.getCodigo()+
                "\n  Nome: "+this.getNome()+
                "\n  Localização: "+this.getGps().toString()+
                "\n  Raio: "+this.getRaio()+
                "\n  Accept medical: "+this.aceitoTransporteMedicamentos() + "\n}";
    }
    /**
     * Cria uma cópia do Voluntário
     *
     * @return Voluntário
     */
    public Voluntario clone() {
        return new Voluntario(this);
    }

    /**
     * Verificar se um dado Objeto é igual a este Voluntário
     *
     * @param o Objeto
     * @return boolean
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voluntario v = (Voluntario) o;
        return Double.compare(v.getRaio(), getRaio()) == 0 &&
                Objects.equals(getCodigo(), v.getCodigo()) &&
                Objects.equals(getNome(), v.getNome()) &&
                Objects.equals(getGps(), v.getGps()) &&
                Objects.equals(getPassword(), v.getPassword());
    }
}