package org.tomighty.plugin.statistics.writer.csv;

import au.com.bytecode.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tomighty.plugin.statistics.writer.StatisticsWriter;
import org.tomighty.plugin.statistics.writer.Status;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author dobermai
 */
public class CSVStatisticsWriter implements StatisticsWriter {

    private static final String FILENAME = "statistics.csv";
    final Logger log = LoggerFactory.getLogger(getClass());


    @Override
    public void append(final Status status) {
        try {

            final CSVWriter writer = new CSVWriter(new FileWriter(FILENAME, true));
            writer.writeNext(new String[]{status.name()});

            writer.close();
        } catch (IOException e) {
            log.error("Error while opening or creating {}", FILENAME, e);
        }


    }
}
