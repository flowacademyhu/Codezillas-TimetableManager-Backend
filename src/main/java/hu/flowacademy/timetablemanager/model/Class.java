package hu.flowacademy.timetablemanager.model;

import javax.persistence.*;

@Entity
@Table(name= "classes")
public class Class {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

}
