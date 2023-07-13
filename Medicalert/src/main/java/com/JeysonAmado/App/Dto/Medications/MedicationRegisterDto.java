package com.JeysonAmado.App.Dto.Medications;

import com.JeysonAmado.App.Entities.Medications.MedicationEntity;
import lombok.Data;

@Data
public class MedicationRegisterDto {

    private Long id;

    private Long medication;

    private String medicationName;

    private double quantity;

    private String presentation;

    private String additionalNotes;
}
