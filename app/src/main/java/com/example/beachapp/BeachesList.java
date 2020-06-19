package com.example.beachapp;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beaches_list);

        myBeaches = new ArrayList<SingleBeach>();
        beachesList = (ListView)findViewById(R.id.beaches_list);

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

                        for(int i = 0; i < allBeaches.getBeaches().size(); i++){

                            //IF WILL CHANGE ... GONNA SHOW THE ORGANIZED BEACHES
                            if(allBeaches.getBeaches().get(i).isOrganized()){
                                myBeaches.add(allBeaches.getBeaches().get(i));
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
}