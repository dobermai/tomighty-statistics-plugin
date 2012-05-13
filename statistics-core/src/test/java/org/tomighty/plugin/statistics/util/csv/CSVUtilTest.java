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

package org.tomighty.plugin.statistics.util.csv;

import org.joda.time.DateTime;
import org.junit.Test;
import org.tomighty.Phase;
import org.tomighty.plugin.statistics.Pomodoro;
import org.tomighty.plugin.statistics.writer.Status;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author dobermai
 */
public class CSVUtilTest {

    @Test
    public void testConversionToPomodoroDates() {

        final DateTime now = DateTime.now();
        final List<String[]> csvRows = new ArrayList<String[]>() {{
            add(new String[]{now.toString(), Phase.POMODORO.name(), Status.STARTED.name()});
            add(new String[]{now.toString(), Phase.POMODORO.name(), Status.FINISHED.name()});
        }};

        List<Pomodoro> pomodoroList = CSVUtil.convertCSVListToPomodoros(csvRows);

        Pomodoro pomodoro = pomodoroList.get(0);

        assertEquals(now.toDate(), pomodoro.getStarted());
        assertEquals(now.toDate(), pomodoro.getEnded());
    }

    @Test
    public void testConversionToPomodoroWithFinishedPomodoro() {

        final DateTime now = DateTime.now();
        final List<String[]> csvRows = new ArrayList<String[]>() {{
            add(new String[]{now.toString(), Phase.POMODORO.name(), Status.STARTED.name()});
            add(new String[]{now.toString(), Phase.POMODORO.name(), Status.FINISHED.name()});
        }};

        List<Pomodoro> pomodoroList = CSVUtil.convertCSVListToPomodoros(csvRows);

        Pomodoro pomodoro = pomodoroList.get(0);

        assertEquals(Pomodoro.PomodoroStatus.FINISHED, pomodoro.getStatus());
    }

    @Test
    public void testConversionToPomodoroWithUnfinishedPomodoro() {

        final DateTime now = DateTime.now();
        final List<String[]> csvRows = new ArrayList<String[]>() {{
            add(new String[]{now.toString(), Phase.POMODORO.name(), Status.STARTED.name()});
            add(new String[]{now.toString(), Phase.POMODORO.name(), Status.INTERRUPTED.name()});
        }};

        List<Pomodoro> pomodoroList = CSVUtil.convertCSVListToPomodoros(csvRows);

        Pomodoro pomodoro = pomodoroList.get(0);

        assertEquals(Pomodoro.PomodoroStatus.UNFINISHED, pomodoro.getStatus());
    }

    @Test
    public void testGetPomodoroListWithStartedPomodoroFirst() {

        final List<String[]> csvRows = new ArrayList<String[]>() {{
            add(new String[]{DateTime.now().toString(), Phase.POMODORO.name(), Status.STARTED.name()});
            add(new String[]{DateTime.now().toString(), Phase.POMODORO.name(), Status.FINISHED.name()});
            add(new String[]{DateTime.now().toString(), Phase.POMODORO.name(), Status.STARTED.name()});
            add(new String[]{DateTime.now().toString(), Phase.POMODORO.name(), Status.INTERRUPTED.name()});
            add(new String[]{DateTime.now().toString(), Phase.POMODORO.name(), Status.STARTED.name()});
        }};

        List<Pomodoro> pomodoroList = CSVUtil.convertCSVListToPomodoros(csvRows);

        assertEquals(2, pomodoroList.size());
    }

    @Test
    public void testGetPomodoroListWithFinishedPomodoroFirst() {

        final List<String[]> csvRows = new ArrayList<String[]>() {{
            add(new String[]{DateTime.now().toString(), Phase.POMODORO.name(), Status.FINISHED.name()});
            add(new String[]{DateTime.now().toString(), Phase.POMODORO.name(), Status.STARTED.name()});
            add(new String[]{DateTime.now().toString(), Phase.POMODORO.name(), Status.FINISHED.name()});
            add(new String[]{DateTime.now().toString(), Phase.POMODORO.name(), Status.STARTED.name()});
            add(new String[]{DateTime.now().toString(), Phase.POMODORO.name(), Status.FINISHED.name()});
        }};

        List<Pomodoro> pomodoroList = CSVUtil.convertCSVListToPomodoros(csvRows);

        assertEquals(2, pomodoroList.size());
    }

    @Test
    public void testGetPomodoroListWithBreaks() {

        final List<String[]> csvRows = new ArrayList<String[]>() {{
            add(new String[]{DateTime.now().toString(), Phase.BREAK.name(), Status.STARTED.name()});
            add(new String[]{DateTime.now().toString(), Phase.BREAK.name(), Status.FINISHED.name()});
            add(new String[]{DateTime.now().toString(), Phase.POMODORO.name(), Status.STARTED.name()});
            add(new String[]{DateTime.now().toString(), Phase.POMODORO.name(), Status.FINISHED.name()});
        }};

        List<Pomodoro> pomodoroList = CSVUtil.convertCSVListToPomodoros(csvRows);

        assertEquals(1, pomodoroList.size());
    }


}

