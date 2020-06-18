package com.example.beachapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        //GONNA USE RETROFIT LIBRARY

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://project1.syros.aegean.gr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //USING THE RETROFIT INTERFACE I BUILT
        JsonPlaceholderApi placeholder = retrofit.create(JsonPlaceholderApi.class);

        Call<BeachesRes> call = placeholder.getAllBeaches("beach.json");

        call.enqueue(new Callback<BeachesRes>() {
            @Override
            public void onResponse(Call<BeachesRes> call, Response<BeachesRes> response) {
                if(!response.isSuccessful()){
                    return;
                }

                allBeaches = response.body();

                for(int i = 0; i < allBeaches.getBeaches().size(); i++){
                    if(allBeaches.getBeaches().get(i).isOrganized()){
                        myBeaches.add(allBeaches.getBeaches().get(i));
                    }
                }
                adapter = new BeachesAdapter(BeachesList.this, myBeaches);
                beachesList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<BeachesRes> call, Throwable t) {

            }
        });

    }
}