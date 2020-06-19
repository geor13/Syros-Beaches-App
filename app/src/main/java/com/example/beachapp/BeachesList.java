package com.example.beachapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
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
    private String accessQuery;
    private String typeQuery;
    private String organizedQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beaches_list);

        myBeaches = new ArrayList<SingleBeach>();
        beachesList = (ListView)findViewById(R.id.beaches_list);

        SharedPreferences sharedPreferences = getSharedPreferences(Keys.SHARED_PREFS, this.MODE_PRIVATE);
        accessQuery = sharedPreferences.getString(Keys.ACCESS_KEY, "");
        typeQuery = sharedPreferences.getString(Keys.TYPE_KEY,"");
        organizedQuery = sharedPreferences.getString(Keys.ORGANISED_KEY, "");

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

                        switch (accessQuery){
                            case "TRANSPORT":
                                for(int i = 0; i < allBeaches.getBeaches().size(); i++){
                                    if(allBeaches.getBeaches().get(i).isAccess()){
                                        myBeaches.add(allBeaches.getBeaches().get(i));
                                    }
                                }
                            case "WALK":
                                for(int i = 0; i < allBeaches.getBeaches().size(); i++){
                                    if(!allBeaches.getBeaches().get(i).isAccess()){
                                        myBeaches.add(allBeaches.getBeaches().get(i));
                                    }
                                }
                            case "BOTH_ACCESS":
                                for(int i = 0; i < allBeaches.getBeaches().size(); i++){
                                        myBeaches.add(allBeaches.getBeaches().get(i));
                                }
                        }

                        switch(typeQuery){
                            case "SANDY":
                                for(int i = 0; i < myBeaches.size(); i++){
                                    if(myBeaches.get(i).isType()){
                                        myBeaches.remove(i);
                                    }
                                }
                            case "ROCKY":
                                for(int i = 0; i < myBeaches.size(); i++){
                                    if(!myBeaches.get(i).isType()){
                                        myBeaches.remove(i);
                                    }
                                }
                        }

                        switch (organizedQuery){
                            case "ORGANIZED":
                                for(int i = 0; i < myBeaches.size(); i++){
                                    if(!myBeaches.get(i).isOrganized()){
                                        myBeaches.remove(i);
                                    }
                                }
                            case "UNORGANIZED":
                                for(int i = 0; i < myBeaches.size(); i++){
                                    if(myBeaches.get(i).isOrganized()){
                                        myBeaches.remove(i);
                                    }
                                }
                        }


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