package io.github.komdosh.alarms.services.impl

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import io.github.komdosh.alarms.services.AlarmService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class AlarmServiceImpl : AlarmService {
    private var scheduler = Executors.newSingleThreadScheduledExecutor()

    override fun setAlertInSeconds(p: Project, delay: Long, dimension: String) {
        val delayNumberForText: Long = if (dimension.equals("minutes", ignoreCase = true)) {
            TimeUnit.SECONDS.toMinutes(delay)
        } else {
            delay
        }

        Messages.showMessageDialog(p, "Alarm schedule for every $delayNumberForText $dimension",
                "Alarm is Set", Messages.getInformationIcon())

        scheduler.scheduleWithFixedDelay({
            ApplicationManager.getApplication().invokeAndWait({
                Messages.showMessageDialog(p, "You should take a break," +
                    " alarm is set for every " + delayNumberForText + " " + dimension,
                    "Time to Relax", Messages.getInformationIcon())
            }, ModalityState.NON_MODAL)
        }, delay, delay, TimeUnit.SECONDS)
    }

    override fun disableAlerts(p: Project) {
        Messages.showMessageDialog(p, "Alarm is disabled",
                "Alarm is Disabled", Messages.getInformationIcon())
        scheduler.shutdown()
        scheduler = Executors.newSingleThreadScheduledExecutor()
    }
}