package org.tomighty.plugin.statistics;

import org.tomighty.bus.Subscriber;
import org.tomighty.bus.messages.time.TimerInterrupted;

/**
 * @author dobermai
 */
public class TimerInterruptedSubscriber implements Subscriber<TimerInterrupted>{
    @Override
    public void receive(final TimerInterrupted timerInterrupted) {
        System.out.println("interrupted");
    }
}
