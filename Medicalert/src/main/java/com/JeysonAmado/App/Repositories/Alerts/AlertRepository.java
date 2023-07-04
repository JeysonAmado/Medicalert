package com.JeysonAmado.App.Repositories.Alerts;

import com.JeysonAmado.App.Entities.Alerts.AlertEntity;
import com.JeysonAmado.App.Repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends BaseRepository<AlertEntity,Long> {
}
