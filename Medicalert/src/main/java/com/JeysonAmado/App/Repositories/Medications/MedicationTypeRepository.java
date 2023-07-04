package com.JeysonAmado.App.Repositories.Medications;

import com.JeysonAmado.App.Entities.Medications.MedicationTypeEntity;
import com.JeysonAmado.App.Repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationTypeRepository extends BaseRepository<MedicationTypeEntity,Long> {
}
