package com.jabirdeveloper.tinderswipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splashScreenActivity extends AppCompatActivity {
    Handler handler = new Handler();
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        i = new Intent(this, MainActivity.class);
        runTwo();


    }

    final Runnable r = new Runnable() {
        @Override
        public void run() {
            startActivity(i);
        }
    };

    public void runTwo(){
        handler.postDelayed(r, 3000);
    }
}