package hu.flowacademy.timetablemanager.service.dto;

import hu.flowacademy.timetablemanager.model.Class;
import hu.flowacademy.timetablemanager.model.User;

import java.util.List;

public class SubjectDTO {

    private Long id;

    private String title;

    private String color;
    // Might have to contain classes and user(mentor) list
    private List<Class> classes;

    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
