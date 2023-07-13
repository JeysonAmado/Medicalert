package com.JeysonAmado.App.Integration.Services;

import com.JeysonAmado.App.Dto.Medications.MedicationRegisterDto;
import com.JeysonAmado.App.Entities.Medications.MedicationEntity;
import com.JeysonAmado.App.Entities.Medications.MedicationRegisterEntity;
import com.JeysonAmado.App.Entities.Medications.MedicationTypeEntity;
import com.JeysonAmado.App.Entities.Users.UserEntity;
import com.JeysonAmado.App.Interfaces.Services.MedicationRegisterServiceInterface;
import com.JeysonAmado.App.Maps.Medications.MedicationRegisterMap;
import com.JeysonAmado.App.Repositories.Medications.MedicationRegisterRepository;
import com.JeysonAmado.App.Repositories.Medications.MedicationRepository;
import com.JeysonAmado.App.Repositories.Medications.MedicationTypeRepository;
import com.JeysonAmado.App.Repositories.Users.UserRepository;
import com.JeysonAmado.App.Services.MedicationRegisterService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({MedicationRegisterService.class, MedicationRegisterMap.class})
public class MedicationRegisterServiceTest {

    @Autowired
    private MedicationRegisterServiceInterface medicationRegisterService;

    @Autowired
    private MedicationRegisterRepository medicationRegisterRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private MedicationTypeRepository medicationTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  MedicationRegisterMap medicationRegisterMap;

    @Test
    public void isStoreWorking(){
        MedicationRegisterEntity medicationRegister = new MedicationRegisterEntity();
        medicationRegister.setMedication(new MedicationEntity());
        medicationRegister.setQuantity(3);
        medicationRegister.setPresentation("ml");
        medicationRegister.setAdditionalNotes(null);

        UserEntity user = Mockito.mock(UserEntity.class);
        Mockito.when(user.getId()).thenReturn(2L);

        MedicationRegisterEntity storedMedicationRegister = medicationRegisterService.store(medicationRegister,user.getId());
        MedicationRegisterEntity foundMedicationRegister = medicationRegisterRepository.findById(storedMedicationRegister.getId()).orElse(null);
        assertNotNull(foundMedicationRegister);
        assertEquals(storedMedicationRegister,foundMedicationRegister);
        assertEquals(3,foundMedicationRegister.getQuantity());
        assertEquals("ml",foundMedicationRegister.getPresentation());
        assertNull(foundMedicationRegister.getAdditionalNotes());
        assertEquals(2L,foundMedicationRegister.getUserWhoCreatedId());
    }

    @Test
    public void isGetByIdWorking(){
        MedicationRegisterEntity medicationRegister = new MedicationRegisterEntity();
        medicationRegister.setMedication(new MedicationEntity());
        medicationRegister.setQuantity(3);
        medicationRegister.setPresentation("ml");
        medicationRegister.setAdditionalNotes(null);

        UserEntity user = Mockito.mock(UserEntity.class);
        Mockito.when(user.getId()).thenReturn(2L);

        MedicationRegisterEntity storedMedicationRegister = medicationRegisterService.store(medicationRegister,user.getId());
        MedicationRegisterDto foundMedicationRegister = medicationRegisterService.getById(storedMedicationRegister.getId());
        assertNotNull(foundMedicationRegister);
        assertEquals(3,foundMedicationRegister.getQuantity());
        assertEquals("ml",foundMedicationRegister.getPresentation());
        assertNull(foundMedicationRegister.getAdditionalNotes());
    }

