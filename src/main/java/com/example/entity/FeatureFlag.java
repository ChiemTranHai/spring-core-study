package com.example.entity;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="feature_flag")
public class FeatureFlag {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(length = 36)
    private UUID id;
    @Column
    private String name;
    @Column
    private String value;
    @Column
    private String description;
    @Column
    private String type;

    @OneToMany(mappedBy = "featureFlag")
    private Set<Variant> variants;

    @ManyToMany
    @JoinTable(name = "feature_flag_environment",
    joinColumns = @JoinColumn(name = "feature_flag_id", foreignKey = @ForeignKey(name="fk_environment_feature_flag_id")),
    inverseJoinColumns = @JoinColumn(name="environment_id",foreignKey = @ForeignKey(name = "fk_ff_environment_id")))
    private Set<Environment> environments;

    @OneToOne(mappedBy = "featureFlag", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FeatureFlagInfo featureFlagInfo;
}
