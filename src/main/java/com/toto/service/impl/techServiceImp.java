package com.toto.service.impl;

import com.toto.entity.Technician;
import com.toto.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class techServiceImp implements techService {

    @Autowired
    private TechnicianRepository technicianRepository;

    @Override
    public List<Technician> getTechs() {
        return technicianRepository.findAll();
    }

    @Override
    public List<Technician> getTechsBySite(String site) {
        return technicianRepository.findAllBySite(site);
    }

    @Override
    public void addTech(Technician technician) {
        technicianRepository.save(technician);
    }

    @Override
    public void deleteTech(Technician technician) {
        technicianRepository.delete(technician);
    }

    @Override
    public void increaseRebootCount(Technician technician) {
        technician.setRebootCount(technician.getRebootCount() + 1);
        technicianRepository.save(technician);
    }

}
