package com.enigma.listsong.models.singer;


import com.enigma.listsong.models.PageSearch;

public class SingerSearch extends PageSearch {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
