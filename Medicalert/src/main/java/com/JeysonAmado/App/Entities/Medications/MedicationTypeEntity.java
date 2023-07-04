package com.JeysonAmado.App.Entities.Medications;

import com.JeysonAmado.App.Entities.BaseEntity;
import com.JeysonAmado.App.Entities.Users.UserEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;

@Entity
@Where(clause = "deleted_at is NULL")
@Table(name = "medication_types")
public class MedicationTypeEntity extends BaseEntity {

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

    @Override
    public String toString() {
        return "MedicationTypeEntity{" +
                "name='" + name + '\'' +
                ", userWhoCreated=" + userWhoCreated +
                ", userWhoUpdated=" + userWhoUpdated +
                ", userWhoDeleted=" + userWhoDeleted +
                '}';
    }
}
