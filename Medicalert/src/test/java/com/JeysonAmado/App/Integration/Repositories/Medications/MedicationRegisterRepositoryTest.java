package com.JeysonAmado.App.Integration.Repositories.Medications;

import com.JeysonAmado.App.Entities.Medications.MedicationEntity;
import com.JeysonAmado.App.Entities.Medications.MedicationRegisterEntity;
import com.JeysonAmado.App.Repositories.Medications.MedicationRegisterRepository;
import com.JeysonAmado.App.Repositories.Medications.MedicationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MedicationRegisterRepositoryTest {

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private MedicationRegisterRepository medicationRegisterRepository;

    @Test
    public void isSaveWorking(){
        MedicationRegisterEntity medicationRegister = new MedicationRegisterEntity();
        MedicationEntity medication = new MedicationEntity();
        medication.setName("Paracetamol");
        MedicationEntity savedMedication = medicationRepository.save(medication);
        medicationRegister.setMedication(savedMedication);
        medicationRegister.setPresentation("ml");
        medicationRegister.setQuantity(12);
        medicationRegister.setAdditionalNotes("Tomar con precaución");

        MedicationRegisterEntity savedMedicationRegister = medicationRegisterRepository.save(medicationRegister);

        assertNotNull(savedMedicationRegister.getId());
        assertEquals(medication, savedMedicationRegister.getMedication());
        assertEquals("ml", savedMedicationRegister.getPresentation());
        assertEquals(12, savedMedicationRegister.getQuantity());
        assertEquals("Tomar con precaución", savedMedicationRegister.getAdditionalNotes());
    }

    @Test
    public void isFindByIdWorking(){
        MedicationRegisterEntity medicationRegister = new MedicationRegisterEntity();
        medicationRegister.setPresentation("ml");
        medicationRegister.setQuantity(12);
        medicationRegister.setAdditionalNotes("Tomar con precaución");

        MedicationRegisterEntity savedMedicationRegister = medicationRegisterRepository.save(medicationRegister);
        MedicationRegisterEntity findMedicationRegister = medicationRegisterRepository.findById(savedMedicationRegister.getId()).orElse(null);
        assertNotNull(findMedicationRegister);
        assertEquals(savedMedicationRegister,findMedicationRegister);
        assertEquals(savedMedicationRegister.getId(),findMedicationRegister.getId());
    }

    @Test
    public void isUpdateWorking() {
        MedicationRegisterEntity medicationRegister = new MedicationRegisterEntity();
        medicationRegister.setPresentation("ml");
        medicationRegister.setQuantity(12);
        medicationRegister.setAdditionalNotes("Tomar con precaución");
        MedicationRegisterEntity savedMedicationRegister = medicationRegisterRepository.save(medicationRegister);
        savedMedicationRegister.setQuantity(25);
        MedicationRegisterEntity updatedMedicationRegister = medicationRegisterRepository.findById(savedMedicationRegister.getId()).orElse(null);

        assertNotNull(updatedMedicationRegister);
        assertEquals(savedMedicationRegister.getId(),updatedMedicationRegister.getId());
        assertEquals(25,updatedMedicationRegister.getQuantity());
    }

    @Test
    public void isDeleteWorking() {
        MedicationRegisterEntity medicationRegister = new MedicationRegisterEntity();
        medicationRegister.setPresentation("ml");
        medicationRegister.setQuantity(12);
        medicationRegister.setAdditionalNotes("Tomar con precaución");
        MedicationRegisterEntity savedMedicationRegister = medicationRegisterRepository.save(medicationRegister);
        Long id = savedMedicationRegister.getId();
        medicationRegisterRepository.delete(savedMedicationRegister);
        MedicationRegisterEntity deletedMedicationRegister = medicationRegisterRepository.findById(id).orElse(null);
        assertNull(deletedMedicationRegister);
    }
}
