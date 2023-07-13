package com.JeysonAmado.App.Http.Controllers.Alerts;

import com.JeysonAmado.App.Dto.Alerts.AlertDto;
import com.JeysonAmado.App.Entities.Alerts.AlertEntity;
import com.JeysonAmado.App.Http.Config.JWTUtilities;
import com.JeysonAmado.App.Http.Controllers.BaseController;
import com.JeysonAmado.App.Interfaces.Services.AlertServiceInterface;
import com.JeysonAmado.App.Utilities.GlobalHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController extends BaseController {


    private final AlertServiceInterface alertService;

    @Autowired
    public AlertController(JWTUtilities jwtUtilities, AlertServiceInterface alertService) {
        super(jwtUtilities);
        this.alertService = alertService;
    }


    @PostMapping("/store")
    public ResponseEntity<AlertEntity> store(@RequestBody AlertEntity alert,
                                             @RequestHeader(value = "Authorization") String authorizationToken) {
        Long userId = getCurrentUserId(authorizationToken);
        AlertEntity createdAlert = alertService.store(alert,userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAlert);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<AlertDto> getAlert(@PathVariable Long id) {
        AlertDto alertDto = alertService.getById(id);
        return (alertDto != null) ? ResponseEntity.ok(alertDto) : ResponseEntity.notFound().build();

    }

    @GetMapping
    public ResponseEntity<List<AlertDto>> getAllAlerts() {
        List<AlertDto> alerts = alertService.getAll();
        return ResponseEntity.ok(alerts);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AlertEntity> updateUser(@RequestBody AlertDto alertDto,
                                                               @PathVariable Long id,
                                                               @RequestHeader(value = "Authorization") String authorizationToken) {
        Long userId = getCurrentUserId(authorizationToken);
        return (alertService.getById(id) == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(alertService.update(alertDto, id, userId));

    }

    @DeleteMapping("delete/{alertId}")
    public ResponseEntity<String> deleteAlert(@PathVariable Long alertId, @RequestHeader(value = "Authorization") String authorizationToken) {
        Long userId = getCurrentUserId(authorizationToken);
        if (alertService.getById(alertId) == null) {
            return ResponseEntity.notFound().build();
        }
        AlertEntity alert = alertService.delete(alertId, userId);
        return ResponseEntity.ok("Alerta " + alert.getName() + " ha sido eliminado");
    }

    @GetMapping("take_dose/{alertId}")
    public ResponseEntity<String> takeDose(@PathVariable Long alertId) {
        if (alertService.getById(alertId) == null) {
            return ResponseEntity.notFound().build();
        }
        AlertDto alert = alertService.takeDose(alertId);
        return ResponseEntity.ok("Alarma actualizada a las  " + GlobalHelper.formatDateTime(alert.getNextAlertAt()));
    }
}
