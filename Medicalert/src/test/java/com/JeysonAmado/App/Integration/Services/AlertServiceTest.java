package com.JeysonAmado.App.Integration.Services;


import com.JeysonAmado.App.Dto.Alerts.AlertDto;
import com.JeysonAmado.App.Entities.Alerts.AlertEntity;
import com.JeysonAmado.App.Entities.Users.UserEntity;
import com.JeysonAmado.App.Interfaces.Services.AlertServiceInterface;
import com.JeysonAmado.App.Maps.Alerts.AlertMap;
import com.JeysonAmado.App.Repositories.Alerts.AlertRepository;
import com.JeysonAmado.App.Services.AlertService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Import({AlertService.class, AlertMap.class})
public class AlertServiceTest {


    @Autowired
    private AlertServiceInterface alertService;

    @Autowired
    private AlertRepository alertRepository;

    @Test
    public void isStoreWorking(){
        AlertEntity alertEntity = new AlertEntity();
        alertEntity.setName("Medicina Prueba");
        alertEntity.setMedicationRegisterId(4L);
        alertEntity.setHoursToRepeat(8.5);
        LocalDateTime startAtDate = LocalDateTime.of(2023,7,13,0,0);
        LocalDateTime nextAlertDate = LocalDateTime.of(2023,7,13,8,30);
        alertEntity.setStartAt(startAtDate);


        UserEntity user = Mockito.mock(UserEntity.class);
        Mockito.when(user.getId()).thenReturn(1L);

        AlertEntity storedAlert = alertService.store(alertEntity,user.getId());
        AlertEntity foundAlert = alertRepository.findById(storedAlert.getId()).orElse(null);
        assertNotNull(foundAlert);
        assertEquals(storedAlert,foundAlert);
        assertEquals("Medicina Prueba", foundAlert.getName());
        assertEquals(4L, foundAlert.getMedicationRegisterId());
        assertEquals(8.5, foundAlert.getHoursToRepeat());
        assertEquals(startAtDate,foundAlert.getStartAt());
        assertEquals(0,foundAlert.getDosesTaken());
        assertEquals(nextAlertDate,foundAlert.getNextAlertAt());
        assertEquals(1L,foundAlert.getUserWhoCreatedId());
    }

    @Test
    public void isGetByIdWorking(){
        AlertEntity alertEntity = new AlertEntity();
        alertEntity.setName("Medicina Prueba");
        alertEntity.setMedicationRegisterId(4L);
        alertEntity.setHoursToRepeat(8.5);
        LocalDateTime startAtDate = LocalDateTime.of(2023,7,13,0,0);
        LocalDateTime nextAlertDate = LocalDateTime.of(2023,7,13,8,30);
        alertEntity.setStartAt(startAtDate);


        UserEntity user = Mockito.mock(UserEntity.class);
        Mockito.when(user.getId()).thenReturn(2L);

        AlertEntity storedAlert = alertService.store(alertEntity,user.getId());
        AlertDto foundAlert = alertService.getById(storedAlert.getId());
        assertNotNull(foundAlert);
        assertEquals("Medicina Prueba", foundAlert.getName());
        assertEquals(4L, foundAlert.getMedicationRegister());
        assertEquals(8.5, foundAlert.getHoursToRepeat());
        assertEquals(startAtDate,foundAlert.getStartAt());
        assertEquals(0,foundAlert.getDosesTaken());
        assertEquals(nextAlertDate,foundAlert.getNextAlertAt());
    }

    @Test
    public void isUpdateWorking(){
        AlertEntity alertEntity = new AlertEntity();
        alertEntity.setName("Medicina Prueba");
        alertEntity.setMedicationRegisterId(4L);
        alertEntity.setHoursToRepeat(8.5);
        LocalDateTime startAtDate = LocalDateTime.of(2023,7,13,0,0);
        alertEntity.setStartAt(startAtDate);


        UserEntity user = Mockito.mock(UserEntity.class);
        Mockito.when(user.getId()).thenReturn(3L);

        AlertEntity storedAlert = alertService.store(alertEntity,user.getId());

        AlertDto alertDto = new AlertDto();
        alertDto.setName("Medicina Prueba 2");

        AlertEntity updatedAlert = alertService.update(alertDto,storedAlert.getId(),3L);
        assertNotNull(updatedAlert);
        assertEquals(storedAlert.getId(),updatedAlert.getId());
        assertEquals("Medicina Prueba 2",updatedAlert.getName());
        assertEquals(3L,updatedAlert.getUserWhoUpdatedId());
    }

    @Test
    public void isDeleteWorking(){
        AlertEntity alertEntity = new AlertEntity();
        alertEntity.setName("Medicina Prueba");
        alertEntity.setMedicationRegisterId(4L);
        alertEntity.setHoursToRepeat(8.5);
        LocalDateTime startAtDate = LocalDateTime.of(2023,7,13,0,0);
        alertEntity.setStartAt(startAtDate);


        UserEntity user = Mockito.mock(UserEntity.class);
        Mockito.when(user.getId()).thenReturn(4L);

        AlertEntity storedAlert = alertService.store(alertEntity,user.getId());
        AlertEntity deletedAlert = alertService.delete(storedAlert.getId(),4L);

        assertNotNull(deletedAlert);
        assertEquals(storedAlert.getId(),deletedAlert.getId());
        assertEquals(4L,deletedAlert.getUserWhoDeletedId());
    }

    @Test
    public void isTakeDoseWorking(){
        AlertEntity alertEntity = new AlertEntity();
        alertEntity.setName("Medicina Prueba");
        alertEntity.setMedicationRegisterId(4L);
        alertEntity.setHoursToRepeat(8.5);
        LocalDateTime startAtDate = LocalDateTime.of(2023,7,13,0,0);
        alertEntity.setStartAt(startAtDate);


        UserEntity user = Mockito.mock(UserEntity.class);
        Mockito.when(user.getId()).thenReturn(4L);

        AlertEntity storedAlert = alertService.store(alertEntity,user.getId());
        AlertDto dose = alertService.takeDose(storedAlert.getId());

        assertNotNull(dose);
        assertEquals(1,dose.getDosesTaken());
    }



}
