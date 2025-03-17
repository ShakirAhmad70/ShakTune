package com.shakspotify.models;

import java.io.Serializable;

public class MusicLangItemModel {

    private final String langName;
    private final int artistImageId, bgStartColor, bgEndColor;
    private boolean isSelected;

    public MusicLangItemModel(String langName, int artistImage, int bgStartColor, int bgEndColor) {
        this.langName = langName;
        this.artistImageId = artistImage;
        this.bgStartColor = bgStartColor;
        this.bgEndColor = bgEndColor;
        this.isSelected = false;
    }

    public String getLangName() {
        return langName;
    }

    public int getArtistImageId() {
        return artistImageId;
    }

    public int getBgStartColor() {
        return bgStartColor;
    }

    public int getBgEndColor() {
        return bgEndColor;
    }

    public boolean getSelected(){
        return isSelected;
    }
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
