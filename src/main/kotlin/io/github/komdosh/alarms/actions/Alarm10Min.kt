package io.github.komdosh.alarms.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.ServiceManager
import io.github.komdosh.alarms.services.AlarmService
import java.util.concurrent.TimeUnit

class Alarm10Min : AnAction("Alarm10Min") {
    override fun actionPerformed(event: AnActionEvent) {
        event.project?.let{
            val alarmService = ServiceManager.getService(AlarmService::class.java)
            alarmService.setAlertInSeconds(it, TimeUnit.MINUTES, 10)
        }
    }
}
