package hu.flowacademy.timetablemanager.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="subjects")
public class Subject {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(unique = true, nullable = false)
    private String color;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Class> classes;

    @ManyToMany
    @JoinTable(
            inverseJoinColumns =
            @JoinColumn(name = "subject_id"),
            joinColumns =
            @JoinColumn(name = "user_id")
    )
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

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
