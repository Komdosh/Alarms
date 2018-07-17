package io.github.komdosh.alarms.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.ServiceManager
import io.github.komdosh.alarms.services.AlarmService
import java.util.concurrent.TimeUnit

class Alarm10Sec : AnAction("Alarm10Sec") {
    override fun actionPerformed(event: AnActionEvent) {
        val p = event.project

        val alarmService = ServiceManager.getService(AlarmService::class.java)
        alarmService.setAlertInSeconds(p!!, TimeUnit.SECONDS.toSeconds(10), "seconds")
    }
}