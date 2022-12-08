package com.jabirdeveloper.tinderswipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CardStackAdapter extends BaseAdapter {

    // on below line we have created variables
    // for our array list and context.
    private ArrayList<SongTemplate> songData;
    private Context context;

    // on below line we have created constructor for our variables.
    public CardStackAdapter(ArrayList<SongTemplate> songData, Context context) {
        this.songData = songData;
        this.context = context;
    }

    @Override
    public int getCount() {
        // in get count method we are returning the size of our array list.
        return songData.size();
    }

    @Override
    public Object getItem(int position) {
        // in get item method we are returning the item from our array list.
        return songData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // in get item id we are returning the position.
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // in get view method we are inflating our layout on below line.
        View v = convertView;
        if (v == null) {
            // on below line we are inflating our layout.
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_card, parent, false);
        }
        // on below line we are initializing our variables and setting data to our variables.
        ((TextView) v.findViewById(R.id.songName)).setText(songData.get(position).getSongName());
        ((TextView) v.findViewById(R.id.artistName)).setText(songData.get(position).getArtistName());
        ((ImageView) v.findViewById(R.id.songImage)).setImageResource(songData.get(position).getAlbumArtImage());
        return v;
    }

}
