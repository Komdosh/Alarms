package io.github.komdosh.alarms.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import io.github.komdosh.alarms.services.AlarmService;

import java.util.concurrent.TimeUnit;

public class AlarmCustomMin extends AnAction {

    public AlarmCustomMin() {
        super("Alarm20Min");
    }

    public void actionPerformed(AnActionEvent event) {
        Project p = event.getProject();
        String s = Messages.showInputDialog(p, "Set scheduler minutes delay for alarm:",
            "Alarm Is Set", Messages.getInformationIcon());

        try {
            if (s == null) {
                throw new NumberFormatException("String is null");
            }
            int minutes = Integer.parseInt(s);

            AlarmService alarmService = ServiceManager.getService(AlarmService.class);
            alarmService.setAlertInSeconds(p, TimeUnit.SECONDS.toSeconds(minutes), "Minutes");
        } catch (NumberFormatException e) {
            Messages.showMessageDialog(p, "Wrong number of minutes",
                "Alarm Is Not Set", Messages.getErrorIcon());
        }

    }
}