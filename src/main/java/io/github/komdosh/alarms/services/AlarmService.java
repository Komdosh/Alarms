package io.github.komdosh.alarms.services;

import com.intellij.openapi.project.Project;

public interface AlarmService {
    void setAlertInSeconds(Project p, long delay, String dimension);

    void disableAlerts();
}
