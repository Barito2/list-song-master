package com.enigma.listsong.services;

import com.enigma.listsong.entities.Song;
import com.enigma.listsong.models.song.SongModel;

import java.util.List;

public interface SongService {

    void addSong(SongModel genre);

    void updateSong(SongModel genre);

    void deleteSong(Song genre);

    Song findSongById(Integer id);

    List<Song> findAllSong();
}
