package org.tomighty.plugin.statistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tomighty.bus.Bus;
import org.tomighty.bus.messages.timer.TimerInterrupted;
import org.tomighty.plugin.Plugin;
import org.tomighty.plugin.statistics.subscriber.TimerFinishedSubscriber;
import org.tomighty.plugin.statistics.subscriber.TimerInterruptedSubscriber;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.net.URISyntaxException;

/**
 * @author dobermai
 */
public class StatisticsPlugin implements Plugin {


    private final Bus bus;
    private final TimerInterruptedSubscriber subscriber;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Inject
    public StatisticsPlugin(final Bus bus, TimerInterruptedSubscriber subscriber) throws URISyntaxException, ClassNotFoundException {
        this.bus = bus;

        this.subscriber = subscriber;
    }

    @PostConstruct
    public void initialize() {

        bus.subscribe(subscriber, TimerInterrupted.class);
        bus.subscribe(new TimerFinishedSubscriber(), org.tomighty.bus.messages.timer.TimerFinished.class);


        log.info("Statistics Plugin Information");
    }
}
