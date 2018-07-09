package io.github.komdosh.alarms.services.impl

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import io.github.komdosh.alarms.services.AlarmService
import java.lang.reflect.InvocationTargetException
import java.util.concurrent.TimeUnit
import java.util.logging.Logger
import javax.swing.SwingUtilities

class AlarmServiceImpl : AlarmService {
    private var isEnabled = false
    private var executionTimeoutLoop: Thread? = null
    private val logger = Logger.getLogger(AlarmService::class.java.name)

    override fun setAlertInSeconds(p: Project, delay: Long, dimension: String) {
        val delayNumberForText: Long = if (dimension.equals("minutes", ignoreCase = true)) {
            TimeUnit.SECONDS.toMinutes(delay)
        } else {
            delay
        }

        Messages.showMessageDialog(p, "Alarm schedule for every $delayNumberForText $dimension",
                "Alarm Is Set", Messages.getInformationIcon())

        isEnabled = true

        setTimeout({
            Messages.showMessageDialog(p, "You should take a break," +
                    " alarm is set for every " + delayNumberForText + " " + dimension,
                    "Time to Relax", Messages.getInformationIcon())
        }, delay)
    }

    private fun setTimeout(s: () -> Unit, delay: Long) {
        if (!isEnabled) {
            return
        }

        executionTimeoutLoop = Thread { setExecution(s, delay) }

        executionTimeoutLoop!!.start()
    }

    private fun setExecution(s: () -> Unit, delay: Long) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(delay))
            SwingUtilities.invokeAndWait(s)
            setTimeout(s, delay)
        } catch (e: InterruptedException) {
            logger.info("io.github.komdosh.alarms: Alarm Thread Interrupted")
        } catch (e: InvocationTargetException) {
            logger.info("io.github.komdosh.alarms: InvocationTargetException")
        }

    }

    override fun disableAlerts() {
        isEnabled = false
        executionTimeoutLoop!!.interrupt()
    }
}