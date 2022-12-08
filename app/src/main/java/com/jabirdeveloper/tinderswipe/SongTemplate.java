package com.jabirdeveloper.tinderswipe;

public class SongTemplate {
    //album art
    private int albumArtImage;
    // variables for our song name,
    // description,tracks and duration,imageId.
    private String songName;
    private String artistName;

    //constructor
    public SongTemplate(int albumArtImage, String songName, String artistName) {
        this.albumArtImage = albumArtImage;
        this.songName = songName;
        this.artistName = artistName;
    }

    //getters and setters
    public int getAlbumArtImage() {
        return albumArtImage;
    }

    public void setAlbumArtImage(int albumArtImage) {
        this.albumArtImage = albumArtImage;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public String toString() {
        return "SongTemplate{" +
                "albumArtImage=" + albumArtImage +
                ", songName='" + songName + '\'' +
                ", artistName='" + artistName + '\'' +
                '}';
    }
}
