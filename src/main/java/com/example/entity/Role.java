package com.example.entity;


import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
