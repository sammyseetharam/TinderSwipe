package com.jabirdeveloper.tinderswipe.Model;

public class Playlist {
    private String id;
    private String name;
    private Tracks tracks;

    public Playlist(String id, String name, Tracks tracks){
        this.id = id;
        this.name = name;
        this.tracks = tracks;

    }

    public Playlist() {
        id = null;
        name = null;
        tracks = new Tracks();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }
}
