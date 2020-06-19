package com.example.beachapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Access extends AppCompatActivity {

    private ImageView previousButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        Toolbar topBarAccess = (Toolbar) findViewById(R.id.TopBarAccess);
        setSupportActionBar(topBarAccess);

        previousButton = (ImageView) findViewById(R.id.ImageView_Previous);

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Access.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
