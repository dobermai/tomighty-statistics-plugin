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

package org.tomighty.plugin.statistics.api.csv;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.tomighty.plugin.statistics.config.Directories;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author dobermai
 */
public class StatisticsCSVImplTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void testDeleteAllPomodoros() throws Exception {

        final Directories directoriesMock = mock(Directories.class);
        final File tempFolder = temporaryFolder.newFolder();
        final File statisticsFile = new File(tempFolder, "statistics.csv");
        FileUtils.touch(statisticsFile);

        when(directoriesMock.getStatsDirectory()).thenReturn(tempFolder);

        final StatisticsCSVImpl statisticsCSV = new StatisticsCSVImpl(directoriesMock);

        statisticsCSV.deleteAllPomodoros();

        assertTrue(tempFolder.exists());
        assertFalse(statisticsFile.exists());
    }
}
