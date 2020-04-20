import jdk.jshell.execution.Util;

import java.util.Objects;

public class Utilizador {

    private String codUtilizador;
    private String nomeUtilizador;
    // GPS guardar par de coordenadas latitude,longitude;
    private double latitude;
    private double longitude;

    public Utilizador() {
        this.codUtilizador = "";
        this.nomeUtilizador = "";
        this.latitude = 0;
        this.longitude = 0;
    }

    public Utilizador(Utilizador u) {
        this.codUtilizador = u.getCodUtilizador();
        this.nomeUtilizador = u.getNomeUtilizador();
        this.latitude = u.getLatitude();
        this.longitude = u.getLongitude();
    }

    public Utilizador(String codUtilizador, String nomeUtilizador, double latitude, double longitude) {
        this.codUtilizador = codUtilizador;
        this.nomeUtilizador = nomeUtilizador;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Utilizador:").append(this.codUtilizador).append(",").
                append(this.nomeUtilizador).append(",").append(this.latitude).append(",").append(this.longitude);
        return sb.toString();
    }

    public Utilizador clone() {
        return new Utilizador(this);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilizador that = (Utilizador) o;
        return Objects.equals(getCodUtilizador(), that.getCodUtilizador());
    }

}