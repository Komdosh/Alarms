package io.github.komdosh.alarms.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import io.github.komdosh.alarms.Alarms;

public class AlarmDisable extends AnAction {

    public AlarmDisable() {
        super("AlarmDisable");
    }

    public void actionPerformed(AnActionEvent event) {
        Alarms.disableAlerts();
    }
}