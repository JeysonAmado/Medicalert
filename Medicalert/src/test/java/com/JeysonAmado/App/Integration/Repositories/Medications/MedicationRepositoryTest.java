package com.JeysonAmado.App.Integration.Repositories.Medications;

import com.JeysonAmado.App.Entities.Medications.MedicationEntity;
import com.JeysonAmado.App.Entities.Medications.MedicationTypeEntity;
import com.JeysonAmado.App.Repositories.Medications.MedicationRepository;
import com.JeysonAmado.App.Repositories.Medications.MedicationTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MedicationRepositoryTest {

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private MedicationTypeRepository medicationTypeRepository;


    @Test
    public void isSaveWorking(){
        MedicationEntity medication = new MedicationEntity();
        MedicationTypeEntity medicationType = new MedicationTypeEntity();
        medicationType.setName("Analgésico");
        MedicationTypeEntity savedMedicationType = medicationTypeRepository.save(medicationType);

        medication.setMedicationType(medicationType);
        medication.setName("Paracetamol");
        MedicationEntity savedMedication = medicationRepository.save(medication);

        assertNotNull(savedMedicationType.getId());
        assertEquals("Analgésico", savedMedication.getMedicationType().getName());
        assertEquals("Paracetamol", savedMedication.getName());
    }

    @Test
    public void isFindByIdWorking(){
        MedicationEntity medication = new MedicationEntity();
        medication.setName("Paracetamol");
        MedicationEntity savedMedication = medicationRepository.save(medication);
        MedicationEntity findMedication = medicationRepository.findById(savedMedication.getId()).orElse(null);

        assertNotNull(findMedication);
        assertEquals(findMedication,savedMedication);
        assertEquals(findMedication.getId(),savedMedication.getId());
        assertEquals("Paracetamol",savedMedication.getName());
    }

    @Test
    public void isUpdateWorking() {
        MedicationEntity medication = new MedicationEntity();
        medication.setName("Paracetamol");
        MedicationEntity savedMedication = medicationRepository.save(medication);
        savedMedication.setName("Ibuprofeno");
        MedicationEntity updatedMedication = medicationRepository.findById(savedMedication.getId()).orElse(null);

        assertNotNull(updatedMedication);
        assertEquals(updatedMedication.getId(),savedMedication.getId());
        assertEquals("Ibuprofeno",savedMedication.getName());
    }

    @Test
    public void isDeleteWorking() {
        MedicationEntity medication = new MedicationEntity();
        medication.setName("Paracetamol");
        MedicationEntity savedMedication = medicationRepository.save(medication);
        Long id = savedMedication.getId();
        medicationRepository.delete(savedMedication);
        MedicationTypeEntity deletedMedication = medicationTypeRepository.findById(id).orElse(null);

        assertNull(deletedMedication);
    }

}
