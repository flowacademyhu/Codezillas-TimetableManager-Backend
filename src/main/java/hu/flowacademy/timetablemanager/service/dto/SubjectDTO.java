package hu.flowacademy.timetablemanager.service.dto;

import hu.flowacademy.timetablemanager.model.Class;
import hu.flowacademy.timetablemanager.model.User;

import java.util.List;

public class SubjectDTO {

    private Long id;

    private String title;

    private String color;
    // Might have to contain classes and user(mentor) list
    private List<Long> classIds;

    private List<Long> userIds;

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

    public List<Long> getClassIds() {
        return classIds;
    }

    public void setClassIds(List<Long> classIds) {
        this.classIds = classIds;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }
}
