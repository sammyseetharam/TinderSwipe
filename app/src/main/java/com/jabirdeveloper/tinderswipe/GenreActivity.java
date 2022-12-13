package com.jabirdeveloper.tinderswipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class GenreActivity extends AppCompatActivity {

    ImageButton country;
    ImageButton pop;
    ImageButton hip;
    ImageButton rock;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genre_screen);



        country = (ImageButton) findViewById(R.id.imageButtonCountry);

        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.ifCountry = false;
                MainActivity.ifHip = false;
                MainActivity.ifPop = false;
                MainActivity.ifRock = false;

                Intent intent1 = new Intent(GenreActivity.this, rightTutorial.class);
                startActivity(intent1);
                MainActivity.ifCountry = true;
            }
        });

        pop = (ImageButton) findViewById(R.id.imageButtonPop);

        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.ifCountry = false;
                MainActivity.ifHip = false;
                MainActivity.ifPop = false;
                MainActivity.ifRock = false;

                Intent intent1 = new Intent(GenreActivity.this, rightTutorial.class);
                startActivity(intent1);
                MainActivity.ifPop = true;
            }
        });

        hip = (ImageButton) findViewById(R.id.imageButtonRap);

        hip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.ifCountry = false;
                MainActivity.ifHip = false;
                MainActivity.ifPop = false;
                MainActivity.ifRock = false;

                Intent intent1 = new Intent(GenreActivity.this, rightTutorial.class);
                startActivity(intent1);
                MainActivity.ifHip = true;
            }
        });

        rock = (ImageButton) findViewById(R.id.imageButtonRock);

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.ifCountry = false;
                MainActivity.ifHip = false;
                MainActivity.ifPop = false;
                MainActivity.ifRock = false;

                Intent intent1 = new Intent(GenreActivity.this, rightTutorial.class);
                startActivity(intent1);
                MainActivity.ifRock = true;
            }
        });

    }

    public void signOutClick(View view){
        AuthenticateActivty.signOut(getApplicationContext());
    }

}