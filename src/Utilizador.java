import java.util.Objects;

public class Utilizador {

    GPS gps; // GPS guardar par de coordenadas latitude,longitude;
    private String codigo;
    private String nome;
    private String password;

    /**
     * Construtor padrão
     */
    public Utilizador() {
        this.codigo = "";
        this.nome = "";
        this.password = "";
        this.gps = new GPS();
    }

    /**
     * Construtor por cópia
     *
     * @param u Utilizador
     */
    public Utilizador(Utilizador u) {
        this.codigo = u.getCodigo();
        this.nome = u.getNome();
        this.password = u.getPassword();
        this.gps = u.getGps();
    }

    /**
     * Construtor por parâmetros
     *
     * @param codigo   Código de Utilizador
     * @param nome     Nome do Utilizador
     * @param password Password do Utilizador
     * @param gps      Localização do Utilizador
     */
    public Utilizador(String codigo, String nome, String password, GPS gps) {
        this.codigo = codigo;
        this.nome = nome;
        this.password = password;
        this.gps = gps;
    }

    /**
     * Obtém a password do Utilizador
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Definir a password do Utilizador
     *
     * @param password Password do Utilizador
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtém o código do Utilizador
     *
     * @return String
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Definir o Código do Utilizador
     *
     * @param codigo Código do Utilizador
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtém o nome do Utilizador
     *
     * @return String
     */
    public String getNome() {
        return nome;
    }

    /**
     * Definir o Nome do Utilizador
     *
     * @param nome Nome do Utilizador
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o GPS do Utilizador
     *
     * @return GPS
     */
    public GPS getGps() {
        return this.gps;
    }

    /**
     * Definir o GPS do Utilizador
     *
     * @param g GPS do Utilizador
     */
    public void setGps(GPS g) {
        this.gps = g;
    }

    /**
     * Converte um Utilizador numa String
     *
     * @return String
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Utilizador:").append(this.codigo).append(",").
                append(this.nome).append(",").append(this.gps.getLatitude()).append(",").append(this.gps.getLongitude());
        return sb.toString();
    }

    public String toStringShow(){
        return "Utilizador:"+
                "\n{ Código: "+this.getCodigo()+
                "\n  Nome: "+this.getNome()+
                "\n  Localização: "+this.getGps().toString()+
                "\n}";
    }
    /**
     * Cria uma cópia do Utilizador
     *
     * @return Utilizador
     */
    public Utilizador clone() {
        return new Utilizador(this);
    }

    /**
     * Verificar se um dado Objeto é igual a este Utilizaodr
     *
     * @param o Objeto
     * @return boolean
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilizador that = (Utilizador) o;
        return Objects.equals(getCodigo(), that.getCodigo()) &&
                Objects.equals(getNome(), that.getNome()) &&
                Objects.equals(getPassword(), that.getPassword()) &&
                Objects.equals(getGps(), that.getGps());
    }

}