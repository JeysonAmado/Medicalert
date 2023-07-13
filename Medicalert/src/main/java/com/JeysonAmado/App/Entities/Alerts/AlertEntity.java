package com.JeysonAmado.App.Entities.Alerts;

import com.JeysonAmado.App.Dto.Alerts.AlertDto;
import com.JeysonAmado.App.Entities.BaseEntity;
import com.JeysonAmado.App.Entities.Medications.MedicationRegisterEntity;
import com.JeysonAmado.App.Entities.Users.UserEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;


@Entity
@Where(clause = "deleted_at is NULL")
@Table(name = "alerts")
public class AlertEntity extends BaseEntity {

    @Column(name = "medication_register_id")
    private Long medicationRegisterId;

    @ManyToOne
    @JoinColumn(name = "medication_register_id", referencedColumnName = "id", insertable = false, updatable = false)
    private MedicationRegisterEntity medicationRegister;

    @Column
    private String name;

    @Column(name = "hours_to_repeat")
    private double hoursToRepeat;

    @Column(name = "doses_taken")
    private int dosesTaken;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "next_alert_at")
    private LocalDateTime nextAlertAt;

    @ManyToOne
    @JoinColumn(name = "user_who_created_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userWhoCreated;

    @ManyToOne
    @JoinColumn(name = "user_who_updated_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userWhoUpdated;

    @ManyToOne
    @JoinColumn(name = "user_who_deleted_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userWhoDeleted;

    public void mergeDto(AlertDto alertDto) {
        if (alertDto.getMedicationRegister() != null) {
            this.setMedicationRegisterId(alertDto.getMedicationRegister());
        }
        if (alertDto.getName() != null) {
            this.setName(alertDto.getName());
        }
        if (alertDto.getHoursToRepeat() != 0) {
            this.setHoursToRepeat(alertDto.getHoursToRepeat());
        }
        if (alertDto.getDosesTaken() != 0) {
            this.setDosesTaken(alertDto.getDosesTaken());
        }

        if (alertDto.getStartAt() != null) {
            this.setStartAt(alertDto.getStartAt());
        }

        if (alertDto.getNextAlertAt() != null) {
            this.setNextAlertAt(alertDto.getNextAlertAt());
        }
    }

    public int[] calculateHoursAndMinutes(double hoursToRepeat){
        int hours = (int) hoursToRepeat;
        int minutes = (int) ((hoursToRepeat - hours) * 60);

        return new int[]{hours, minutes};
    }

    public void calculateNextAlert(LocalDateTime initialDate, int [] hoursAndMinutes){
        int hours = hoursAndMinutes[0];
        int minutes = hoursAndMinutes[1];
        this.setNextAlertAt(initialDate.plusHours(hours).plusMinutes(minutes));
    }

    public Long getMedicationRegisterId() {
        return medicationRegisterId;
    }

    public void setMedicationRegisterId(Long medicationRegisterId) {
        this.medicationRegisterId = medicationRegisterId;
    }

    public MedicationRegisterEntity getMedicationRegister() {
        return medicationRegister;
    }

    public void setMedicationRegister(MedicationRegisterEntity medicationRegister) {
        this.medicationRegister = medicationRegister;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
                "medicationRegister=" + medicationRegister +
                ", name='" + name + '\'' +
                ", hoursToRepeat=" + hoursToRepeat +
                ", dosesTaken=" + dosesTaken +
                ", startAt=" + startAt +
                ", nextAlertAt=" + nextAlertAt +
                ", userWhoCreated=" + userWhoCreated +
                ", userWhoUpdated=" + userWhoUpdated +
                ", userWhoDeleted=" + userWhoDeleted +
                '}';
    }
}
