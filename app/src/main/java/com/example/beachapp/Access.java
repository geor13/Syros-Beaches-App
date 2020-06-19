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

public class Access extends AppCompatActivity {

    private ImageView previousButton;
    private Switch transportSwitch;
    private Switch walkSwitch;
    private Switch bothSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        Toolbar topBarAccess = (Toolbar) findViewById(R.id.TopBarAccess);
        setSupportActionBar(topBarAccess);

        previousButton = (ImageView) findViewById(R.id.ImageView_Previous);
        transportSwitch = (Switch)findViewById(R.id.switch_Transport);
        walkSwitch = (Switch)findViewById(R.id.switch_Walk);
        bothSwitch = (Switch)findViewById(R.id.switch_Access_Both);


        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Access.this, MainActivity.class);
                startActivity(intent);
            }
        });

        transportSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    walkSwitch.setChecked(false);
                    bothSwitch.setChecked(false);

                }
            }
        });

        walkSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    transportSwitch.setChecked(false);
                    bothSwitch.setChecked(false);
                }
            }
        });

        bothSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    walkSwitch.setChecked(false);
                    transportSwitch.setChecked(false);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences sharedPreferences = getSharedPreferences(Keys.SHARED_PREFS, this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(transportSwitch.isChecked()){
            Toast.makeText(Access.this, "TRANSPORT CHECKED", Toast.LENGTH_LONG).show();
            editor.putBoolean(Keys.ACCESS_KEY,true);
        } else if(walkSwitch.isChecked()){
            Toast.makeText(Access.this, "WALK CHECKED", Toast.LENGTH_LONG).show();
            editor.putBoolean(Keys.ACCESS_KEY,false);
        }else if(bothSwitch.isChecked()){
            Toast.makeText(Access.this, "BOTH CHECKED", Toast.LENGTH_LONG).show();
            editor.putBoolean(Keys.ACCESS_KEY,true);
        }

        editor.commit();
    }
}
