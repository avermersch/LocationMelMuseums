package comtion.example.formation.locationmelmuseums.model;

import com.android.volley.toolbox.StringRequest;

/**
 * Created by Formation on 18/01/2018.
 */

public class Museum {

    private String name;

    private String address;

    private String site;

    private Double latitude;

    private Double longitude;

    public Museum() {
    }

    public String getName() {
        return name;
    }

    public Museum setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Museum setAddress(String adress) {
        this.address = adress;
        return this;
    }

    public String getSite() {
        return site;
    }

    public Museum setSite(String site) {
        this.site = site;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Museum setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Museum setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public String toString() {
        return this.name;
    }
}
