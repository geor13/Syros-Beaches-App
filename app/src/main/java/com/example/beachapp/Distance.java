package com.example.beachapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.security.Key;

public class Distance extends AppCompatActivity {

    private ImageView previousButton;
    private SeekBar distance;
    private int userValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);

        Toolbar topBarDistance = (Toolbar) findViewById(R.id.TopBarDistance);
        setSupportActionBar(topBarDistance);

        previousButton = (ImageView) findViewById(R.id.ImageView_Previous);
        distance = (SeekBar)findViewById(R.id.seekBar2);
        userValue = 0;

        previousButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Distance.this, MainActivity.class);
                startActivity(intent);
            }
        });

        distance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                userValue = progress;
                Toast.makeText(Distance.this, "THE VALUE IS "+userValue, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences sharedPreferences = getSharedPreferences(Keys.SHARED_PREFS, this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch (userValue){
            case 1:
                editor.putInt(Keys.DISTANCE_KEY,5);
                break;

            case 2:
                editor.putInt(Keys.DISTANCE_KEY, 10);
                break;
            case 0:
                editor.putInt(Keys.DISTANCE_KEY, 2);
                break;
        }

        editor.commit();
    }
}