package com.enigma.listsong.services.impls;

import com.enigma.listsong.entities.Singer;
import com.enigma.listsong.models.singer.SingerModel;
import com.enigma.listsong.repositories.SingerRepository;
import com.enigma.listsong.services.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    SingerRepository singerRepository;

    @Override
    public void addSinger(SingerModel singer) {
        singerRepository.save(singer);
    }

    @Override
    public void updateSinger(SingerModel singer) {
        singerRepository.update(singer);
    }

    @Override
    public void deleteSinger(Singer singer) {
        singerRepository.delete(singer);
    }

    @Override
    public Singer findSingerById(Integer id) {
        return singerRepository.findById(id);
    }

    @Override
    public List<Singer> findAllSinger() {
        return singerRepository.findAll();
    }
}
