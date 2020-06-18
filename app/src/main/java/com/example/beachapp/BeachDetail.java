package com.example.beachapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BeachDetail extends AppCompatActivity {
    private SingleBeach paralia;
    private TextView onoma;
    private TextView perigrafi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beach_detail);

        Intent intent = getIntent();
        paralia = (SingleBeach)intent.getSerializableExtra("THE_BEACH");

        onoma = (TextView)findViewById(R.id.name_beach);
        perigrafi = (TextView)findViewById(R.id.details_beach);

        onoma.setText(paralia.getName());
        perigrafi.setText(paralia.getDescription());

    }
}