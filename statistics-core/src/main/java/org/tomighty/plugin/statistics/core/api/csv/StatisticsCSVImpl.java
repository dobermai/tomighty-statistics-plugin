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

package org.tomighty.plugin.statistics.core.api.csv;

import au.com.bytecode.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tomighty.plugin.statistics.core.Pomodoro;
import org.tomighty.plugin.statistics.core.Pomodoro;
import org.tomighty.plugin.statistics.core.api.Statistics;
import org.tomighty.plugin.statistics.core.config.Directories;
import org.tomighty.plugin.statistics.core.csv.CSVUtil;

import javax.inject.Inject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dobermai
 */
public class StatisticsCSVImpl implements Statistics {

    private static final String FILENAME = "statistics.csv";

    private final Directories directories;

    private static final Logger log = LoggerFactory.getLogger(StatisticsCSVImpl.class);

    @Inject
    public StatisticsCSVImpl(final Directories directories) {


        this.directories = directories;
    }

    public List<Pomodoro> getAllPomodoros() {

        File statisticsCSVFile = new File(directories.getStatsDirectory(), FILENAME);

        try {
            final CSVReader reader = new CSVReader(new FileReader(statisticsCSVFile));
            final List<String[]> csvEntries = reader.readAll();

            return CSVUtil.convertCSVListToPomodoros(csvEntries);
        } catch (FileNotFoundException e) {
            log.error("File {} not found", statisticsCSVFile.getAbsolutePath(), e);

        } catch (IOException e) {
            log.error("Error while reading file {}", statisticsCSVFile.getAbsolutePath(), e);
        }
        log.warn("Error while reading {}, no Pomodors available", statisticsCSVFile.getAbsolutePath());
        return new ArrayList<Pomodoro>();
    }

    @Override
    public void deleteAllPomodoros() {
        final File statisticsFile = new File(directories.getStatsDirectory(), FILENAME);
        if (statisticsFile.delete()) {

            log.info("Deleted {}", statisticsFile.getAbsolutePath());
        } else {
            log.error("Could not delete {}", statisticsFile.getAbsolutePath());
        }
    }
}
