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

public class Type extends AppCompatActivity {

    private ImageView previousButton;
    private Switch sandySwitch;
    private Switch rockySwitch;
    private Switch bothTypesSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        Toolbar topBarType = (Toolbar) findViewById(R.id.TopBarType);
        setSupportActionBar(topBarType);

        previousButton = (ImageView) findViewById(R.id.ImageView_Previous);
        sandySwitch = (Switch)findViewById(R.id.switch_SandyBeach);
        rockySwitch = (Switch)findViewById(R.id.switch_RockyBeach);
        bothTypesSwitch = (Switch)findViewById(R.id.switch_Type_Both);

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Type.this, MainActivity.class);
                startActivity(intent);
            }
        });

        sandySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rockySwitch.setChecked(false);
                    bothTypesSwitch.setChecked(false);
                }
            }
        });

        rockySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sandySwitch.setChecked(false);
                    bothTypesSwitch.setChecked(false);
                }
            }
        });

        bothTypesSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sandySwitch.setChecked(false);
                rockySwitch.setChecked(false);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences sharedPreferences = getSharedPreferences(Keys.SHARED_PREFS, this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        if(sandySwitch.isChecked()){
            Toast.makeText(Type.this, "SANDY CHECKED", Toast.LENGTH_LONG).show();
            editor.putBoolean(Keys.TYPE_KEY,false);
        } else if(rockySwitch.isChecked()){
            Toast.makeText(Type.this, "ROCKY CHECKED", Toast.LENGTH_LONG).show();
            editor.putBoolean(Keys.TYPE_KEY,true);
        }else if(bothTypesSwitch.isChecked()){
            Toast.makeText(Type.this, "BOTH CHECKED", Toast.LENGTH_LONG).show();
            editor.putBoolean(Keys.TYPE_KEY,true);
        }

        editor.commit();
    }
}