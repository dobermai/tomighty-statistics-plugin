package org.tomighty.plugin.statistics;

import org.tomighty.bus.Subscriber;
import org.tomighty.bus.messages.time.TimerEnd;
import org.tomighty.ioc.Inject;

/**
 * @author dobermai
 */
public class TimerEndSubscriber implements Subscriber<TimerEnd>{

    @Override
    public void receive(final TimerEnd timerEnd) {

        System.out.println("ended!");
    }
}
