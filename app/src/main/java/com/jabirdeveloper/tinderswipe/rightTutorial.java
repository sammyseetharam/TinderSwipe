package com.jabirdeveloper.tinderswipe;


import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class rightTutorial extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageview);

        // Adding the gif here using glide library
        Glide.with(this).load(R.drawable.swiperight).into(imageView);
    }
}