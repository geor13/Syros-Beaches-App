package com.example.beachapp;

import android.location.Location;

import java.util.ArrayList;

public class Checker {
    private boolean access;
    private boolean organized;
    private boolean type;
    private int distance;
    private int requestDistance;
    private BeachesRes allBeaches;

    public Checker(boolean access, boolean organized, boolean type, int requestDistance, BeachesRes allBeaches){
        this.access = access;
        this.organized = organized;
        this.type = type;
        this.distance = distance;
        this.requestDistance = requestDistance;
        this.allBeaches = allBeaches;
    }

    public ArrayList<SingleBeach> calculator(){
        ArrayList<SingleBeach> myBeach = new ArrayList<>();

        for(int i = 0; i < allBeaches.getBeaches().size(); i++){
            if(allBeaches.getBeaches().get(i).isAccess() == access
                    && allBeaches.getBeaches().get(i).isOrganized() == organized
                    && allBeaches.getBeaches().get(i).isType() == type
                    && allBeaches.getBeaches().get(i).getDistance() <= requestDistance) {
                myBeach.add(allBeaches.getBeaches().get(i));
            }
        }
        return myBeach;
    }
}
