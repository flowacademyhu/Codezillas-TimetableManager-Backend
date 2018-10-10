package hu.flowacademy.timetablemanager.service.dto;



import hu.flowacademy.timetablemanager.model.User;

import java.util.ArrayList;
import java.util.List;

public class GroupDTOWuser {

    private Long id;

    private String name;

    private String location;

    private List<User> userNames = new ArrayList<>();

    private List<Long> classIds= new ArrayList<>();

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

    public List<User> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<User> userNames) {
        this.userNames = userNames;
    }

    public List<Long> getClassIds() {
        return classIds;
    }

    public void setClassIds(List<Long> classIds) {
        this.classIds = classIds;
    }
}
