package org.tomighty.plugin.statistics.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tomighty.bus.Subscriber;
import org.tomighty.bus.messages.timer.TimerInterrupted;

/**
 * @author dobermai
 */
public class TimerInterruptedSubscriber implements Subscriber<TimerInterrupted> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void receive(final TimerInterrupted timerInterrupted) {
        log.info("interrupted");
    }
}
