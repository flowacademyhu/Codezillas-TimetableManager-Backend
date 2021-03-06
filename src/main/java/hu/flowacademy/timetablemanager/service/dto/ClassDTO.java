package hu.flowacademy.timetablemanager.service.dto;

import hu.flowacademy.timetablemanager.model.Class;

import java.util.ArrayList;
import java.util.List;

public class ClassDTO {

    private Long id;

    private Long startDate;

    private Long endDate;

    private String comment;

    private List<Long> mentorIds = new ArrayList<>();

    private Long subjectId;

    private Long groupId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public List<Long> getMentorIds() {
        return mentorIds;
    }

    public void setMentorIds(List<Long> mentorIds) {
        this.mentorIds = mentorIds;
    }
}

