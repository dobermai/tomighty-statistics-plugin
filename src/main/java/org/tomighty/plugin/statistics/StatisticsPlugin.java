package org.tomighty.plugin.statistics;

import org.tomighty.bus.Bus;
import org.tomighty.bus.messages.time.TimerEnd;
import org.tomighty.bus.messages.time.TimerInterrupted;
import org.tomighty.ioc.Initializable;
import org.tomighty.ioc.Inject;
import org.tomighty.ioc.New;
import org.tomighty.log.Log;
import org.tomighty.plugin.Plugin;
import org.tomighty.plugin.statistics.subscriber.TimerEndSubscriber;
import org.tomighty.plugin.statistics.subscriber.TimerInterruptedSubscriber;

/**
 * @author dobermai
 */
public class StatisticsPlugin implements Plugin, Initializable {

    @Inject
    @New
    private Log log;

    @Inject
    private Bus bus;

    @Override
    public void initialize() {

        log.info("Initialized Statistics Plugin");

        bus.subscribe(new TimerInterruptedSubscriber(), TimerInterrupted.class);
        bus.subscribe(new TimerEndSubscriber(), TimerEnd.class);

    }
}
