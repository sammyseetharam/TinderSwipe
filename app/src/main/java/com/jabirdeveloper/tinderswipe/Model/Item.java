package com.jabirdeveloper.tinderswipe.Model;

public class Item {
    private Song track;
    //private Album album;

    public Item(Song track) {
        this.track = track;
        //this.album = album;
    }

    public Song getTrack() {
        return track;
    }

    public void setTrack(Song track) {
        this.track = track;
    }
}
