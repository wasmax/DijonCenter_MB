package com.example.barthelemy.dijoncenter_mb.Model;

/**
 * Created by Max on 08/09/2017.
 */
public class Position {

    public Position(Float lat, Float lon) {
        this.lat = lat;
        this.lon = lon;
    }

    Float lat;

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    Float lon;


}
