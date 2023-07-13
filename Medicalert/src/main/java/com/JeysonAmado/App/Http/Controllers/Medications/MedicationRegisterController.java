package com.JeysonAmado.App.Http.Controllers.Medications;

import com.JeysonAmado.App.Dto.Medications.MedicationRegisterDto;
import com.JeysonAmado.App.Entities.Medications.MedicationRegisterEntity;
import com.JeysonAmado.App.Http.Config.JWTUtilities;
import com.JeysonAmado.App.Http.Controllers.BaseController;
import com.JeysonAmado.App.Interfaces.Services.MedicationRegisterServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("find/{id}")
    public ResponseEntity<MedicationRegisterDto> getMedicationRegister(@PathVariable Long id) {
        MedicationRegisterDto medicationRegister = medicationRegisterService.getById(id);
        if (medicationRegister != null) {
            return ResponseEntity.ok(medicationRegister);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<MedicationRegisterDto>> getAllMedicationsRegister() {
        List<MedicationRegisterDto> medicationRegisters = medicationRegisterService.getAll();
        return ResponseEntity.ok(medicationRegisters);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MedicationRegisterEntity> updateUser(@RequestBody MedicationRegisterDto medicationRegisterDto,
                                                               @PathVariable Long id,
                                                               @RequestHeader(value = "Authorization") String authorizationToken) {
        Long userId = getCurrentUserId(authorizationToken);
        if (medicationRegisterService.getById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medicationRegisterService.update(medicationRegisterDto,id,userId));
    }

    @DeleteMapping("delete/{medicationRegisterId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long medicationRegisterId, @RequestHeader(value = "Authorization") String authorizationToken) {
        Long userId = getCurrentUserId(authorizationToken);
        if (medicationRegisterService.getById(medicationRegisterId) == null) {
            return ResponseEntity.notFound().build();
        }
        MedicationRegisterEntity medicationRegister = medicationRegisterService.delete(medicationRegisterId, userId);
        return ResponseEntity.ok("Registro de medicamento " + medicationRegister.getMedication().getName() + " ha sido eliminado");
    }

}
