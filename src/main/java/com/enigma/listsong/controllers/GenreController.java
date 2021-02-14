package com.enigma.listsong.controllers;

import com.enigma.listsong.entities.Genre;
import com.enigma.listsong.exceptions.EntityNotFoundException;
import com.enigma.listsong.messages.ResponseMessage;
import com.enigma.listsong.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping
@RestController
public class GenreController {

    @Autowired
    private GenreService service;

    @PostMapping("/genre")
    public ResponseMessage<Genre> add(@RequestBody @Valid Genre model) {

        service.addGenre(model);
        return ResponseMessage.success(model);
    }

    @GetMapping("/genres")
    public ResponseMessage<List<Genre>> findAll() {
        List<Genre> data = service.findAllGenre();
        return ResponseMessage.success(data);
    }

    @GetMapping("/genre/{id}")
    public ResponseMessage findById(@PathVariable Integer id) {

        Genre data = service.findGenreById(id);
        if (data == null) {
            throw new EntityNotFoundException();
        }

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/genre/{id}")
    public ResponseMessage removeById(@PathVariable Integer id) {

        Genre data = service.findGenreById(id);
        if (data == null) {
            throw new EntityNotFoundException();
        }
        service.deleteGenre(data);

        return ResponseMessage.success(data);
    }

    @PutMapping("/genre/{id}")
    public ResponseMessage<Genre> removeById(@PathVariable Integer id, @RequestBody Genre model) {

        model.setId(id);
        service.updateGenre(model);

        return ResponseMessage.success(model);
    }
}
