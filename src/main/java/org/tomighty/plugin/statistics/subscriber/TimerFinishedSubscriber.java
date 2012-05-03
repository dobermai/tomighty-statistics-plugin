package org.tomighty.plugin.statistics.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tomighty.bus.Subscriber;
import org.tomighty.bus.messages.timer.TimerFinished;

/**
 * @author dobermai
 */
public class TimerFinishedSubscriber implements Subscriber<TimerFinished> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void receive(final TimerFinished timerEnd) {

        log.info("ended!");
    }
}
