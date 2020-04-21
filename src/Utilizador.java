import jdk.jshell.execution.Util;

import java.util.Objects;

public class Utilizador {

    private String codUtilizador;
    private String nomeUtilizador;
    // GPS guardar par de coordenadas latitude,longitude;
    GPS gps;

    public Utilizador() {
        this.codUtilizador = "";
        this.nomeUtilizador = "";
        this.gps = new GPS();
    }

    public Utilizador(Utilizador u) {
        this.codUtilizador = u.getCodUtilizador();
        this.nomeUtilizador = u.getNomeUtilizador();
        this.gps = u.getGps();
    }

    public Utilizador(String codUtilizador, String nomeUtilizador, GPS gps) {
        this.codUtilizador = codUtilizador;
        this.nomeUtilizador = nomeUtilizador;
        this.gps = gps;
    }

    public Utilizador(String codUtilizador, String nomeUtilizador, double latitude, double longitude) {
        this.codUtilizador = codUtilizador;
        this.nomeUtilizador = nomeUtilizador;
        this.gps = new GPS(latitude,longitude);
    }

    public String getCodUtilizador() {
        return codUtilizador;
    }

    public void setCodUtilizador(String codUtilizador) {
        this.codUtilizador = codUtilizador;
    }

    public String getNomeUtilizador() {
        return nomeUtilizador;
    }

    public void setNomeUtilizador(String nomeUtilizador) {
        this.nomeUtilizador = nomeUtilizador;
    }

    public GPS getGps(){
        return this.gps;
    }

    public void setGps(GPS g){
        this.gps = g;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Utilizador:").append(this.codUtilizador).append(",").
                append(this.nomeUtilizador).append(",").append(this.gps.getLatitude()).append(",").append(this.gps.getLongitude());
        return sb.toString();
    }

    public Utilizador clone() {
        return new Utilizador(this);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilizador that = (Utilizador) o;
        return Objects.equals(getCodUtilizador(), that.getCodUtilizador()) &&
                Objects.equals(getNomeUtilizador(), that.getNomeUtilizador()) &&
                Objects.equals(getGps(), that.getGps());
    }

}