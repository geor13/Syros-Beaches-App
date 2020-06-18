package com.example.beachapp;

import java.io.Serializable;
import java.util.ArrayList;

public class BeachesRes  implements Serializable{
    private ArrayList<SingleBeach> beaches;

    public ArrayList<SingleBeach> getBeaches() {
        return beaches;
    }
}
