package com.toto.service.impl;

import com.toto.entity.Technician;
import com.toto.entity.User;
import com.toto.repository.TechnicianRepository;
import com.toto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class authServiceImp implements authService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public Boolean checkPassword(User user, String password) {
        return (user.getPassword().equals(password));
    }

}
