package com.muralijha.sup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splashscreen extends AppCompatActivity {
    public static  int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(splashscreen.this,MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
