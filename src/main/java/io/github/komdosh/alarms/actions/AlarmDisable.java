package io.github.komdosh.alarms.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.components.ServiceManager;
import io.github.komdosh.alarms.services.AlarmService;

public class AlarmDisable extends AnAction {

    public AlarmDisable() {
        super("AlarmDisable");
    }

    public void actionPerformed(AnActionEvent event) {
        AlarmService alarmService = ServiceManager.getService(AlarmService.class);
        alarmService.disableAlerts();
    }
}