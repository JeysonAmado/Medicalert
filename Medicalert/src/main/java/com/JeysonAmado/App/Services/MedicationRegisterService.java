package com.JeysonAmado.App.Services;

import com.JeysonAmado.App.Dto.Medications.MedicationRegisterDto;
import com.JeysonAmado.App.Entities.Medications.MedicationRegisterEntity;
import com.JeysonAmado.App.Interfaces.Services.MedicationRegisterServiceInterface;
import com.JeysonAmado.App.Maps.Medications.MedicationRegisterMap;
import com.JeysonAmado.App.Repositories.Medications.MedicationRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationRegisterService implements MedicationRegisterServiceInterface {


    private final MedicationRegisterRepository medicationRegisterRepository;

    private final MedicationRegisterMap medicationRegisterMap;

    @Autowired
    public MedicationRegisterService(MedicationRegisterRepository medicationRegisterRepository, MedicationRegisterMap medicationRegisterMap) {
        this.medicationRegisterRepository = medicationRegisterRepository;
        this.medicationRegisterMap = medicationRegisterMap;
    }

    @Override
    public MedicationRegisterEntity store(MedicationRegisterEntity medicationRegister, Long userId) {
        medicationRegister.commitCreate(userId);
        return medicationRegisterRepository.save(medicationRegister);
    }

    @Override
    public MedicationRegisterDto getById(Long id) {
        MedicationRegisterEntity medicationRegister = medicationRegisterRepository.findById(id).orElse(null);
        return medicationRegisterMap.toDto(medicationRegister);
    }

    @Override
    public List<MedicationRegisterDto> getAll() {
        List<MedicationRegisterEntity> allMedicationRegister = medicationRegisterRepository.findAll();
        return allMedicationRegister.stream()
                .map(medicationRegisterMap::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MedicationRegisterEntity update(MedicationRegisterDto medicationRegisterDto, Long medicationRegisterId,Long userId) {
        MedicationRegisterEntity medicationRegister = medicationRegisterRepository.findById(medicationRegisterId).orElse(null);
        if (medicationRegister != null){
            medicationRegister.mergeDto(medicationRegisterDto);
            medicationRegister.commitUpdate(userId);
            return medicationRegisterRepository.save(medicationRegister);
        }
        return null;
    }

    @Override
    public MedicationRegisterEntity delete(Long medicationRegisterId, Long userId) {
        MedicationRegisterEntity medicationRegister = medicationRegisterRepository.findById(medicationRegisterId).orElse(null);
        if (medicationRegister != null){
            medicationRegister.commitDelete(userId);
            return medicationRegisterRepository.save(medicationRegister);
        }
        return null;
    }
}
