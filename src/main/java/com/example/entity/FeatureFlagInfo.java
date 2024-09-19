package com.example.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "feature_flag_info")
public class FeatureFlagInfo {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(length = 36)
    private UUID id;

    @Column
    @Temporal(TemporalType.DATE)
    private Date expiredDate;
    @Column
    private String status;
    @Column
    private int percent;

    @OneToOne
    @PrimaryKeyJoinColumn(foreignKey = @ForeignKey(name = "fk_feature_flag_id"))
    private FeatureFlag featureFlag;
}
