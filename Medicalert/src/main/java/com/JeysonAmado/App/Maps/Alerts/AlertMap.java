package com.JeysonAmado.App.Maps.Alerts;

import com.JeysonAmado.App.Dto.Alerts.AlertDto;
import com.JeysonAmado.App.Entities.Alerts.AlertEntity;
import com.JeysonAmado.App.Maps.BaseMap;
import org.springframework.stereotype.Component;

@Component
public class AlertMap extends BaseMap<AlertDto, AlertEntity> {

    public AlertMap() {
        super(AlertDto.class, AlertEntity.class);
    }
}
