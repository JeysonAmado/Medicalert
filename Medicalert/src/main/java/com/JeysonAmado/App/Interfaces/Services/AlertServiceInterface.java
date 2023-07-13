package com.JeysonAmado.App.Interfaces.Services;

import com.JeysonAmado.App.Dto.Alerts.AlertDto;
import com.JeysonAmado.App.Entities.Alerts.AlertEntity;

import java.util.List;

public interface AlertServiceInterface {
    AlertEntity store(AlertEntity alert, Long userId);

    AlertDto getById(Long id);

    List<AlertDto> getAll();

    AlertEntity update(AlertDto alertDto, Long alertId, Long userId);

    AlertEntity delete(Long alertId, Long userId);

    AlertDto takeDose(Long alertId);

}