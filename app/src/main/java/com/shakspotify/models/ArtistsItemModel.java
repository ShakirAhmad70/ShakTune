package com.shakspotify.models;

public class ArtistsItemModel {
    String artistName;
    int artistImg;
    boolean isSelected = false;

    public ArtistsItemModel(String artistName, int artistImg) {
        this.artistName = artistName;
        this.artistImg = artistImg;
    }

    public String getArtistName() {
        return artistName;
    }

    public int getArtistImg() {
        return artistImg;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
