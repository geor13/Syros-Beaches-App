package com.example.beachapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class BeachesList extends AppCompatActivity {
    private ListView beachesList;
    private BeachesRes allBeaches;
    private ArrayList<SingleBeach> myBeaches;
    private BeachesAdapter adapter;

    private boolean accessQuery;
    private boolean typeQuery;
    private boolean organizedQuery;
    private String bothAccess;
    private String bothType;
    private String bothOrganized;
    private int distanceQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beaches_list);

        myBeaches = new ArrayList<SingleBeach>();
        beachesList = (ListView)findViewById(R.id.beaches_list);

        SharedPreferences sharedPreferences = getSharedPreferences(Keys.SHARED_PREFS, this.MODE_PRIVATE);
        accessQuery = sharedPreferences.getBoolean(Keys.ACCESS_KEY, true);
        bothAccess = sharedPreferences.getString(Keys.BOTH_ACCESS_KEY, "");

        typeQuery = sharedPreferences.getBoolean(Keys.TYPE_KEY,true);
        bothType = sharedPreferences.getString(Keys.BOTH_TYPE_KEY, "");

        organizedQuery = sharedPreferences.getBoolean(Keys.ORGANISED_KEY, true);
        bothOrganized = sharedPreferences.getString(Keys.BOTH_ORGANIZED_KEY, "");

        distanceQuery = sharedPreferences.getInt(Keys.DISTANCE_KEY, 0);

        generateRequest();
    }

    public void generateRequest(){

        //GONNA USE VOLLEY !!!
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://project1.syros.aegean.gr/~dpsd16066/beach.json";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        allBeaches = new Gson().fromJson(response, BeachesRes.class);

                        Checker checker = new Checker(accessQuery, organizedQuery, typeQuery, distanceQuery,allBeaches );
                        myBeaches = checker.calculator(bothAccess, bothType, bothOrganized);

                        adapter = new BeachesAdapter(BeachesList.this, myBeaches);
                        beachesList.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                textView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
     public void searching (boolean search){

     }
}