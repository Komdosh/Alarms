package io.github.komdosh.alarms.services

import com.intellij.openapi.project.Project
import java.util.concurrent.TimeUnit

interface AlarmService {
    fun setAlertInSeconds(p: Project, timeUnit: TimeUnit, value: Long)

    fun disableAlerts(p: Project)
}
