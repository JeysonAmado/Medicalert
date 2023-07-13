package com.JeysonAmado.App.Entities.Medications;


import com.JeysonAmado.App.Entities.BaseEntity;
import com.JeysonAmado.App.Entities.Users.UserEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;


@Entity
@Where(clause = "deleted_at is NULL")
@Table(name = "medications")
public class MedicationEntity extends BaseEntity {


    @Column(name = "medication_type_id")
    private Long medicationTypeId;

    @ManyToOne
    @JoinColumn(name = "medication_type_id", referencedColumnName = "id", insertable = false, updatable = false)
    private MedicationTypeEntity medicationType;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_who_created_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userWhoCreated;

    @ManyToOne
    @JoinColumn(name = "user_who_updated_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userWhoUpdated;

    @ManyToOne
    @JoinColumn(name = "user_who_deleted_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userWhoDeleted;

    public Long getMedicationTypeId() {
        return medicationTypeId;
    }

    public void setMedicationTypeId(Long medicationTypeId) {
        this.medicationTypeId = medicationTypeId;
    }

    public MedicationTypeEntity getMedicationType() {
        return medicationType;
    }

    public void setMedicationType(MedicationTypeEntity medicationTypeId) {
        this.medicationType = medicationTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getUserWhoCreated() {
        return userWhoCreated;
    }

    public void setUserWhoCreated(UserEntity userWhoCreated) {
        this.userWhoCreated = userWhoCreated;
    }

    public UserEntity getUserWhoUpdated() {
        return userWhoUpdated;
    }

    public void setUserWhoUpdated(UserEntity userWhoUpdated) {
        this.userWhoUpdated = userWhoUpdated;
    }

    public UserEntity getUserWhoDeleted() {
        return userWhoDeleted;
    }

    public void setUserWhoDeleted(UserEntity userWhoDeleted) {
        this.userWhoDeleted = userWhoDeleted;
    }
}
