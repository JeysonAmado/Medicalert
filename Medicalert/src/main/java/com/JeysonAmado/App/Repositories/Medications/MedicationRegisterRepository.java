package com.JeysonAmado.App.Repositories.Medications;

import com.JeysonAmado.App.Entities.Medications.MedicationRegisterEntity;
import com.JeysonAmado.App.Repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRegisterRepository extends BaseRepository<MedicationRegisterEntity,Long> {
}
