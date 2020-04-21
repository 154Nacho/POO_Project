import java.util.Objects;

public class GPS {
    private double latitude;
    private double longitude;

    public GPS() {
        this.latitude = 0;
        this.longitude = 0;
    }

    public GPS(GPS g) {
        this.latitude = g.getLatitude();
        this.longitude = g.getLongitude();
    }

    public GPS(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
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
        sb.append(this.latitude).append(",").append(this.longitude);
        return sb.toString();
    }

    public GPS clone(){
        return new GPS(this);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GPS gps = (GPS) o;
        return Double.compare(gps.getLatitude(), getLatitude()) == 0 &&
                Double.compare(gps.getLongitude(), getLongitude()) == 0;
    }

    /**
     * @brief Distância entre 2 localizações GPS
     * @param that 2º ponto GPS
     * @return Distância
     */
    public double distanceTo(GPS that){
        double lat1 = Math.toRadians(this.getLatitude());
        double lat2 = Math.toRadians(that.getLatitude());
        double lon1 = Math.toRadians(that.getLatitude()-this.getLatitude());
        double lon2 = Math.toRadians(that.getLongitude()-this.getLongitude());

        double a = Math.sin(lon1/2) * Math.sin(lon1/2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(lon2/2) * Math.sin(lon2/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return 6371 * c;

    }
}