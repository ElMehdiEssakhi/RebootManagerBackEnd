package com.toto.controller;


import com.toto.entity.Machine;
import com.toto.repository.MachineRepository;
import com.toto.service.impl.machineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("machine")
public class MachineController {
    @Autowired
    private machineService machineService;

    @GetMapping("/getAll")
    public List<Machine> getAllMachines() {
        return machineService.getAllMachine(); }
    @PostMapping("/add")
    public void addMachine(@RequestBody Machine machine) {
        machineService.addMachine(machine);
    }
    @DeleteMapping("/delete")
    public void deleteMachine(@RequestBody Machine machine) {
        machineService.deleteMachine(machine);
    }

    @GetMapping("/getSumAuto")
    public Long sumAutoReboots() {
        return machineService.getTotalAutoReboots(); }

    @GetMapping("/getSumManual")
    public Long sumManualReboots() {
        return machineService.getTotalManualReboots();
    }

    @GetMapping("/getSumAutoByZones")
    public List<RebByZonesResponse> getTotalAutoRebootsByZones() {
        return machineService.getTotalAutoRebootsZoned();
    }

    @GetMapping("/getSumManualByZones")
    public List<RebByZonesResponse> getTotalManualRebootsByZones() {
        return machineService.getTotalManRebootsZoned();
    }

    @GetMapping("/getSumAlertsByZones")
    public List<alertsByZoneResponse> getTotalAlertsByZones() {return  machineService.getTotalAlertsZone();}

}
