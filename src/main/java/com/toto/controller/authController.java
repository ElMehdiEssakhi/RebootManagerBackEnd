package com.toto.controller;


import com.toto.entity.Technician;
import com.toto.entity.User;
import com.toto.service.impl.authService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class authController {
    @Autowired
    private authService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req){
        User userTryingToLog = authService.getUserByName(req.getUsername());
        if (userTryingToLog!=null && authService.checkPassword(userTryingToLog, req.getPassword())) {
            return ResponseEntity.ok(new LoginResponse(userTryingToLog.getRole(), userTryingToLog.getUsername()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }
}
