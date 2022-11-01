package com.jabirdeveloper.tinderswipe;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daprlabs.cardstack.SwipeDeck;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // on below line we are creating variable
    // for our array list and swipe deck.
    private SwipeDeck cardStack;
    private ArrayList<SongTemplate> songTemplateArrayList;
    public ArrayList<SongTemplate> wantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // on below line we are initializing our array list and swipe deck.
        songTemplateArrayList = new ArrayList<>();
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        // on below line we are adding data to our array list.
        songTemplateArrayList.add(new SongTemplate(R.drawable.graduationalbumcover, "I Wonder", "Kanye West"));
        songTemplateArrayList.add(new SongTemplate(R.drawable.graduationalbumcover, "Champion", "Kanye West"));
        songTemplateArrayList.add(new SongTemplate(R.drawable.graduationalbumcover, "Flashing Lights", "Kanye West"));
        songTemplateArrayList.add(new SongTemplate(R.drawable.graduationalbumcover, "Barry Bonds", "Kanye West"));
        songTemplateArrayList.add(new SongTemplate(R.drawable.graduationalbumcover, "Big Brother", "Kanye West"));


        // on below line we are creating a variable for our adapter class and passing array list to it.
        final CardStackAdapter adapter = new CardStackAdapter(songTemplateArrayList, this);

        // on below line we are setting adapter to our card stack.
        cardStack.setAdapter(adapter);

        // on below line we are setting event callback to our card stack.
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                // on card swipe left we are displaying a toast message.
                Toast.makeText(MainActivity.this, "Song Swiped Left", Toast.LENGTH_SHORT).show();
                SongTemplate wantSong = new SongTemplate(songTemplateArrayList.get(position).getAlbumArtImage(), songTemplateArrayList.get(position).getSongName(),
                        songTemplateArrayList.get(position).getArtistName());
                wantList.add(wantSong);
            }

            @Override
            public void cardSwipedRight(int position) {
                // on card swiped to right we are displaying a toast message.
                Toast.makeText(MainActivity.this, "Song Swiped Right", Toast.LENGTH_SHORT).show();
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