package com.JeysonAmado.App.Maps.Medications;

import com.JeysonAmado.App.Dto.Medications.MedicationRegisterDto;
import com.JeysonAmado.App.Entities.Medications.MedicationRegisterEntity;
import com.JeysonAmado.App.Maps.BaseMap;
import org.springframework.stereotype.Component;

@Component
public class MedicationRegisterMap extends BaseMap<MedicationRegisterDto, MedicationRegisterEntity> {
    public MedicationRegisterMap() {
        super(MedicationRegisterDto.class, MedicationRegisterEntity.class);
    }
}
