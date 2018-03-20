package com.softuni.residentEvil.services;

import com.softuni.residentEvil.entities.Group;
import com.softuni.residentEvil.models.view.ViewGroup;

import java.util.List;

public interface GroupService {
    ViewGroup findById(Long id);

    List<Group> findByAdministrator_id(Long id);

    List<Group> findAll();
}
