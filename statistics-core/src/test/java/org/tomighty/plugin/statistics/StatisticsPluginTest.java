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

import org.junit.Test;
import org.tomighty.bus.Bus;
import org.tomighty.bus.Subscriber;
import org.tomighty.bus.messages.timer.TimerFinished;
import org.tomighty.bus.messages.timer.TimerInterrupted;
import org.tomighty.bus.messages.timer.TimerStarted;
import org.tomighty.plugin.statistics.core.StatisticsPlugin;

import static org.mockito.Mockito.*;

public class StatisticsPluginTest {

    @Test
    public void testBusSubscriptions() throws Exception {

        final Bus busMock = mock(Bus.class);


        StatisticsPlugin plugin = new StatisticsPlugin(busMock, null, null, null);
        plugin.initialize();

        //Test that we subscribe to all events
        verify(busMock).subscribe(any(Subscriber.class), eq(TimerInterrupted.class));
        verify(busMock).subscribe(any(Subscriber.class), eq(TimerFinished.class));
        verify(busMock).subscribe(any(Subscriber.class), eq(TimerStarted.class));
    }
}
