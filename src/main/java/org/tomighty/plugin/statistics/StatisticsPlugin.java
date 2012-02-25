package org.tomighty.plugin.statistics;

import org.tomighty.ioc.Initializable;
import org.tomighty.ioc.Inject;
import org.tomighty.ioc.New;
import org.tomighty.log.Log;
import org.tomighty.plugin.Plugin;

/**
 * @author dobermai
 */
public class StatisticsPlugin implements Plugin, Initializable {

    @Inject
    @New
    private Log log;

    @Override
    public void initialize() {
        log.info("Initialized");
        System.out.println("Initialized");
    }
}
