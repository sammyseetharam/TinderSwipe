package com.jabirdeveloper.tinderswipe;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class rightTutorial extends AppCompatActivity {

    ImageView imageView;
    Button change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_tutorial);
        imageView = findViewById(R.id.imageview);

        change = findViewById(R.id.begin);



        // Adding the gif here using glide library
        //Glide.with(this).load(R.drawable.swiperight).into(imageView);
    }

    public void tutorialToSong (View view){
        Intent intent1 = new Intent(this, splashScreenActivity.class);
        startActivity(intent1);
    }

}