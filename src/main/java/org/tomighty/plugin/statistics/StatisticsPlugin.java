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

package org.tomighty.plugin.statistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tomighty.bus.Bus;
import org.tomighty.bus.messages.timer.TimerFinished;
import org.tomighty.bus.messages.timer.TimerInterrupted;
import org.tomighty.plugin.Plugin;
import org.tomighty.plugin.statistics.subscriber.TimerFinishedSubscriber;
import org.tomighty.plugin.statistics.subscriber.TimerInterruptedSubscriber;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

public class StatisticsPlugin implements Plugin {


    private final Bus bus;
    private final TimerInterruptedSubscriber subscriber;
    private final TimerFinishedSubscriber finishedSubscriber;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Inject
    public StatisticsPlugin(final Bus bus, final TimerInterruptedSubscriber interruptedSubscriber,
                            final TimerFinishedSubscriber finishedSubscriber) {
        this.bus = bus;

        this.subscriber = interruptedSubscriber;
        this.finishedSubscriber = finishedSubscriber;
    }

    @PostConstruct
    public void initialize() {

        bus.subscribe(subscriber, TimerInterrupted.class);
        bus.subscribe(finishedSubscriber, TimerFinished.class);


        log.info("Statistics Plugin initialized");
    }
}
