package com.example.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "variant")
public class Variant {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(length = 36)
    private UUID id;

    private String name;
    private String value;

    @ManyToOne
    @JoinColumn(name = "feature_flag_id", foreignKey = @ForeignKey(name = "fk_variant_feature_flag"))
    private FeatureFlag featureFlag;

}
