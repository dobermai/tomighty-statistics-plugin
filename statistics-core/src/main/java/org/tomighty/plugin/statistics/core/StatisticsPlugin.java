/*
 * Copyright (c) 2012 Dominik Obermaier.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package org.tomighty.plugin.statistics.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tomighty.bus.Bus;
import org.tomighty.bus.messages.timer.TimerFinished;
import org.tomighty.bus.messages.timer.TimerInterrupted;
import org.tomighty.bus.messages.timer.TimerStarted;
import org.tomighty.plugin.DefaultPlugin;
import org.tomighty.plugin.statistics.core.subscriber.TimerFinishedSubscriber;
import org.tomighty.plugin.statistics.core.subscriber.TimerInterruptedSubscriber;
import org.tomighty.plugin.statistics.core.subscriber.TimerStartedSubscriber;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticsPlugin extends DefaultPlugin {


    private final Bus bus;
    private final TimerInterruptedSubscriber subscriber;
    private final TimerFinishedSubscriber finishedSubscriber;
    private final TimerStartedSubscriber startedSubscriber;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Inject
    public StatisticsPlugin(final Bus bus, final TimerInterruptedSubscriber interruptedSubscriber,
                            final TimerFinishedSubscriber finishedSubscriber,
                            final TimerStartedSubscriber startedSubscriber
    ) {
        this.bus = bus;

        this.subscriber = interruptedSubscriber;
        this.finishedSubscriber = finishedSubscriber;
        this.startedSubscriber = startedSubscriber;
    }

    @PostConstruct
    public void initialize() {

        bus.subscribe(subscriber, TimerInterrupted.class);
        bus.subscribe(finishedSubscriber, TimerFinished.class);
        bus.subscribe(startedSubscriber, TimerStarted.class);


        log.info("Statistics Plugin initialized");
    }

    @Override
    public MenuItem getMenuItem() {
        MenuItem statistics = new MenuItem("Statistics");
        statistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                log.info("Statistics clicked");
            }
        });
        return statistics;
    }
}
