package com.example.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="environment")
public class Environment {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(length = 36)
    private UUID id;
    @Column
    private String code;
    @Column
    private String name;

    @ManyToMany(mappedBy = "environments")
    private Set<Project> projects;

    @ManyToMany(mappedBy = "environments")
    private Set<FeatureFlag> featureFlags;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<FeatureFlag> getFeatureFlags() {
        return featureFlags;
    }

    public void setFeatureFlags(Set<FeatureFlag> featureFlags) {
        this.featureFlags = featureFlags;
    }

    @Override
    public String toString() {
        return "Environment{" +
                "id=" + id.toString() +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
//                ", featureFlags=" + featureFlags +
                '}';
    }
}
