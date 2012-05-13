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

package org.tomighty.plugin.statistics.core.writer.csv;

import au.com.bytecode.opencsv.CSVWriter;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tomighty.Phase;
import org.tomighty.plugin.statistics.core.config.Directories;
import org.tomighty.plugin.statistics.core.writer.StatisticsWriter;
import org.tomighty.plugin.statistics.core.writer.Status;

import javax.inject.Inject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVStatisticsWriter implements StatisticsWriter {

    private static final String FILENAME = "statistics.csv";

    final Logger log = LoggerFactory.getLogger(getClass());

    private final Directories directories;

    @Inject
    public CSVStatisticsWriter(final Directories directories) {
        this.directories = directories;
    }

    @Override
    public void append(final Phase phase, final Status status) {

        final File file = new File(directories.getStatsDirectory(), FILENAME);

        try {

            final CSVWriter writer = new CSVWriter(new FileWriter(file, true));
            writer.writeNext(new String[]{DateTime.now().toString(), phase.name(), status.name()});

            writer.close();
        } catch (IOException e) {
            log.error("Error while opening or creating {}", file.getAbsoluteFile(), e);
        }
    }
}
