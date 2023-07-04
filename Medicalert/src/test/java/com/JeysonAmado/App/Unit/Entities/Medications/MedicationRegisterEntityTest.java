package com.JeysonAmado.App.Unit.Entities.Medications;

import com.JeysonAmado.App.Entities.Medications.MedicationEntity;
import com.JeysonAmado.App.Entities.Medications.MedicationRegisterEntity;
import com.JeysonAmado.App.Entities.Users.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedicationRegisterEntityTest {

    @Test
    public void isSetAndGetMedicationWorking(){
        MedicationRegisterEntity medicationRegister = new MedicationRegisterEntity();
        MedicationEntity medication = new MedicationEntity();
        medication.setName("Diclofenalco");
        medicationRegister.setMedication(medication);
        assertEquals("Diclofenalco",medicationRegister.getMedication().getName());
    }

    @Test
    public void isSetAndGetPresentationWorking(){
        MedicationRegisterEntity medicationRegister = new MedicationRegisterEntity();
        medicationRegister.setPresentation("ml");
        assertEquals("ml",medicationRegister.getPresentation());
    }

    @Test
    public void isSetAndGetQuantityWorking(){
        MedicationRegisterEntity medicationRegister = new MedicationRegisterEntity();
        medicationRegister.setQuantity(3.6);
        assertEquals(3.6,medicationRegister.getQuantity());
    }

    @Test
    public void isSetAndGetAdditionalNotesWorking(){
        MedicationRegisterEntity medicationRegister = new MedicationRegisterEntity();
        medicationRegister.setAdditionalNotes("Comer antes de consumir");
        assertEquals("Comer antes de consumir",medicationRegister.getAdditionalNotes());
    }

    @Test
    public void isSetAndGetUserWhoCreatedWorking(){
        MedicationRegisterEntity medicationRegister = new MedicationRegisterEntity();
        UserEntity user = new UserEntity();
        user.setEmail("user@example.com");
        medicationRegister.setUserWhoCreated(user);
        assertEquals("user@example.com",medicationRegister.getUserWhoCreated().getEmail());
    }

    @Test
    public void isSetAndGetUserWhoUpdatedWorking(){
        MedicationRegisterEntity medicationRegister = new MedicationRegisterEntity();
        UserEntity user = new UserEntity();
        user.setEmail("user@example.com");
        medicationRegister.setUserWhoUpdated(user);
        assertEquals("user@example.com",medicationRegister.getUserWhoUpdated().getEmail());
    }

    @Test
    public void isSetAndGetUserWhoDeletedWorking(){
        MedicationRegisterEntity medicationRegister = new MedicationRegisterEntity();
        UserEntity user = new UserEntity();
        user.setEmail("user@example.com");
        medicationRegister.setUserWhoDeleted(user);
        assertEquals("user@example.com",medicationRegister.getUserWhoDeleted().getEmail());
    }
}
