package com.JeysonAmado.App.Dto.Medications;

import com.JeysonAmado.App.Entities.Medications.MedicationEntity;
import lombok.Data;

@Data
public class MedicationRegisterDto {

    private MedicationEntity medication;

    private String presentation;

    private double quantity;

    private String additionalNotes;
}
