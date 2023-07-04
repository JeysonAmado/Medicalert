package com.JeysonAmado.App.Entities.Medications;

import com.JeysonAmado.App.Entities.BaseEntity;
import com.JeysonAmado.App.Entities.Users.UserEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;

@Entity
@Where(clause = "deleted_at is NULL")
@Table(name = "medication_register")
public class MedicationRegisterEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "medication_id", referencedColumnName = "id", insertable = false, updatable = false)
    private MedicationEntity medication;

    @Column
    private String presentation;

    @Column
    private double quantity;

    @Column(name = "additional_notes")
    private String additionalNotes;

    @ManyToOne
    @JoinColumn(name = "user_who_created_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userWhoCreated;

    @ManyToOne
    @JoinColumn(name = "user_who_updated_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userWhoUpdated;

    @ManyToOne
    @JoinColumn(name = "user_who_deleted_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userWhoDeleted;

    public MedicationEntity getMedication() {
        return medication;
    }

    public void setMedication(MedicationEntity medication) {
        this.medication = medication;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
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
        return "MedicationRegisterEntity{" +
                "medication=" + medication +
                ", presentation='" + presentation + '\'' +
                ", quantity=" + quantity +
                ", additionalNotes='" + additionalNotes + '\'' +
                ", userWhoCreated=" + userWhoCreated +
                ", userWhoUpdated=" + userWhoUpdated +
                ", userWhoDeleted=" + userWhoDeleted +
                '}';
    }
}
