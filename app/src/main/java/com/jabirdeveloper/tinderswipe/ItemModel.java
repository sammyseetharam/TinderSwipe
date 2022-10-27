package com.jabirdeveloper.tinderswipe;

public class ItemModel {
    private int image;
    private String name, usia, kota;

    public ItemModel() {
    }

    public ItemModel(int image, String name, String usia, String kota) {
        this.image = image;
        this.name = name;
        this.usia = usia;
        this.kota = kota;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getUsia() {
        return usia;
    }

    public String getKota() {
        return kota;
    }
}
