package com.example.beachapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageButton searchButton;
    private ImageView nextToAccess;
    private ImageView nextToOrganized;
    private ImageView nextToType;
    private ImageView nextToDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar topBar = (Toolbar) findViewById(R.id.TopBar);
        setSupportActionBar(topBar);

        searchButton = (ImageButton)findViewById(R.id.imageButton_Search);
        nextToAccess = (ImageView) findViewById(R.id.imageView_NextAccess);
        nextToOrganized = (ImageView) findViewById(R.id.imageView_NextOrganized);
        nextToType = (ImageView) findViewById(R.id.imageView_NextType);
        nextToDistance = (ImageView) findViewById(R.id.imageView_NextDistance);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BeachesList.class);

                startActivity(intent);
            }
        });

        nextToAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Access.class);
                startActivity(intent);
            }
        });

        nextToOrganized.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Organized.class);
                startActivity(intent);
            }
        });

        nextToType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Type.class);
                startActivity(intent);
            }
        });

        nextToDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Distance.class);
                startActivity(intent);
            }
        });
    }
}
