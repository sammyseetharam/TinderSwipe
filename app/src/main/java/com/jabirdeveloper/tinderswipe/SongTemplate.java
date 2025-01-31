package com.jabirdeveloper.tinderswipe;

public class SongTemplate {
    //album art
    private String albumArtImage;
    // variables for our song name,
    // description,tracks and duration,imageId.
    private String songName;
    private String artistName;

    //constructor
    public SongTemplate(String albumArtImage, String songName, String artistName) {
        this.albumArtImage = albumArtImage;
        this.songName = songName;
        this.artistName = artistName;
    }

    //getters and setters
    public String getAlbumArtImage() {
        return albumArtImage;
    }

    public void setAlbumArtImage(String albumArtImage) {
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
