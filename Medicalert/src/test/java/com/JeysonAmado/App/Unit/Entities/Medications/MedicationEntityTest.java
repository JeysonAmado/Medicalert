package com.JeysonAmado.App.Unit.Entities.Medications;

import com.JeysonAmado.App.Entities.Medications.MedicationEntity;
import com.JeysonAmado.App.Entities.Medications.MedicationTypeEntity;
import com.JeysonAmado.App.Entities.Users.UserEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedicationEntityTest {

    @Test
    public void isSetAndGetNameWorking(){
        MedicationEntity medication = new MedicationEntity();
        medication.setName("Acetaminofen");
        assertEquals("Acetaminofen",medication.getName());
    }

    @Test
    public void isSetAndGetMedicationTypeWorking(){
        MedicationEntity medication = new MedicationEntity();
        MedicationTypeEntity medicationType = new MedicationTypeEntity();
        medicationType.setName("Analgésicos");
        medication.setMedicationType(medicationType);
        assertEquals("Analgésicos",medication.getMedicationType().getName());
    }

    @Test
    public void isSetAndGetUserWhoCreatedWorking(){
        MedicationEntity medication = new MedicationEntity();
        UserEntity user = new UserEntity();
        user.setEmail("user@example.com");
        medication.setUserWhoCreated(user);
        assertEquals("user@example.com",medication.getUserWhoCreated().getEmail());
    }

    @Test
    public void isSetAndGetUserWhoUpdatedWorking(){
        MedicationEntity medication = new MedicationEntity();
        UserEntity user = new UserEntity();
        user.setEmail("user@example.com");
        medication.setUserWhoUpdated(user);
        assertEquals("user@example.com",medication.getUserWhoUpdated().getEmail());
    }

    @Test
    public void isSetAndGetUserWhoDeletedWorking(){
        MedicationEntity medication = new MedicationEntity();
        UserEntity user = new UserEntity();
        user.setEmail("user@example.com");
        medication.setUserWhoDeleted(user);
        assertEquals("user@example.com",medication.getUserWhoDeleted().getEmail());
    }
}
