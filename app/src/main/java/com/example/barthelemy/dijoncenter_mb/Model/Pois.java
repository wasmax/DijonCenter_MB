package com.example.barthelemy.dijoncenter_mb.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Max on 08/09/2017.
 */
public class Pois implements Serializable{
    String id;
    String type;
    String name;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    Location location;
    Position position;

    public Pois(String id, String type, String name, Location location, Position position) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.location = location;
        this.position = position;
    }

    public Pois() {  }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
