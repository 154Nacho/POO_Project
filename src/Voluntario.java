import java.util.Objects;

public class Voluntario extends Utilizador {

    private double raio;
    private boolean acceptmedical;

    /**
     * Construtor padrão
     */
    public Voluntario() {
        super();
        this.raio = 0;
        this.acceptmedical = false;
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