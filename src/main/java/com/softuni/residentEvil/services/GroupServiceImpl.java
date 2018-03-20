package com.softuni.residentEvil.services;

import com.softuni.residentEvil.entities.Group;
import com.softuni.residentEvil.models.view.ViewGroup;
import com.softuni.residentEvil.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }


    @Override
    public ViewGroup findById(Long id) {
        Group user = this.groupRepository.getOne(id);
        ViewGroup viewGroup = new ViewGroup();
        viewGroup.setName(user.getName());
        viewGroup.setId(id);
        return viewGroup;
    }

    @Override
    public List<Group> findByAdministrator_id(Long id) {
        return this.groupRepository.findByAdministrator_idEquals(id);
    }

    @Override
    public List<Group> findAll() {
      return this.groupRepository.findAll();
    }
}
