package com.toto.service.impl;


import com.toto.entity.Machine;
import com.toto.entity.RebootLog;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface rebootService {
    List<RebootLog> getAllReboots();
    List<RebootLog> getPendingRebootsBySite(String site);
    void checkRebootAsManual(String userId, Long machineId);
    List<RebootLog> getRebootsByDate(String date);
    List<RebootLog> getAlertStatus();
}
