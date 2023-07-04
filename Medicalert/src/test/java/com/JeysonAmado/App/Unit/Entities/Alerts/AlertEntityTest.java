package com.JeysonAmado.App.Unit.Entities.Alerts;

import com.JeysonAmado.App.Entities.Alerts.AlertEntity;
import com.JeysonAmado.App.Entities.Medications.MedicationRegisterEntity;
import com.JeysonAmado.App.Entities.Users.UserEntity;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlertEntityTest {

    @Test
    public void isSetAndGetMedicationRegisterWorking(){
        AlertEntity alert = new AlertEntity();
        MedicationRegisterEntity medicationRegister= new MedicationRegisterEntity();
        medicationRegister.setQuantity(2.8);
        alert.setMedicationRegister(medicationRegister);
        assertEquals(2.8,alert.getMedicationRegister().getQuantity());
    }

    @Test
    public void isSetAndGetHoursToRepeatWorking(){
        AlertEntity alert = new AlertEntity();
        alert.setHoursToRepeat(1.5);
        assertEquals(1.5,alert.getHoursToRepeat());
    }

    @Test
    public void isSetAndGetDosesTakenWorking(){
        AlertEntity alert = new AlertEntity();
        alert.setDosesTaken(3);
        assertEquals(3,alert.getDosesTaken());
    }

    @Test
    public void isSetAndGetStartAtWorking(){
        AlertEntity alert = new AlertEntity();
        alert.setStartAt(LocalDateTime.of(2020,11,23,0,0));
        assertEquals(LocalDateTime.of(2020,11,23,0,0),alert.getStartAt());
    }

    @Test
    public void isSetAndGetNextAlertAtWorking(){
        AlertEntity alert = new AlertEntity();
        alert.setNextAlertAt(LocalDateTime.of(2022 ,7,4,14,20));
        assertEquals(LocalDateTime.of(2022 ,7,4,14,20),alert.getNextAlertAt());
    }

    @Test
    public void isSetAndGetUserWhoCreatedWorking(){
        AlertEntity alert = new AlertEntity();
        UserEntity user = new UserEntity();
        user.setEmail("user@example.com");
        alert.setUserWhoCreated(user);
        assertEquals("user@example.com",alert.getUserWhoCreated().getEmail());
    }

    @Test
    public void isSetAndGetUserWhoUpdatedWorking(){
        AlertEntity alert = new AlertEntity();
        UserEntity user = new UserEntity();
        user.setEmail("user@example.com");
        alert.setUserWhoUpdated(user);
        assertEquals("user@example.com",alert.getUserWhoUpdated().getEmail());
    }

    @Test
    public void isSetAndGetUserWhoDeletedWorking(){
        AlertEntity alert = new AlertEntity();
        UserEntity user = new UserEntity();
        user.setEmail("user@example.com");
        alert.setUserWhoDeleted(user);
        assertEquals("user@example.com",alert.getUserWhoDeleted().getEmail());
    }
}
