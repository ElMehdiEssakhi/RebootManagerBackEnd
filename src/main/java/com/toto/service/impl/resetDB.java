package com.toto.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class resetDB {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Scheduled(cron = "0 0 0 1 * ?") // Midnight on the 1st of every month
    public void resetTables() {
        jdbcTemplate.execute("TRUNCATE TABLE REBOOTLOGS");
        jdbcTemplate.execute("UPDATE MACHINES" +
                                    "SET taskSchedulerReboots=0,techReboots=0");
        jdbcTemplate.execute("UPDATE TECHNICIANS" +
                "SET rebootCount=0");
        System.out.println("Table have been reset.");
    }
}
