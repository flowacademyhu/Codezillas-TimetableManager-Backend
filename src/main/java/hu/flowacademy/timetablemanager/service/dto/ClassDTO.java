package hu.flowacademy.timetablemanager.service.dto;

public class ClassDTO {

    private Long id;

    private Long startDate;

    private Long endDate;

    private String comment;

    private Long fk_id_mentor_many_to_many;

    private Long fk_id_subject;

    private Long fk_id_group;

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

    public Long getFk_id_mentor_many_to_many() {
        return fk_id_mentor_many_to_many;
    }

    public void setFk_id_mentor_many_to_many(Long fk_id_mentor_many_to_many) {
        this.fk_id_mentor_many_to_many = fk_id_mentor_many_to_many;
    }

    public Long getFk_id_subject() {
        return fk_id_subject;
    }

    public void setFk_id_subject(Long fk_id_subject) {
        this.fk_id_subject = fk_id_subject;
    }

    public Long getFk_id_group() {
        return fk_id_group;
    }

    public void setFk_id_group(Long fk_id_group) {
        this.fk_id_group = fk_id_group;
    }
}

