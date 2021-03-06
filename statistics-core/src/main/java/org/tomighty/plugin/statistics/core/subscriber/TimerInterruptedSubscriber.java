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

package org.tomighty.plugin.statistics.core.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tomighty.bus.Subscriber;
import org.tomighty.bus.messages.timer.TimerInterrupted;
import org.tomighty.plugin.statistics.core.writer.StatisticsWriter;
import org.tomighty.plugin.statistics.core.writer.Status;

import javax.inject.Inject;


public class TimerInterruptedSubscriber implements Subscriber<TimerInterrupted> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private StatisticsWriter writer;

    @Inject
    public TimerInterruptedSubscriber(StatisticsWriter writer) {
        this.writer = writer;
    }

    @Override
    public void receive(final TimerInterrupted timerInterrupted) {

        writer.append(timerInterrupted.getPhase(), Status.INTERRUPTED);
        log.debug("Received Interrupted Event for phase {}", timerInterrupted.getPhase());
    }
}