    @Test
    public void isGetAllWorking(){
        MedicationTypeEntity medicationType = new MedicationTypeEntity();
        medicationType.setName("Analgensico");
        MedicationTypeEntity savedMedicationType = medicationTypeRepository.save(medicationType);

        MedicationEntity medication = new MedicationEntity();
        medication.setName("Naproxeno");
        medication.setMedicationType(savedMedicationType);
        MedicationEntity savedMedication = medicationRepository.save(medication);

        MedicationRegisterEntity medicationRegisterOne = new MedicationRegisterEntity();
        medicationRegisterOne.setMedicationId(savedMedication.getId());
        medicationRegisterOne.setMedication(savedMedication);
        medicationRegisterOne.setQuantity(3);
        medicationRegisterOne.setPresentation("ml");
        medicationRegisterOne.setAdditionalNotes(null);

        MedicationRegisterEntity medicationRegisterTwo = new MedicationRegisterEntity();
        medicationRegisterTwo.setMedicationId(savedMedication.getId());
        medicationRegisterTwo.setMedication(savedMedication);
        medicationRegisterTwo.setQuantity(8);
        medicationRegisterTwo.setPresentation("ml");
        medicationRegisterTwo.setAdditionalNotes("Consumir en ayunas");

        UserEntity user = new UserEntity();
        user.setName("user");
        user.setEmail("user@example");
        user.setPassword("example");
        UserEntity savedUser = userRepository.save(user);

        MedicationRegisterEntity storedMedicationRegisterOne = medicationRegisterService.store(medicationRegisterOne,savedUser.getId());
        MedicationRegisterEntity storedMedicationRegisterTwo = medicationRegisterService.store(medicationRegisterTwo,savedUser.getId());
        List<MedicationRegisterDto> allMedicationRegister = medicationRegisterService.getAll();

        assertNotNull(allMedicationRegister);
        assertEquals(2, allMedicationRegister.size());
        assertTrue(allMedicationRegister.contains(medicationRegisterMap.toDto(storedMedicationRegisterOne)));
        assertTrue(allMedicationRegister.contains(medicationRegisterMap.toDto(storedMedicationRegisterTwo)));
    }

    @Test
    public void isUpdateWorking(){
        MedicationRegisterEntity medicationRegister = new MedicationRegisterEntity();
        medicationRegister.setMedication(new MedicationEntity());
        medicationRegister.setQuantity(3);
        medicationRegister.setPresentation("ml");
        medicationRegister.setAdditionalNotes(null);

        UserEntity user = Mockito.mock(UserEntity.class);
        Mockito.when(user.getId()).thenReturn(2L);

        MedicationRegisterEntity storedMedicationRegister = medicationRegisterService.store(medicationRegister,user.getId());

        MedicationRegisterDto medicationRegisterDto = new MedicationRegisterDto();
        medicationRegisterDto.setQuantity(8);
        medicationRegisterDto.setPresentation("gr");
        medicationRegisterDto.setAdditionalNotes("No comer en las siguientes dos horas");

        MedicationRegisterEntity updatedMedicationRegister = medicationRegisterService.update(medicationRegisterDto,storedMedicationRegister.getId(),3L);
        assertNotNull(updatedMedicationRegister);
        assertEquals(storedMedicationRegister.getId(),updatedMedicationRegister.getId());
        assertEquals(8,updatedMedicationRegister.getQuantity());
        assertEquals("gr",updatedMedicationRegister.getPresentation());
        assertNotNull(updatedMedicationRegister.getAdditionalNotes());
        assertEquals(2L,updatedMedicationRegister.getUserWhoCreatedId());
        assertEquals(3L,updatedMedicationRegister.getUserWhoUpdatedId());
    }

    @Test
    public void isDeleteWorking(){
        MedicationRegisterEntity medicationRegister = new MedicationRegisterEntity();
        medicationRegister.setMedication(new MedicationEntity());
        medicationRegister.setQuantity(3);
        medicationRegister.setPresentation("ml");
        medicationRegister.setAdditionalNotes(null);

        UserEntity user = Mockito.mock(UserEntity.class);
        Mockito.when(user.getId()).thenReturn(2L);

        MedicationRegisterEntity storedMedicationRegister = medicationRegisterService.store(medicationRegister,user.getId());

        MedicationRegisterDto medicationRegisterDto = new MedicationRegisterDto();
        medicationRegisterDto.setQuantity(8);
        medicationRegisterDto.setPresentation("gr");
        medicationRegisterDto.setAdditionalNotes("No comer en las siguientes dos horas");

        MedicationRegisterEntity updatedMedicationRegister = medicationRegisterService.update(medicationRegisterDto,storedMedicationRegister.getId(),3L);
        medicationRegisterService.delete(updatedMedicationRegister.getId(),4L);
        assertNotNull(updatedMedicationRegister);
        assertEquals(storedMedicationRegister.getId(),updatedMedicationRegister.getId());
        assertEquals(8,updatedMedicationRegister.getQuantity());
        assertEquals("gr",updatedMedicationRegister.getPresentation());
        assertNotNull(updatedMedicationRegister.getAdditionalNotes());
        assertEquals(2L,updatedMedicationRegister.getUserWhoCreatedId());
        assertEquals(3L,updatedMedicationRegister.getUserWhoUpdatedId());
        assertEquals(4L,updatedMedicationRegister.getUserWhoDeletedId());
    }

}
