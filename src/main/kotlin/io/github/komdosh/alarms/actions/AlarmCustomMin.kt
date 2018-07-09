package io.github.komdosh.alarms.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.ui.Messages
import io.github.komdosh.alarms.services.AlarmService
import java.util.concurrent.TimeUnit

class AlarmCustomMin : AnAction("AlarmCustomMin") {
    override fun actionPerformed(event: AnActionEvent) {
        val p = event.project
        val s = Messages.showInputDialog(p, "Set scheduler minutes delay for alarm",
                "Alarm Set Custom Delay", Messages.getInformationIcon())

        try {
            if (s == null) {
                throw NumberFormatException("String is null")
            }
            val minutes = Integer.parseInt(s)

            val alarmService = ServiceManager.getService(AlarmService::class.java)
            alarmService.setAlertInSeconds(p!!, TimeUnit.MINUTES.toSeconds(minutes.toLong()), "minutes")
        } catch (e: NumberFormatException) {
            Messages.showMessageDialog(p, "Wrong number of minutes",
                    "Alarm Is Not Set", Messages.getErrorIcon())
        }

    }
}