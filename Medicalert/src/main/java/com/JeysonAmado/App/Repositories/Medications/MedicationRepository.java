package com.JeysonAmado.App.Repositories.Medications;

import com.JeysonAmado.App.Entities.Medications.MedicationEntity;
import com.JeysonAmado.App.Repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends BaseRepository<MedicationEntity,Long> {
}
