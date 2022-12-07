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


    public static final String countryID = "421Ms54es5s5iOY1H3yJUV";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genre_screen);

        country = (ImageButton) findViewById(R.id.imageButtonCountry);

        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(GenreActivity.this, rightTutorial.class);
                startActivity(intent1);
            }
        });

    }

}