package com.example.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(length = 36)
    private UUID id;
    @Column
    private String name;
    @Column
    private String code;

    @OrderBy("code")
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "project_environment",
            joinColumns = @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "fk_environment_project_id")),
            inverseJoinColumns = @JoinColumn(name = "environment_id", foreignKey = @ForeignKey(name = "fk_project_environment_id")))
    private Set<Environment> environments;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Environment> getEnvironments() {
        return environments;
    }

    public void setEnvironments(Set<Environment> environments) {
        this.environments = environments;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
//                ", environments=" + environments +
                '}';
    }
}
