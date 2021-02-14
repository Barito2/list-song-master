package com.enigma.listsong.repositories;

import com.enigma.listsong.entities.Group;

import java.util.List;

public interface GroupRepository {

    boolean save(Group group);

    boolean update(Group group);

    boolean delete(Group group);

    Group findById(Integer id);

    List<Group> findAll();
}
