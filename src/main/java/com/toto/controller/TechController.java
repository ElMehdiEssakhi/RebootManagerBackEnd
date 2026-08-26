package com.toto.controller;


import com.toto.entity.Technician;
import com.toto.service.impl.techService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("tech")

public class TechController {
    @Autowired
    private techService techService;
    @GetMapping("/getAll")
    public List<Technician> getTechs(){
        return techService.getTechs();
    }
    @GetMapping("/getBySite")
    public List<Technician> getTechsBySite(String site){
        return techService.getTechsBySite(site);
    }
    @PostMapping("/add")
    public void addTech(@RequestBody Technician technician){
        techService.addTech(technician);
    }
    @DeleteMapping("/delete")
    public void deleteTech(@RequestBody Technician technician){
        techService.deleteTech(technician);
    }
}
