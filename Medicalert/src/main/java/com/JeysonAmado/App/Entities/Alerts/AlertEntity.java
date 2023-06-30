package com.JeysonAmado.App.Entities.Alerts;

import com.JeysonAmado.App.Entities.BaseEntity;
import com.JeysonAmado.App.Entities.Medications.MedicationEntity;
import com.JeysonAmado.App.Entities.Users.UserEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;


@Entity
@Where(clause = "deleted_at is NULL")
@Table(name = "alerts")
public class AlertEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "medication_id", referencedColumnName = "id", insertable = false, updatable = false)
    private MedicationEntity medicationId;

    @Column(name = "hours_to_repeat")
    double hoursToRepeat;
    @Column(name = "doses_taken")
    int dosesTaken;

    @Column(name = "start_at")
    LocalDateTime startAt;

    @Column(name = "next_alert_at")
    LocalDateTime nextAlertAt;

    @ManyToOne
    @JoinColumn(name = "user_who_created_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userWhoCreated;

    @ManyToOne
    @JoinColumn(name = "user_who_updated_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userWhoUpdated;

    @ManyToOne
    @JoinColumn(name = "user_who_deleted_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userWhoDeleted;

    public double getHoursToRepeat() {
        return hoursToRepeat;
    }

    public void setHoursToRepeat(double hoursToRepeat) {
        this.hoursToRepeat = hoursToRepeat;
    }

    public int getDosesTaken() {
        return dosesTaken;
    }

    public void setDosesTaken(int dosesTaken) {
        this.dosesTaken = dosesTaken;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getNextAlertAt() {
        return nextAlertAt;
    }

    public void setNextAlertAt(LocalDateTime nextAlertAt) {
        this.nextAlertAt = nextAlertAt;
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
        return "AlertEntity{" +
                "hoursToRepeat=" + hoursToRepeat +
                ", dosesTaken=" + dosesTaken +
                ", startAt=" + startAt +
                ", nextAlertAt=" + nextAlertAt +
                '}';
    }
}
