package com.JeysonAmado.App.Dto.Alerts;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlertDto {

    private Long id;

    private Long medicationRegister;

    private String name;

    private double hoursToRepeat;

    private int dosesTaken;

    private LocalDateTime startAt;

    private LocalDateTime nextAlertAt;

}
