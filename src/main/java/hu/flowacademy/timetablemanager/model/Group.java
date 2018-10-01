package hu.flowacademy.timetablemanager.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column()
    private String location;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<User> users;

    @OneToMany(mappedBy = "group")
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
