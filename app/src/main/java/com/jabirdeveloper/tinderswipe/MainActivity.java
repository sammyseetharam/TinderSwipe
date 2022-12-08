package com.jabirdeveloper.tinderswipe;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daprlabs.cardstack.SwipeDeck;
import com.jabirdeveloper.tinderswipe.Connectors.SongService;
import com.jabirdeveloper.tinderswipe.Model.Playlist;
import com.jabirdeveloper.tinderswipe.Model.Song;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "padmuhamed";
    // on below line we are creating variable
    // for our array list and swipe deck.
    private SwipeDeck cardStack;
    public static ArrayList<SongTemplate> songTemplateArrayList = new ArrayList<>();
    public ArrayList<SongTemplate> wantList = new ArrayList<>();
    public static SongService songService;
    public static Playlist mPlaylist = new Playlist();

    public static final String countryID = "421Ms54es5s5iOY1H3yJUV";
    public static final String popID = "4tc0wc9XiPRVWYcqzYezzC";
    public static final String hipID = "3iz3TTVJr5Q7s05NwYA0Mh";
    public static final String rockID = "3P4nfoXbVOJT0TqZF0SEnI";


    public static boolean ifCountry;
    public static boolean ifPop;
    public static boolean ifHip;
    public static boolean ifRock;
    public static String playlistID;


    @SuppressWarnings("deprecation")
    public static Drawable getUrlDrawable(String url) {
        try {/* www .  ja va 2 s. c  o m*/
            URL aryURI = new URL(url);
            URLConnection conn = aryURI.openConnection();
            InputStream is = conn.getInputStream();
            Bitmap bmp = BitmapFactory.decodeStream(is);
            return new BitmapDrawable(bmp);
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // on below line we are initializing our array list and swipe deck.

        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        //choosing right playlist

        if(ifCountry == true){
            playlistID = countryID;
        }

        if(ifPop == true){
            playlistID = popID;
        }

        if(ifHip == true){
            playlistID = hipID;
        }

        if(ifRock == true){
            playlistID = rockID;
        }

        songService = new SongService(getApplicationContext());
        songService.getmPlaylist(() ->{ mPlaylist = songService.getPlaylist(); }, playlistID);

        Log.d("Sammy",mPlaylist.toString());

        int size = mPlaylist.getTracks().getTotal();
        //String songName = mPlaylist.getTracks().getItems().get(0).getTrack().getName();
        //int size = 5;
        for(int i = 0; i < 10; i++){
            Drawable image = null;

            String songName = "sammy";
                    //mPlaylist.getTracks().getItems().get(i).getTrack().getName();
            String artistName = "deet";
                    //mPlaylist.getTracks().getItems().get(i).getTrack().getAlbum().getArtists().get(0).getName();
            //String imageURL = mPlaylist.getTracks().getItems().get(i).getTrack().getAlbum().getImages().get(0).getUrl();

            //Create song template object
            Drawable myDrawable = getUrlDrawable("https://www.dupage88.net/site/public/agoraimages/?item=4364&v=7");
            int resID = getResources().getIdentifier("myDrawable", "drawable", getPackageName());
            SongTemplate newSong = new SongTemplate(resID, songName,artistName);
            songTemplateArrayList.add(newSong);

            Log.d("Sammy",songTemplateArrayList.toString());
        }

        /*
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
        songTemplateArrayList.add(song5); */

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