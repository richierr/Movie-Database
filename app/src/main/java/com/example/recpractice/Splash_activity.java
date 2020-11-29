package com.example.recpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Splash_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences=getSharedPreferences("com.example.recpractice_preferences",MODE_PRIVATE);
        boolean showSplash=sharedPreferences.getBoolean("show_splash",true);
        int splash_length=sharedPreferences.getInt("seek_bar_value",1);
        if(showSplash){
        setContentView(R.layout.activity_splash_activity);

            new Handler().postDelayed(() -> {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }, (splash_length*1000));
        }else{
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}