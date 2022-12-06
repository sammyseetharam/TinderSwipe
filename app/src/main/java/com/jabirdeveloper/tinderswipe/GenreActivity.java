package com.jabirdeveloper.tinderswipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class GenreActivity extends AppCompatActivity {

    ImageView imageView1;
    ImageButton change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genre_screen);
        imageView1 = findViewById(R.id.imageview);

        change = findViewById(R.id.imageButtonCountry);



        // Adding the gif here using glide library
        //Glide.with(this).load(R.drawable.swiperight).into(imageView);
    }

    public void tutorialToSong (View view){
        Intent intent1 = new Intent(this, rightTutorial.class);
        startActivity(intent1);
    }

}