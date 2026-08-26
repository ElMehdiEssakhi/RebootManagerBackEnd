package com.toto.service.impl;

import com.toto.controller.RebByZonesResponse;
import com.toto.controller.alertsByZoneResponse;
import com.toto.entity.Machine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface machineService {
    List<Machine> getAllMachine();
    Long getTotalMachine();
    Long getTotalManualReboots();
    Long getTotalAutoReboots();
    List<RebByZonesResponse>  getTotalAutoRebootsZoned();
    List<RebByZonesResponse>  getTotalManRebootsZoned();
    List<alertsByZoneResponse>  getTotalAlertsZone();
    void addMachine(Machine machine);
    void deleteMachine(Machine machine);
    void increaseManualCount(Machine machine);
    void increaseAutoCount(Machine machine);
    void increaseAlertCount(Machine machine);
}
