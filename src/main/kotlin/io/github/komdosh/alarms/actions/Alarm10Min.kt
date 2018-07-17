package io.github.komdosh.alarms.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.ServiceManager
import io.github.komdosh.alarms.services.AlarmService
import java.util.concurrent.TimeUnit

class Alarm10Min : AnAction("Alarm10Min") {
    override fun actionPerformed(event: AnActionEvent) {
        val p = event.project

        val alarmService = ServiceManager.getService(AlarmService::class.java)
        alarmService.setAlertInSeconds(p!!, TimeUnit.MINUTES.toSeconds(10), "minutes")
    }
}