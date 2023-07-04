package com.JeysonAmado.App.Integration.Repositories.Medications;

import com.JeysonAmado.App.Entities.Medications.MedicationTypeEntity;
import com.JeysonAmado.App.Repositories.Medications.MedicationTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class MedicationTypeRepositoryTest {

    @Autowired
    private MedicationTypeRepository medicationTypeRepository;

    @Test
    public void isSaveWorking(){
        MedicationTypeEntity medicationType = new MedicationTypeEntity();
        medicationType.setName("Analgésico");
        MedicationTypeEntity savedMedicationType = medicationTypeRepository.save(medicationType);

        assertNotNull(savedMedicationType.getId());
        assertEquals("Analgésico", savedMedicationType.getName());
    }

    @Test
    public void isFindByIdWorking(){
        MedicationTypeEntity medicationType = new MedicationTypeEntity();
        medicationType.setName("Analgésico");
        MedicationTypeEntity savedMedicationType = medicationTypeRepository.save(medicationType);
        MedicationTypeEntity findMedicationType = medicationTypeRepository.findById(savedMedicationType.getId()).orElse(null);

        assertNotNull(findMedicationType);
        assertEquals(findMedicationType,savedMedicationType);
        assertEquals(findMedicationType.getId(),savedMedicationType.getId());
        assertEquals("Analgésico",savedMedicationType.getName());
    }

    @Test
    public void isUpdateWorking() {
        MedicationTypeEntity MedicationType = new MedicationTypeEntity();
        MedicationType.setName("Antibiotico");
        MedicationTypeEntity savedMedicationType = medicationTypeRepository.save(MedicationType);
        savedMedicationType.setName("Analgésico");
        MedicationTypeEntity updatedMedicationType = medicationTypeRepository.save(savedMedicationType);
        MedicationTypeEntity findMedicationType = medicationTypeRepository.findById(updatedMedicationType.getId()).orElse(null);

        assertNotNull(findMedicationType);
        assertEquals("Analgésico", findMedicationType.getName());
    }

    @Test
    public void isDeleteWorking() {
        MedicationTypeEntity medicationType = new MedicationTypeEntity();
        medicationType.setName("Analgésico");
        MedicationTypeEntity savedMedicationType = medicationTypeRepository.save(medicationType);
        Long id = savedMedicationType.getId();
        medicationTypeRepository.delete(savedMedicationType);
        MedicationTypeEntity deletedMedicationType = medicationTypeRepository.findById(id).orElse(null);

        assertNull(deletedMedicationType);
    }
}
