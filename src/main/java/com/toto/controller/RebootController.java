package com.toto.controller;

import com.toto.entity.RebootLog;

import com.toto.service.impl.rebootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/reboot")
public class RebootController {
    @Autowired
    private rebootService rebootService;

    @GetMapping("/All")
    public List<RebootLog> getAllReboots() {
        return rebootService.getAllReboots();
    }

    @GetMapping("/pending")
    public List<RebootLog> getPendingRebootsBySite(@RequestParam String site) {return rebootService.getPendingRebootsBySite(site);}

    @PatchMapping("/check")
    public void checkReboot(@RequestBody CheckRebootRequest request) {rebootService.checkRebootAsManual(request.getRebootedBy(), request.getRebootId()); }

    @GetMapping("/byDate")
    public List<RebootLog> getRebootedByDate(@RequestParam String date) {
        return rebootService.getRebootsByDate(date);
    }
    @GetMapping("/alerts")
    public List<RebootLog> getAlerts() {
        return rebootService.getAlertStatus();
    }
}
