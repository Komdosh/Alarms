package io.github.komdosh.alarms.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.ui.Messages
import io.github.komdosh.alarms.services.AlarmService
import java.util.concurrent.TimeUnit

class AlarmCustomMin : AnAction("AlarmCustomMin") {
    override fun actionPerformed(event: AnActionEvent) {
        event.project?.let {
            val minutesString = Messages.showInputDialog(it, "Set scheduler minutes delay for alarm",
                    "Alarm Set Custom Delay", Messages.getInformationIcon())

            try {
                if (minutesString.isNullOrBlank()) {
                    throw NumberFormatException("String couldn't be blank or null")
                }
                val minutes = Integer.parseInt(minutesString)

                val alarmService = ServiceManager.getService(AlarmService::class.java)
                alarmService.setAlertInSeconds(it, TimeUnit.MINUTES, minutes.toLong())
            } catch (e: NumberFormatException) {
                Messages.showMessageDialog(it, "Wrong number of minutes",
                        "Alarm Is Not Set", Messages.getErrorIcon())
            }
        }
    }
}
