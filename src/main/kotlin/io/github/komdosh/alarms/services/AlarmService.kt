package io.github.komdosh.alarms.services

import com.intellij.openapi.project.Project

interface AlarmService {
    fun setAlertInSeconds(p: Project, delay: Long, dimension: String)

    fun disableAlerts()
}