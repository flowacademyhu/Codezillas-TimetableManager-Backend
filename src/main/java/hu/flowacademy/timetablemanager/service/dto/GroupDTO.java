package hu.flowacademy.timetablemanager.service.dto;

public class GroupDTO {

    private Long id;

    private String name;

    private String location;

    //Might have to contain users list and classes list?

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
}
