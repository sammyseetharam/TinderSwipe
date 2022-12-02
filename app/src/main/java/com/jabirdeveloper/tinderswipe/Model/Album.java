package com.jabirdeveloper.tinderswipe.Model;

import java.util.ArrayList;

public class Album {
    private String id;
    private String name;
    private ArrayList<Image> images;
    private ArrayList<Artist> artists;


    public Album(String id, String name, ArrayList<Image> images, ArrayList<Artist> artists) {
        this.id = id;
        this.name = name;
        this.images = images;
        this.artists = artists;
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

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }
}
