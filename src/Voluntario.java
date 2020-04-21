import java.util.Objects;

public class Voluntario {

    private String codVoluntario;
    private String nomeVoluntario;
    // GPS?
    GPS gps;
    private double raio;

    public Voluntario() {
        this.codVoluntario = "";
        this.nomeVoluntario = "";
        this.gps = new GPS();
        this.raio = 0;
    }

    public Voluntario(Voluntario v) {
        this.codVoluntario = v.getCodVoluntario();
        this.nomeVoluntario = v.getNomeVoluntario();
        this.gps = v.getGps();
        this.raio = v.getRaio();
    }

    public Voluntario(String codVoluntario, String nomeVoluntario, GPS gps, double raio) {
        this.codVoluntario = codVoluntario;
        this.nomeVoluntario = nomeVoluntario;
        this.gps = gps;
        this.raio = raio;
    }

    public Voluntario(String codVoluntario, String nomeVoluntario, double latitude, double longitude, double raio) {
        this.codVoluntario = codVoluntario;
        this.nomeVoluntario = nomeVoluntario;
        this.gps = new GPS(latitude,longitude);
        this.raio = raio;
    }

    public String getCodVoluntario() {
        return codVoluntario;
    }

    public void setCodVoluntario(String codVoluntario) {
        this.codVoluntario = codVoluntario;
    }

    public String getNomeVoluntario() {
        return nomeVoluntario;
    }

    public void setNomeVoluntario(String nomeVoluntario) {
        this.nomeVoluntario = nomeVoluntario;
    }

    public GPS getGps(){
        return this.gps;
    }

    public void setGps(GPS g){
        this.gps = g;
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Voluntario:").append(this.codVoluntario).append(",").append(this.nomeVoluntario).
                append(",").append(this.gps.getLatitude()).append(",").append(this.gps.getLongitude()).
                append(",").append(this.raio);
        return sb.toString();
    }

    public Voluntario clone(){
        return new Voluntario(this);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voluntario that = (Voluntario) o;
        return Double.compare(that.getRaio(), getRaio()) == 0 &&
                Objects.equals(getCodVoluntario(), that.getCodVoluntario()) &&
                Objects.equals(getNomeVoluntario(), that.getNomeVoluntario()) &&
                Objects.equals(getGps(), that.getGps());
    }
}