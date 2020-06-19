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
    private int distanceQuery;
    private Location locationA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beaches_list);

        myBeaches = new ArrayList<SingleBeach>();
        beachesList = (ListView)findViewById(R.id.beaches_list);

        SharedPreferences sharedPreferences = getSharedPreferences(Keys.SHARED_PREFS, this.MODE_PRIVATE);
        accessQuery = sharedPreferences.getBoolean(Keys.ACCESS_KEY, true);
        typeQuery = sharedPreferences.getBoolean(Keys.TYPE_KEY,true);
        organizedQuery = sharedPreferences.getBoolean(Keys.ORGANISED_KEY, true);
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
                        // Display the first 500 characters of the response string.
//                        textView.setText("Response is: "+ response.substring(0,500));

                        allBeaches = new Gson().fromJson(response, BeachesRes.class);
//
//                        switch (accessQuery){
//                            case "TRANSPORT":
//                                for(int i = 0; i < allBeaches.getBeaches().size(); i++){
//                                    if(allBeaches.getBeaches().get(i).isAccess()){
//                                        myBeaches.add(allBeaches.getBeaches().get(i));
//                                    }
//                                }
//                                break;
//                            case "WALK":
//                                for(int i = 0; i < allBeaches.getBeaches().size(); i++){
//                                    if(!allBeaches.getBeaches().get(i).isAccess()){
//                                        myBeaches.add(allBeaches.getBeaches().get(i));
//                                    }
//                                }
//                                break;
//                            case "BOTH_ACCESS":
//                                for(int i = 0; i < allBeaches.getBeaches().size(); i++){
//                                        myBeaches.add(allBeaches.getBeaches().get(i));
//                                }
//                                break;
//                        }
//
//                        switch(typeQuery){
//                            case "SANDY":
//                                for(int i = 0; i < myBeaches.size(); i++){
//                                    if(myBeaches.get(i).isType()){
//                                        myBeaches.remove(i);
//                                    }
//                                }
//                                break;
//                            case "ROCKY":
//                                for(int i = 0; i < myBeaches.size(); i++){
//                                    if(!myBeaches.get(i).isType()){
//                                        myBeaches.remove(i);
//                                    }
//                                }
//                                break;
//                        }
//
//                        switch (organizedQuery){
//                            case "ORGANIZED":
//                                for(int i = 0; i < myBeaches.size(); i++){
//                                    if(!myBeaches.get(i).isOrganized()){
//                                        myBeaches.remove(i);
//                                    }
//                                }
//                                break;
//                            case "UNORGANIZED":
//                                for(int i = 0; i < myBeaches.size(); i++){
//                                    if(myBeaches.get(i).isOrganized()){
//                                        myBeaches.remove(i);
//                                    }
//                                }
//                                break;
//                        }
//
//                        switch (distanceQuery){
//                            case 0:
//                                for(int i = 0; i < myBeaches.size(); i++){
//                                    Location locationB = new Location("CURRENT_BEACH");
//                                    locationB.setLongitude(myBeaches.get(i).getLongitude());
//                                    locationB.setLatitude(myBeaches.get(i).getLatitude());
//
//                                    int distance = Math.round(locationA.distanceTo(locationB)/1000);
//                                    if(distance > 2){
//                                        myBeaches.remove(myBeaches.get(i));
//                                    }
//                                }
//                                break;
//                            case 5:
//                                for(int i = 0; i < myBeaches.size(); i++){
//                                    Location locationB = new Location("CURRENT_BEACH");
//                                    locationB.setLongitude(myBeaches.get(i).getLongitude());
//                                    locationB.setLatitude(myBeaches.get(i).getLatitude());
//
//                                    int distance = Math.round(locationA.distanceTo(locationB)/1000);
//                                    if(distance > 5){
//                                        myBeaches.remove(myBeaches.get(i));
//                                    }
//                                }
//                                break;
//                            case 10:
//                                for(int i = 0; i < myBeaches.size(); i++){
//                                    Location locationB = new Location("CURRENT_BEACH");
//                                    locationB.setLongitude(myBeaches.get(i).getLongitude());
//                                    locationB.setLatitude(myBeaches.get(i).getLatitude());
//
//                                    int distance = Math.round(locationA.distanceTo(locationB)/1000);
//                                    if(distance > 10){
//                                        myBeaches.add(myBeaches.get(i));
//                                    }
//                                }
//                                break;
//                        }
                        Checker checker = new Checker(accessQuery, organizedQuery, typeQuery, distanceQuery,allBeaches );
                        myBeaches = checker.calculator();

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