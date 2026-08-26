package com.toto.service.impl;

import com.toto.entity.Technician;
import com.toto.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface authService {
    User getUserByName(String name);
    Boolean checkPassword(User user, String password);
}
