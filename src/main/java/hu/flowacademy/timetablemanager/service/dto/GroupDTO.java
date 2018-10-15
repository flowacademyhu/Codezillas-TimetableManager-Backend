package hu.flowacademy.timetablemanager.service.dto;

import java.util.ArrayList;
import java.util.List;

public class GroupDTO {

    private Long id;

    private String name;

    private List<Long> userIds = new ArrayList<>();

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

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public List<Long> getClassIds() {
        return classIds;
    }

    public void setClassIds(List<Long> classIds) {
        this.classIds = classIds;
    }
}
