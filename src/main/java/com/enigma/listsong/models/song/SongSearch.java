package com.enigma.listsong.models.song;


import com.enigma.listsong.models.PageSearch;

public class SongSearch extends PageSearch {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
