package com.enigma.listsong.repositories;

import com.enigma.listsong.entities.Song;
import com.enigma.listsong.models.song.SongModel;

import java.util.List;

public interface SongRepository {

    boolean save(SongModel singer);

    boolean update(SongModel singer);

    boolean delete(Song singer);

    Song findById(Integer id);

    List<Song> findAll();
}
