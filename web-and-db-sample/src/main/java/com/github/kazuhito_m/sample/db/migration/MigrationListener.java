package com.github.kazuhito_m.sample.db.migration;


import com.github.kazuhito_m.sample.config.Settings;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.flywaydb.core.api.MigrationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
// import javax.servlet.annotation.WebServletContextListener;


/* @WebServletContextListener FIXME なぜかこのアノテーションが使えない…これ使えばweb.xmlの一文いらんのに…。 */
public class MigrationListener implements ServletContextListener {

    private final static Logger log = LoggerFactory.getLogger(MigrationListener.class);
    private final static Flyway flyway;

    static {
        flyway = getFlyway();
    }

    static Flyway getFlyway() {

        Settings s = Settings.get();

        Flyway flyway = new Flyway();
        flyway.setDataSource(s.getJdbcUrl(), s.getJdbcUser(), s.getJdbcPassword());
        flyway.setLocations(s.getFlywaySqlLocation());
        flyway.setOutOfOrder(true);
        flyway.repair();

        return flyway;

    }

    public static void migrate() {
        try {

            Object version = (flyway.info().current() == null) ?
                    "first applied!!" : flyway.info().current().getVersion();
            log.info("starting migrate...:" + version);

            int ret = flyway.migrate();

            log.info("migration done.:" + ret);

            dumpMigrationStatus();

        } catch (FlywayException e) {
            log.error("migration faild.", e);
        }
    }


    private static void dumpMigrationStatus() {
        MigrationInfo[] infos = flyway.info().all();
        for (MigrationInfo info : infos) {
            log.debug(String.format("%s\t%-20s\t%s", info.getVersion(), info.getState(), info.getDescription()));
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        migrate();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
