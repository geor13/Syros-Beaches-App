package com.example.beachapp;

import java.io.Serializable;

public class SingleBeach implements Serializable {
    private String name;
    private boolean access;
    private boolean organized;
    private boolean type;
    private String description;
    private boolean suppliesNear;
    private String latitude;
    private String longitude;

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

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
