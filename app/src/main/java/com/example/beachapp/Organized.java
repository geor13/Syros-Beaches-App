package com.example.beachapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class Organized extends AppCompatActivity {

    private ImageView previousButton;
    private Switch yesSwitch, noSwitch, bothOrganisedSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organized);

        Toolbar topBarOrganized = (Toolbar) findViewById(R.id.TopBarOrganized);
        setSupportActionBar(topBarOrganized);

        previousButton = (ImageView) findViewById(R.id.ImageView_Previous);
        yesSwitch = (Switch)findViewById(R.id.switch_Yes);
        noSwitch = (Switch)findViewById(R.id.switch_No);
        bothOrganisedSwitch = (Switch)findViewById(R.id.switch_Org_Both);

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Organized.this, MainActivity.class);
                startActivity(intent);
            }
        });

        yesSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    noSwitch.setChecked(false);
                    bothOrganisedSwitch.setChecked(false);
                }
            }
        });

        noSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    yesSwitch.setChecked(false);
                    bothOrganisedSwitch.setChecked(false);
                }
            }
        });

        bothOrganisedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    yesSwitch.setChecked(false);
                    noSwitch.setChecked(false);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences sharedPreferences = getSharedPreferences(Keys.SHARED_PREFS, this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(yesSwitch.isChecked()){
            Toast.makeText(Organized.this, "ORGANIZED CHECKED", Toast.LENGTH_SHORT).show();
            editor.putBoolean(Keys.ORGANISED_KEY,true);
            editor.putString(Keys.BOTH_ORGANIZED_KEY,"");
        } else if(noSwitch.isChecked()){
            Toast.makeText(Organized.this, "UNORGANIZED CHECKED", Toast.LENGTH_SHORT).show();
            editor.putBoolean(Keys.ORGANISED_KEY,false);
            editor.putString(Keys.BOTH_ORGANIZED_KEY,"");
        }else if(bothOrganisedSwitch.isChecked()){
            Toast.makeText(Organized.this, "BOTH ORGANIZED CHECKED", Toast.LENGTH_SHORT).show();
            editor.putString(Keys.BOTH_ORGANIZED_KEY,"BOTH_ORGANIZED");
        }

        editor.commit();
    }
}