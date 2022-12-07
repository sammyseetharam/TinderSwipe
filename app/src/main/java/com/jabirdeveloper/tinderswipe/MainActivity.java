package com.jabirdeveloper.tinderswipe;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daprlabs.cardstack.SwipeDeck;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "praneet";
    // on below line we are creating variable
    // for our array list and swipe deck.
    private SwipeDeck cardStack;
    private ArrayList<SongTemplate> songTemplateArrayList = new ArrayList<>();
    public ArrayList<SongTemplate> wantList = new ArrayList<>();

    public static final String countryID = "421Ms54es5s5iOY1H3yJUV";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // on below line we are initializing our array list and swipe deck.

        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);


        // on below line we are adding data to our array list.
        SongTemplate song1 = new SongTemplate(R.drawable.graduationalbumcover, "I Wonder", "By: Kanye West");
        songTemplateArrayList.add(song1);
        SongTemplate song2 = new SongTemplate(R.drawable.graduationalbumcover, "Champion", "By: Kanye West");
        songTemplateArrayList.add(song2);
        SongTemplate song3 = new SongTemplate(R.drawable.graduationalbumcover, "Flashing Lights", "By: Kanye West");
        songTemplateArrayList.add(song3);
        SongTemplate song4 = new SongTemplate(R.drawable.graduationalbumcover, "Barry Bonds", "By: Kanye West");
        songTemplateArrayList.add(song4);
        SongTemplate song5 = new SongTemplate(R.drawable.graduationalbumcover, "Big Brother", "By: Kanye West");
        songTemplateArrayList.add(song5);

        // on below line we are creating a variable for our adapter class and passing array list to it.
        final CardStackAdapter adapter = new CardStackAdapter(songTemplateArrayList, this);

        // on below line we are setting adapter to our card stack.
        cardStack.setAdapter(adapter);

        // on below line we are setting event callback to our card stack.
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                // on card swipe left we are displaying a toast message.
                Toast.makeText(MainActivity.this, "Song discarded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardSwipedRight(int position) {
                // on card swiped to right we are displaying a toast message.
                Toast.makeText(MainActivity.this, "Song added to playlist", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "cardSwipedRight: the position is: " + position);
                wantList.add(songTemplateArrayList.get(position));
            }

            @Override
            public void cardsDepleted() {
                // this method is called when no card is present
                Toast.makeText(MainActivity.this, "No more songs present", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardActionDown() {
                // this method is called when card is swiped down.
                Log.i("TAG", "CARDS MOVED DOWN");
            }

            @Override
            public void cardActionUp() {
                // this method is called when card is moved up.
                Log.i("TAG", "CARDS MOVED UP");
            }
        });
    }
}