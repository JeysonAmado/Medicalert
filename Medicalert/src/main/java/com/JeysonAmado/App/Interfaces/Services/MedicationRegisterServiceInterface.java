package com.JeysonAmado.App.Interfaces.Services;

import com.JeysonAmado.App.Dto.Medications.MedicationRegisterDto;
import com.JeysonAmado.App.Entities.Medications.MedicationRegisterEntity;

import java.util.List;

public interface MedicationRegisterServiceInterface {

    MedicationRegisterEntity store(MedicationRegisterEntity medicationRegister, Long userId);

    MedicationRegisterDto getById(Long id);

    List<MedicationRegisterDto> getAll();

    MedicationRegisterEntity update(MedicationRegisterDto medicationRegisterDto, Long medicationRegisterId,Long userId);

    MedicationRegisterEntity delete(Long medicationRegisterId,Long userId);

}
