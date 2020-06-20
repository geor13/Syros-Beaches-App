package com.example.beachapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BeachDetail extends AppCompatActivity {
    private SingleBeach paralia;
    private TextView onoma;
    private TextView perigrafi;
    private ImageView beachImage;
    private TextView otherPotions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beach_detail);

        Intent intent = getIntent();
        paralia = (SingleBeach)intent.getSerializableExtra("THE_BEACH");

        Toolbar topBar = (Toolbar) findViewById(R.id.DetailTopBar);
        setSupportActionBar(topBar);

        onoma = (TextView)findViewById(R.id.textView5);
        perigrafi = (TextView)findViewById(R.id.textView_Description);
        beachImage = (ImageView)findViewById(R.id.imageView_PhotoBeach);
        otherPotions = (TextView)findViewById(R.id.GoToMain);

        onoma.setText(paralia.getName());
        perigrafi.setText(paralia.getDescription());

        Glide.with(this).load(paralia.getView()).into(beachImage);

        otherPotions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BeachDetail.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}