package com.enigma.listsong.services.impls;

import com.enigma.listsong.entities.Song;
import com.enigma.listsong.models.song.SongModel;
import com.enigma.listsong.repositories.SongRepository;
import com.enigma.listsong.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    SongRepository songRepository;

    @Override
    public void addSong(SongModel song) {
        songRepository.save(song);
    }

    @Override
    public void updateSong(SongModel song) {
        songRepository.update(song);
    }

    @Override
    public void deleteSong(Song song) {
        songRepository.delete(song);
    }

    @Override
    public Song findSongById(Integer id) {
        return songRepository.findById(id);
    }

    @Override
    public List<Song> findAllSong() {
        return songRepository.findAll();
    }
}
