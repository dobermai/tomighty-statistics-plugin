package org.tomighty.plugin.statistics;

import com.google.inject.AbstractModule;
import org.tomighty.plugin.statistics.writer.StatisticsWriter;
import org.tomighty.plugin.statistics.writer.csv.CSVStatisticsWriter;

/**
 * @author dobermai
 */
public class Modules extends AbstractModule {
    @Override
    protected void configure() {
        bind(StatisticsWriter.class).to(CSVStatisticsWriter.class);
    }
}
