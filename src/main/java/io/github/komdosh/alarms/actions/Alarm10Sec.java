package io.github.komdosh.alarms.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import io.github.komdosh.alarms.Alarms;

import java.util.concurrent.TimeUnit;

public class Alarm10Sec extends AnAction {

    public Alarm10Sec() {
        super("Alarm10Sec");
    }

    public void actionPerformed(AnActionEvent event) {
        Project p = event.getProject();

        Alarms.setAlertInSeconds(p, TimeUnit.SECONDS.toSeconds(10), "Seconds");
    }
}