package com.enigma.listsong.controllers;

import com.enigma.listsong.entities.Group;
import com.enigma.listsong.exceptions.EntityNotFoundException;
import com.enigma.listsong.messages.ResponseMessage;
import com.enigma.listsong.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping
@RestController
public class GroupController {

    @Autowired
    private GroupService service;

    @PostMapping("/group")
    public ResponseMessage<Group> add(@RequestBody @Valid Group model) {

        service.addGroup(model);
        return ResponseMessage.success(model);
    }

    @GetMapping("/groups")
    public ResponseMessage<List<Group>> findAll() {
        List<Group> data = service.findAllGroup();
        return ResponseMessage.success(data);
    }

    @GetMapping("/group/{id}")
    public ResponseMessage findById(@PathVariable Integer id) {

        Group data = service.findGroupById(id);
        if (data == null) {
            throw new EntityNotFoundException();
        }

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/group/{id}")
    public ResponseMessage removeById(@PathVariable Integer id) {

        Group data = service.findGroupById(id);
        if (data == null) {
            throw new EntityNotFoundException();
        }
        service.deleteGroup(data);

        return ResponseMessage.success(data);
    }

    @PutMapping("/group/{id}")
    public ResponseMessage<Group> removeById(@PathVariable Integer id, @RequestBody Group model) {

        model.setId(id);
        service.updateGroup(model);

        return ResponseMessage.success(model);
    }
}
