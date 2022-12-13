package com.jabirdeveloper.tinderswipe;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.daprlabs.cardstack.SwipeDeck;
import com.jabirdeveloper.tinderswipe.Connectors.SongService;
import com.jabirdeveloper.tinderswipe.Model.Item;
import com.jabirdeveloper.tinderswipe.Model.Playlist;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "padmuhamed";
    // on below line we are creating variable
    // for our array list and swipe deck.
    private SwipeDeck cardStack;
    public static ArrayList<SongTemplate> songTemplateArrayList = new ArrayList<>();
    public ArrayList<Integer> wantList = new ArrayList<>();
    ArrayList<Item> wantSongs = new ArrayList<>();
    public static SongService songService;
    public static Playlist mPlaylist = new Playlist();

    private Playlist mNewPlaylist;
    Intent i;

    public static final String countryID = "421Ms54es5s5iOY1H3yJUV";
    public static final String popID = "4tc0wc9XiPRVWYcqzYezzC";
    public static final String hipID = "3iz3TTVJr5Q7s05NwYA0Mh";
    public static final String rockID = "3P4nfoXbVOJT0TqZF0SEnI";


    public static boolean ifCountry;
    public static boolean ifPop;
    public static boolean ifHip;
    public static boolean ifRock;
    public static String playlistID;
    public ArrayList<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // on below line we are initializing our array list and swipe deck.
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);
        //choosing right playlist
        if(ifCountry){
            playlistID = countryID;
        }
        if(ifPop){
            playlistID = popID;
        }
        if(ifHip){
            playlistID = hipID;
        }
        if(ifRock){
            playlistID = rockID;
        }
        startGetSwipe();
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
                wantList.add(songTemplateArrayList.indexOf(songTemplateArrayList.get(position)));
            }
            @Override
            public void cardsDepleted() {
                // this method is called when no card is present
                Toast.makeText(MainActivity.this, "No more songs present", Toast.LENGTH_SHORT).show();
                songService.getmPlaylist(()->{
                    for(int x: wantList){
                        wantSongs.add(mPlaylist.getTracks().getItems().get(x));
                    }
                    if(wantSongs.size() > 0){
                        songService.addSongs(wantSongs);
                    }
                },playlistID);
                i = new Intent(MainActivity.this,PostSwipeActivity.class);
                startActivity(i);
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

    public void startGetSwipe(){
        int si = songTemplateArrayList.size()/2;
        for (int i = 0; i < si;i++) {
            songTemplateArrayList.remove(0);
        }
        songService = new SongService(getApplicationContext());

        songService.getmPlaylist(() ->{
            mPlaylist = songService.getPlaylist();
            items = mPlaylist.getTracks().getItems();
            for(int i = 0; i < mPlaylist.getTracks().getItems().size(); i++){
                Drawable image = null;
                String songName = mPlaylist.getTracks().getItems().get(i).getTrack().getName();
                String artistName = mPlaylist.getTracks().getItems().get(i).getTrack().getAlbum().getArtists().get(0).getName();
                String imageURL = mPlaylist.getTracks().getItems().get(i).getTrack().getAlbum().getImages().get(0).getUrl();
                SongTemplate newSong = new SongTemplate(imageURL, songName,"By: "+ artistName);
                songTemplateArrayList.add(newSong);
            }
        }, playlistID);
        songService.makePlaylist(()->{
            mNewPlaylist = songService.getMyNewPlaylist();
        }, "Your Spindr Playlist");
    }
}