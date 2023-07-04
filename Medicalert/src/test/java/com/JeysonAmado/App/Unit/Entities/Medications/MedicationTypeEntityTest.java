package com.JeysonAmado.App.Unit.Entities.Medications;

import com.JeysonAmado.App.Entities.Medications.MedicationTypeEntity;
import com.JeysonAmado.App.Entities.Users.UserEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedicationTypeEntityTest {

    @Test
    public void isSetAndGetNameWorking(){
        MedicationTypeEntity medicationType = new MedicationTypeEntity();
        medicationType.setName("Antibióticos");
        assertEquals("Antibióticos",medicationType.getName());
    }

    @Test
    public void isSetAndGetUserWhoCreatedWorking(){
        MedicationTypeEntity medication = new MedicationTypeEntity();
        UserEntity user = new UserEntity();
        user.setEmail("user@example.com");
        medication.setUserWhoCreated(user);
        assertEquals("user@example.com",medication.getUserWhoCreated().getEmail());
    }

    @Test
    public void isSetAndGetUserWhoUpdatedWorking(){
        MedicationTypeEntity medication = new MedicationTypeEntity();
        UserEntity user = new UserEntity();
        user.setEmail("user@example.com");
        medication.setUserWhoUpdated(user);
        assertEquals("user@example.com",medication.getUserWhoUpdated().getEmail());
    }

    @Test
    public void isSetAndGetUserWhoDeletedWorking(){
        MedicationTypeEntity medication = new MedicationTypeEntity();
        UserEntity user = new UserEntity();
        user.setEmail("user@example.com");
        medication.setUserWhoDeleted(user);
        assertEquals("user@example.com",medication.getUserWhoDeleted().getEmail());
    }
}
