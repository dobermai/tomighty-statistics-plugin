package org.tomighty.plugin.statistics.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tomighty.bus.Subscriber;
import org.tomighty.bus.messages.timer.TimerInterrupted;
import org.tomighty.plugin.statistics.writer.StatisticsWriter;

import javax.inject.Inject;

/**
 * @author dobermai
 */
public class TimerInterruptedSubscriber implements Subscriber<TimerInterrupted> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Inject
    private StatisticsWriter writer;

    @Override
    public void receive(final TimerInterrupted timerInterrupted) {

        log.info("Received");
    }
}
