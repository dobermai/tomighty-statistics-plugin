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

package org.tomighty.plugin.statistics.core.csv;

import org.joda.time.DateTime;
import org.tomighty.Phase;
import org.tomighty.plugin.statistics.core.Pomodoro;
import org.tomighty.plugin.statistics.core.Pomodoro;
import org.tomighty.plugin.statistics.core.writer.Status;

import java.util.Date;
import java.util.List;

import static com.mycila.inject.internal.guava.collect.Lists.newArrayList;

/**
 * @author dobermai
 */
public class CSVUtil {


    public static List<Pomodoro> convertCSVListToPomodoros(final List<String[]> csvList) {

        List<Pomodoro> pomodoroList = newArrayList();

        Date start = null;
        Date end = null;


        for (String[] csvRow : csvList) {
            if (Phase.valueOf(csvRow[1]) == Phase.POMODORO) {

                final String statusString = csvRow[2];
                final Status status = Status.valueOf(statusString);

                final String time = csvRow[0];
                if (status == Status.STARTED) {

                    start = DateTime.parse(time).toDate();


                } else if (status == Status.FINISHED) {
                    end = DateTime.parse(time).toDate();
                    if (start != null) {

                        final Pomodoro pomodoro = new Pomodoro(start, end, Pomodoro.PomodoroStatus.FINISHED);
                        pomodoroList.add(pomodoro);
                        start = null;
                    }
                } else if (status == Status.INTERRUPTED) {
                    end = DateTime.parse(time).toDate();
                    if (start != null) {
                        final Pomodoro pomodoro = new Pomodoro(start, end, Pomodoro.PomodoroStatus.UNFINISHED);
                        pomodoroList.add(pomodoro);
                        start = null;
                    }
                }
            }
        }


        return pomodoroList;
    }
}
