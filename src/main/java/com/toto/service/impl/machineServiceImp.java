package com.toto.service.impl;

import com.toto.controller.RebByZonesResponse;
import com.toto.controller.alertsByZoneResponse;
import com.toto.entity.Machine;
import com.toto.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class machineServiceImp implements machineService {

    @Autowired
    MachineRepository machineRepository;

    @Override
    public List<Machine> getAllMachine(){return machineRepository.findAll();}

    @Override
    public Long getTotalMachine() {
        return machineRepository.count();
    }

    @Override
    public Long getTotalManualReboots() {
        return machineRepository.sumManualReboots();
    }

    @Override
    public Long getTotalAutoReboots() {
        return machineRepository.sumAutomaticReboots();
    }

    @Override
    public List<RebByZonesResponse> getTotalAutoRebootsZoned() {
        return machineRepository.getAutoRebootsByZone();
    }

    @Override
    public List<RebByZonesResponse> getTotalManRebootsZoned() {
        return machineRepository.geManualRebootsByZone();
    }

    @Override
    public List<alertsByZoneResponse> getTotalAlertsZone() {
        return machineRepository.getAlertsByZone();
    }

    @Override
    public void addMachine(Machine machine) {
        machineRepository.save(machine);
    }

    @Override
    public void deleteMachine(Machine machine) {
        machineRepository.delete(machine);
    }

    @Override
    public void increaseManualCount(Machine machine) {
        machine.setTechReboots(machine.getTechReboots() + 1);
        machineRepository.save(machine);
    }

    @Override
    public void increaseAutoCount(Machine machine) {
        machine.setTaskSchedulerReboots(machine.getTaskSchedulerReboots() + 1);
        machineRepository.save(machine);
    }

    @Override
    public void increaseAlertCount(Machine machine) {
        machine.setAlertCount(machine.getAlertCount() + 1);
        machineRepository.save(machine);
    }
}
