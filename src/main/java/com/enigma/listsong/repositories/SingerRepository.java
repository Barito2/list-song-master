package com.enigma.listsong.repositories;

import com.enigma.listsong.entities.Singer;
import com.enigma.listsong.models.singer.SingerModel;

import java.util.List;

public interface SingerRepository {

    boolean save(SingerModel singer);

    boolean update(SingerModel singer);

    boolean delete(Singer singer);

    Singer findById(Integer id);

    List<Singer> findAll();
}
