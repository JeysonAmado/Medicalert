package com.JeysonAmado.App.Services;

import com.JeysonAmado.App.Dto.Alerts.AlertDto;
import com.JeysonAmado.App.Entities.Alerts.AlertEntity;
import com.JeysonAmado.App.Interfaces.Services.AlertServiceInterface;
import com.JeysonAmado.App.Maps.Alerts.AlertMap;
import com.JeysonAmado.App.Repositories.Alerts.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertService implements AlertServiceInterface {

    private final AlertRepository alertRepository;

    private final AlertMap alertMap;

    @Autowired
    public AlertService(AlertRepository alertRepository, AlertMap alertMap) {
        this.alertRepository = alertRepository;
        this.alertMap = alertMap;
    }

    @Override
    public AlertEntity store(AlertEntity alert, Long userId) {
        alert.setDosesTaken(0);
        int[] hoursAndMinutes = alert.calculateHoursAndMinutes(alert.getHoursToRepeat());
        alert.calculateNextAlert(alert.getStartAt(), hoursAndMinutes);
        alert.commitCreate(userId);
        return alertRepository.save(alert);
    }

    @Override
    public AlertDto getById(Long id) {
        AlertEntity alert = alertRepository.findById(id).orElse(null);
        return alert != null ? alertMap.toDto(alert) : null;
    }

    @Override
    public List<AlertDto> getAll() {
        List<AlertEntity> allAlerts = alertRepository.findAll();
        return allAlerts.stream()
                .map(alertMap::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AlertEntity update(AlertDto alertDto, Long alertId, Long userId) {
        AlertEntity alert = alertRepository.findById(alertId).orElse(null);
        if (alert != null){
            alert.mergeDto(alertDto);
            alert.commitUpdate(userId);
            return alertRepository.save(alert);
        }
        return null;
    }

    @Override
    public AlertEntity delete(Long alertId, Long userId) {
        AlertEntity alert = alertRepository.findById(alertId).orElse(null);
        if (alert != null){
            alert.commitDelete(userId);
            return alertRepository.save(alert);
        }
        return null;
    }

    @Override
    public AlertDto takeDose(Long alertId) {
        AlertEntity alert = alertRepository.findById(alertId).orElse(null);
        if (alert != null){
            int[] hoursAndMinutes = alert.calculateHoursAndMinutes(alert.getHoursToRepeat());
            alert.calculateNextAlert(LocalDateTime.now(), hoursAndMinutes);
            alert.setDosesTaken(alert.getDosesTaken()+1);
            return alertMap.toDto(alertRepository.save(alert));
        }
        return null;
    }
}
