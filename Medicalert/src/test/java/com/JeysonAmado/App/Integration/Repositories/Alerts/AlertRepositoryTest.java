package com.JeysonAmado.App.Integration.Repositories.Alerts;

import com.JeysonAmado.App.Entities.Alerts.AlertEntity;
import com.JeysonAmado.App.Entities.Medications.MedicationEntity;
import com.JeysonAmado.App.Entities.Medications.MedicationRegisterEntity;
import com.JeysonAmado.App.Entities.Medications.MedicationTypeEntity;
import com.JeysonAmado.App.Repositories.Alerts.AlertRepository;
import com.JeysonAmado.App.Repositories.Medications.MedicationRegisterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AlertRepositoryTest {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private MedicationRegisterRepository medicationRegisterRepository;

    @Test
    public void isSaveWorking(){
        AlertEntity alertEntity = new AlertEntity();
        MedicationRegisterEntity medicationRegister = new MedicationRegisterEntity();
        medicationRegister.setQuantity(12);
        alertEntity.setMedicationRegister(medicationRegisterRepository.save(medicationRegister));
        alertEntity.setName("Alarma Doña Juana");
        alertEntity.setHoursToRepeat(4);
        alertEntity.setDosesTaken(0);
        alertEntity.setStartAt(LocalDateTime.of(2020,11,23,0,0));
        alertEntity.setNextAlertAt(LocalDateTime.of(2022 ,7,4,14,20));
        AlertEntity savedAlert = alertRepository.save(alertEntity);


        assertNotNull(savedAlert.getId());
        assertEquals(12, savedAlert.getMedicationRegister().getQuantity());
        assertEquals("Alarma Doña Juana", savedAlert.getName());
        assertEquals(4, savedAlert.getHoursToRepeat());
        assertEquals(0, savedAlert.getDosesTaken());
        assertEquals(LocalDateTime.of(2020,11,23,0,0), savedAlert.getStartAt());
        assertEquals(LocalDateTime.of(2022 ,7,4,14,20), savedAlert.getNextAlertAt());
    }

    @Test
    public void isFindByIdWorking(){
        AlertEntity alertEntity = new AlertEntity();
        alertEntity.setName("Alarma Doña Juana");
        alertEntity.setHoursToRepeat(4);
        alertEntity.setDosesTaken(0);
        AlertEntity savedAlert = alertRepository.save(alertEntity);
        AlertEntity findAlert = alertRepository.findById(savedAlert.getId()).orElse(null);

        assertNotNull(findAlert);
        assertEquals(savedAlert,findAlert);
        assertEquals(savedAlert.getId(),findAlert.getId());
        assertEquals(0,findAlert.getDosesTaken());
    }

    @Test
    public void isUpdateWorking() {
        AlertEntity alertEntity = new AlertEntity();
        alertEntity.setName("Alarma Doña Juana");
        alertEntity.setHoursToRepeat(4);
        alertEntity.setDosesTaken(0);
        AlertEntity savedAlert = alertRepository.save(alertEntity);
        savedAlert.setName("Alarma Julio");
        AlertEntity updatedAlert = alertRepository.findById(savedAlert.getId()).orElse(null);

        assertNotNull(updatedAlert);
        assertEquals(savedAlert.getId(),updatedAlert.getId());
        assertEquals("Alarma Julio",updatedAlert.getName());
    }

    @Test
    public void isDeleteWorking() {
        AlertEntity alertEntity = new AlertEntity();
        alertEntity.setName("Alarma Doña Juana");
        alertEntity.setHoursToRepeat(4);
        alertEntity.setDosesTaken(0);
        AlertEntity savedAlert = alertRepository.save(alertEntity);
        Long id = savedAlert.getId();
        alertRepository.delete(savedAlert);
        AlertEntity deletedAlert = alertRepository.findById(id).orElse(null);

        assertNull(deletedAlert);
    }
}
