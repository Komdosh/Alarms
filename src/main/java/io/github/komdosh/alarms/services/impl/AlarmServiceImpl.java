package io.github.komdosh.alarms.services.impl;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import io.github.komdosh.alarms.services.AlarmService;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class AlarmServiceImpl implements AlarmService {
    private boolean isEnabled = false;
    private Thread executionTimeoutLoop;
    private Logger logger = Logger.getLogger(AlarmService.class.getName());

    public void setAlertInSeconds(Project p, long delay, String dimension) {
        long delayNumberForText;
        if (dimension.equalsIgnoreCase("minutes")) {
            delayNumberForText = TimeUnit.SECONDS.toMinutes(delay);
        } else {
            delayNumberForText = delay;
        }

        Messages.showMessageDialog(p, "Alarm schedule for every " + delayNumberForText + " " + dimension,
            "Alarm Is Set", Messages.getInformationIcon());

        isEnabled = true;

        setTimeout(() -> Messages.showMessageDialog(p, "You should take a break," +
                " alarm is set for every " + delay + " " + dimension,
            "Time to Relax", Messages.getInformationIcon()), delay);
    }

    private void setTimeout(Runnable s, long delay) {
        if (!isEnabled) {
            return;
        }

        executionTimeoutLoop = new Thread(() -> {
            setExecution(s, delay);
        });

        executionTimeoutLoop.start();
    }

    private void setExecution(Runnable s, long delay) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(delay));
            SwingUtilities.invokeAndWait(s);
            setTimeout(s, delay);
        } catch (InterruptedException e) {
            logger.info("io.github.komdosh.alarms: Alarm Thread Interrupted");
        } catch (InvocationTargetException e) {
            logger.info("io.github.komdosh.alarms: InvocationTargetException");
        }
    }

    public void disableAlerts() {
        isEnabled = false;
        executionTimeoutLoop.interrupt();
    }
}
