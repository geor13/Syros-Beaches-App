package com.example.beachapp;

import android.location.Location;

import java.io.Serializable;

public class SingleBeach implements Serializable {
    private String name;
    private boolean access;
    private boolean organized;
    private boolean type;
    private String description;
    private boolean suppliesNear;
    private double latitude;
    private double longitude;
    private String view;

    public String getName() {
        return name;
    }

    public boolean isAccess() {
        return access;
    }

    public boolean isOrganized() {
        return organized;
    }

    public boolean isType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSuppliesNear() {
        return suppliesNear;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getView() {
        return view;
    }

    public int getDistance(){
        Location locationA = new Location("MIAOULI_SQUARE");
        locationA.setLatitude(MiaouliSqr.LATITUDE);
        locationA.setLongitude(MiaouliSqr.LONGTITUDE);

        Location locationB = new Location("CURRENT_BEACH");
        locationB.setLongitude(longitude);
        locationB.setLatitude(latitude);

        int distance = Math.round(locationA.distanceTo(locationB)/1000);

        return distance;
    }
}
