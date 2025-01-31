package com.jabirdeveloper.tinderswipe.Model;

public class Song {
    //https://towardsdatascience.com/using-the-spotify-api-with-your-android-application-the-essentials-1a3c1bc36b9e
    private String id;
    private String name;
    private Album album;
    private String uri;



    public Song(String id, String name, Album album, String uri) {
        this.name = name;
        this.id = id;
        this.album = album;
        this.uri = uri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
