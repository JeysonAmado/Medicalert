package com.JeysonAmado.App.Functional.Controllers.Alerts;

import com.JeysonAmado.App.Dto.Alerts.AlertDto;
import com.JeysonAmado.App.Dto.Auth.LoginDto;
import com.JeysonAmado.App.Entities.Alerts.AlertEntity;
import com.JeysonAmado.App.Http.Controllers.Alerts.AlertController;
import com.JeysonAmado.App.Http.Controllers.Auth.AuthController;
import com.JeysonAmado.App.Repositories.Alerts.AlertRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
public class AlertControllerTest {

    @Autowired
    private AuthController authController;

    @Autowired
    private AlertController alertController;

    @Autowired
    private AlertRepository alertRepository;

    private String jwt;


    @BeforeEach
    public void setUp(){
        alertRepository.deleteAll();
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("goku@example.com");
        loginDto.setPassword("goku123");

        ResponseEntity<String> response = authController.login(loginDto);
        this.jwt = "Bearer " + Objects.requireNonNull(response.getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
    }

    @Test
    public void isStoreWorking() {
        AlertEntity alert = new AlertEntity();
        alert.setMedicationRegisterId(1L);
        alert.setName("Alerta Test");
        alert.setHoursToRepeat(6);
        LocalDateTime startAtDate = LocalDateTime.of(2023,7,13,0,0);
        alert.setStartAt(startAtDate);

        ResponseEntity<AlertEntity> response = alertController.store(alert,this.jwt);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }

    @Test
    public void isGetAlertWorking() {
        AlertEntity alert = new AlertEntity();
        alert.setMedicationRegisterId(1L);
        alert.setName("Alerta Test");
        alert.setHoursToRepeat(6);
        LocalDateTime startAtDate = LocalDateTime.of(2023,7,13,0,0);
        alert.setStartAt(startAtDate);

        ResponseEntity<AlertEntity> storeResponse = alertController.store(alert, this.jwt);
        Long id = Objects.requireNonNull(storeResponse.getBody()).getId();
        ResponseEntity<AlertDto> response = alertController.getAlert(id);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void isGetAlertNotFoundWorking() {
        ResponseEntity<AlertDto> response = alertController.getAlert(200L);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void isGetAllAlertsWorking() {
        LocalDateTime startAtDate = LocalDateTime.of(2023,7,13,0,0);

        AlertEntity alert = new AlertEntity();
        alert.setMedicationRegisterId(1L);
        alert.setName("Alerta Test");
        alert.setHoursToRepeat(6);
        alert.setStartAt(startAtDate);

        AlertEntity alert2 = new AlertEntity();
        alert2.setMedicationRegisterId(1L);
        alert2.setName("Alerta Test2");
        alert2.setHoursToRepeat(24);
        alert2.setStartAt(startAtDate);

        alertController.store(alert, this.jwt);
        alertController.store(alert2, this.jwt);

        ResponseEntity<List<AlertDto>> response = alertController.getAllAlerts();
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    public void isUpdateWorking(){
        AlertEntity alert = new AlertEntity();
        alert.setMedicationRegisterId(1L);
        alert.setName("Alerta Test");
        alert.setHoursToRepeat(6);
        alert.setStartAt(LocalDateTime.of(2023,7,13,0,0));

        ResponseEntity<AlertEntity> storeResponse = alertController.store(alert, this.jwt);
        Long id = Objects.requireNonNull(storeResponse.getBody()).getId();

        AlertDto alertDto = new AlertDto();
        alertDto.setName("Alerta Actualizada");

        ResponseEntity<AlertEntity> updateStore = alertController.updateUser(alertDto, id, this.jwt);
        assertEquals(HttpStatus.OK,updateStore.getStatusCode());
        assertEquals("Alerta Actualizada", Objects.requireNonNull(updateStore.getBody()).getName());
    }

    @Test
    public void isDeleteWorking(){
        AlertEntity alert = new AlertEntity();
        alert.setMedicationRegisterId(1L);
        alert.setName("Alerta Test");
        alert.setHoursToRepeat(6);
        alert.setStartAt(LocalDateTime.of(2023,7,13,0,0));

        ResponseEntity<AlertEntity> storeResponse = alertController.store(alert, this.jwt);
        Long id = Objects.requireNonNull(storeResponse.getBody()).getId();

        ResponseEntity<String> updateStore = alertController.deleteAlert(id, this.jwt);
        assertEquals(HttpStatus.OK,updateStore.getStatusCode());
        assertEquals("Alerta " + alert.getName() + " ha sido eliminado", Objects.requireNonNull(updateStore.getBody()));
    }

    @Test
    public void isTakeDoseWorking(){
        AlertEntity alert = new AlertEntity();
        alert.setMedicationRegisterId(1L);
        alert.setName("Alerta Test");
        alert.setHoursToRepeat(6);
        alert.setStartAt(LocalDateTime.of(2023,7,13,0,0));

        ResponseEntity<AlertEntity> storeResponse = alertController.store(alert, this.jwt);
        Long id = Objects.requireNonNull(storeResponse.getBody()).getId();

        ResponseEntity<String> updateStore = alertController.takeDose(id);
        assertEquals(HttpStatus.OK,updateStore.getStatusCode());
        assertEquals(1, Objects.requireNonNull(alertController.getAlert(id).getBody()).getDosesTaken());
    }
}
