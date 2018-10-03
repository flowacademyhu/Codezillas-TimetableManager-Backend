package hu.flowacademy.timetablemanager.service.dto;

import hu.flowacademy.timetablemanager.model.Class;
import hu.flowacademy.timetablemanager.model.User;

import java.util.List;

public class GroupDTO {

    private Long id;

    private String name;

    private String location;

    private List<User> users;

    private List<Class> classes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }
}
