package com.enigma.listsong.services;

import com.enigma.listsong.entities.Group;

import java.util.List;

public interface GroupService {

    void addGroup(Group group);

    void updateGroup(Group group);

    void deleteGroup(Group group);

    Group findGroupById(Integer id);

    List<Group> findAllGroup();
}
