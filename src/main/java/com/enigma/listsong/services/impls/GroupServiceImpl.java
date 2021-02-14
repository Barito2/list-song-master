package com.enigma.listsong.services.impls;

import com.enigma.listsong.entities.Group;
import com.enigma.listsong.repositories.GroupRepository;
import com.enigma.listsong.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Override
    public void addGroup(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void updateGroup(Group group) {
        groupRepository.update(group);
    }

    @Override
    public void deleteGroup(Group group) {
        groupRepository.delete(group);
    }

    @Override
    public Group findGroupById(Integer id) {
        return groupRepository.findById(id);
    }

    @Override
    public List<Group> findAllGroup() {
        return groupRepository.findAll();
    }
}
