package com.enigma.listsong.controllers;

import com.enigma.listsong.entities.Singer;
import com.enigma.listsong.exceptions.EntityNotFoundException;
import com.enigma.listsong.messages.ResponseMessage;
import com.enigma.listsong.models.singer.SingerModel;
import com.enigma.listsong.services.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping
@RestController
public class SingerController {

    @Autowired
    private SingerService service;

    @PostMapping("/singer")
    public ResponseMessage<SingerModel> add(@RequestBody @Valid SingerModel model) {

        service.addSinger(model);
        return ResponseMessage.success(model);
    }

    @GetMapping("/singers")
    public ResponseMessage<List<Singer>> findAll() {
        List<Singer> data = service.findAllSinger();
        return ResponseMessage.success(data);
    }

    @GetMapping("/singer/{id}")
    public ResponseMessage findById(@PathVariable Integer id) {

        Singer data = service.findSingerById(id);
        if (data == null) {
            throw new EntityNotFoundException();
        }

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/singer/{id}")
    public ResponseMessage removeById(@PathVariable Integer id) {

        Singer data = service.findSingerById(id);
        if (data == null) {
            throw new EntityNotFoundException();
        }
        service.deleteSinger(data);

        return ResponseMessage.success(data);
    }

    @PutMapping("/singer/{id}")
    public ResponseMessage<SingerModel> removeById(@PathVariable Integer id, @RequestBody SingerModel model) {

        model.setId(id);
        service.updateSinger(model);

        return ResponseMessage.success(model);
    }
}
