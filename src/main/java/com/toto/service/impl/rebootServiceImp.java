package com.toto.service.impl;

import com.toto.entity.Machine;
import com.toto.entity.RebootLog;
import com.toto.entity.Technician;
import com.toto.repository.MachineRepository;
import com.toto.repository.RebootLogRepository;
import com.toto.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class rebootServiceImp implements rebootService {

    @Autowired
    private RebootLogRepository rebootRepository;
    @Autowired
    private TechnicianRepository techRepository;
    @Autowired
    private machineService machineService;
    @Autowired
    private techService techService;

    @Override
    public List<RebootLog> getAllReboots() {
        return rebootRepository.findAll();
    }

    @Override
    public List<RebootLog> getPendingRebootsBySite(String site) {
        return rebootRepository.findRebootLogsBySiteAndStatusIn(site, List.of("pending","alert"));
    }

    @Override
    public List<RebootLog> getRebootsByDate(String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        // Create a range for the full day
        LocalDateTime startOfDay = parsedDate.atStartOfDay(); // 2025-04-28T00:00:00
        LocalDateTime endOfDay = parsedDate.atTime(23, 59, 59); // 2025-04-28T23:59:59
        return rebootRepository.findRebootLogsByRebootPostponedAtBetween(startOfDay,endOfDay);
    }

    @Override
    public List<RebootLog> getAlertStatus() {
        return rebootRepository.findByStatus("alert");
    }


    @Override
    public void checkRebootAsManual(String rebootedBy, Long rebId) {
        RebootLog pendingReb=rebootRepository.getRebootLogById(rebId);
        Technician tech=techRepository.findByName(rebootedBy);
        Machine machine= pendingReb.getMachine();
        LocalDateTime manualRebootTime=LocalDateTime.now();
        pendingReb.setStatus("manual");
        pendingReb.setRebootedBy(tech);
        pendingReb.setRebootedAt(manualRebootTime);
        techService.increaseRebootCount(tech);
        machineService.increaseManualCount(machine);
        rebootRepository.save(pendingReb);
    }



}
