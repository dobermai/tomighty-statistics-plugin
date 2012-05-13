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

import java.util.Date;

/**
 * @author dobermai
 */
public class Pomodoro {

    public enum PomodoroStatus {

        FINISHED, UNFINISHED;
    }

    private Date started;

    private Date ended;

    private PomodoroStatus status;


    public Pomodoro(final Date started, final Date ended, final PomodoroStatus status) {
        this.started = started;
        this.ended = ended;
        this.status = status;
    }

    public Date getStarted() {
        return started;
    }

    public Date getEnded() {
        return ended;
    }

    public PomodoroStatus getStatus() {
        return status;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Pomodoro pomodoro = (Pomodoro) o;

        if (ended != null ? !ended.equals(pomodoro.ended) : pomodoro.ended != null) return false;
        if (started != null ? !started.equals(pomodoro.started) : pomodoro.started != null) return false;
        if (status != pomodoro.status) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = started != null ? started.hashCode() : 0;
        result = 31 * result + (ended != null ? ended.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pomodoro{" +
                "started=" + started +
                ", ended=" + ended +
                ", status=" + status +
                '}';
    }
}
