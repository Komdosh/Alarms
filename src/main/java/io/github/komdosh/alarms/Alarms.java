package io.github.komdosh.alarms;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Alarms {
    private static boolean isEnabled = false;
    private static Thread executionTimeoutLoop;
    private static Logger logger = Logger.getLogger(Alarms.class.getName());

    public static void setAlertInSeconds(Project p, long delay, String dimension) {
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

    private static void setTimeout(Runnable s, long delay) {
        if (!isEnabled) {
            return;
        }

        executionTimeoutLoop = new Thread(() -> {
            setExecution(s, delay);
        });

        executionTimeoutLoop.start();
    }

    private static void setExecution(Runnable s, long delay) {
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

    public static void disableAlerts() {
        isEnabled = false;
        executionTimeoutLoop.interrupt();
    }
}
