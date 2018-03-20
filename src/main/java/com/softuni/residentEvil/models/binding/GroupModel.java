package com.softuni.residentEvil.models.binding;

import com.softuni.residentEvil.entities.User;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class GroupModel {

    @NotNull
    String name;
    @NotNull
    Set<User> users;

    public GroupModel() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
