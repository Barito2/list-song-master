package com.enigma.listsong.services;

import com.enigma.listsong.entities.Singer;
import com.enigma.listsong.models.singer.SingerModel;

import java.util.List;

public interface SingerService {

    void addSinger(SingerModel genre);

    void updateSinger(SingerModel genre);

    void deleteSinger(Singer genre);

    Singer findSingerById(Integer id);

    List<Singer> findAllSinger();
}
