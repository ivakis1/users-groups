package com.softuni.residentEvil.controllers;

import com.softuni.residentEvil.entities.Group;
import com.softuni.residentEvil.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/groups")
@Controller
public class GroupController {

    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(path = "/mygroups/{id}")
    public ModelAndView getAllUsersGroups(ModelAndView mav, @PathVariable Long id) {
        List<Group> groups = this.groupService.findByAdministrator_id(id);
        mav.addObject("groupsList", groups);
        mav.setViewName("user-groups");
        return mav;
    }

    @GetMapping(path = "/joingroups")
    public ModelAndView joinGroup(ModelAndView mav) {
        List<Group> groups = this.groupService.findAll();
        mav.addObject("groupsList", groups);
        mav.setViewName("all-groups");
        return mav;
    }
}
