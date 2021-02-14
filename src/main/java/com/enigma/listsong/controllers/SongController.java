package com.enigma.listsong.controllers;

import com.enigma.listsong.entities.Song;
import com.enigma.listsong.exceptions.EntityNotFoundException;
import com.enigma.listsong.messages.ResponseMessage;
import com.enigma.listsong.models.song.SongModel;
import com.enigma.listsong.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping
@RestController
public class SongController {

    @Autowired
    private SongService service;

    @PostMapping("/song")
    public ResponseMessage<SongModel> add(@RequestBody @Valid SongModel model) {

        service.addSong(model);
        return ResponseMessage.success(model);
    }

    @GetMapping("/songs")
    public ResponseMessage<List<Song>> findAll() {
        List<Song> data = service.findAllSong();
        return ResponseMessage.success(data);
    }

    @GetMapping("/song/{id}")
    public ResponseMessage findById(@PathVariable Integer id) {

        Song data = service.findSongById(id);
        if (data == null) {
            throw new EntityNotFoundException();
        }

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/song/{id}")
    public ResponseMessage removeById(@PathVariable Integer id) {

        Song data = service.findSongById(id);
        if (data == null) {
            throw new EntityNotFoundException();
        }
        service.deleteSong(data);

        return ResponseMessage.success(data);
    }

    @PutMapping("/song/{id}")
    public ResponseMessage<SongModel> removeById(@PathVariable Integer id, @RequestBody SongModel model) {

        model.setId(id);
        service.updateSong(model);

        return ResponseMessage.success(model);
    }
}
