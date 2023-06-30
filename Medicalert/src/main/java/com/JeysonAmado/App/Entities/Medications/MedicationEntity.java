package com.JeysonAmado.App.Entities.Medications;


import com.JeysonAmado.App.Entities.BaseEntity;
import com.JeysonAmado.App.Entities.Users.UserEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;


@Entity
@Where(clause = "deleted_at is NULL")
@Table(name = "medications")
public class MedicationEntity extends BaseEntity {

    @Column
    private String name;

    @Column(name = "units_of_measure")
    private String unitsOfMeasure;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitsOfMeasure() {
        return unitsOfMeasure;
    }

    public void setUnitsOfMeasure(String unitsOfMeasure) {
        this.unitsOfMeasure = unitsOfMeasure;
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

    @Override
    public String toString() {
        return "MedicationEntity{" +
                "name='" + name + '\'' +
                ", unitsOfMeasure='" + unitsOfMeasure + '\'' +
                ", quantity=" + quantity +
                ", additionalNotes='" + additionalNotes + '\'' +
                '}';
    }
}
