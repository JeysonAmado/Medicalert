package com.JeysonAmado.App.Http.Controllers;

import com.JeysonAmado.App.Entities.Medications.MedicationRegisterEntity;
import com.JeysonAmado.App.Http.Config.JWTUtilities;
import com.JeysonAmado.App.Interfaces.Services.MedicationRegisterServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medications_register")
public class MedicationRegisterController extends BaseController {

    private final MedicationRegisterServiceInterface medicationRegisterService;

    @Autowired
    public MedicationRegisterController(JWTUtilities jwtUtilities, MedicationRegisterServiceInterface medicationRegisterService) {
        super(jwtUtilities);
        this.medicationRegisterService = medicationRegisterService;
    }

    @PostMapping("/store")
    public ResponseEntity<MedicationRegisterEntity> store(@RequestBody MedicationRegisterEntity medicationRegister,
                                                               @RequestHeader(value = "Authorization") String authorizationToken) {
        Long userId = getCurrentUserId(authorizationToken);
        MedicationRegisterEntity createdMedicationRegister = medicationRegisterService.store(medicationRegister,userId);
        return ResponseEntity.ok(createdMedicationRegister);
    }
}
