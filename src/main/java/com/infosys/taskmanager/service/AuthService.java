package com.infosys.taskmanager.service;

import com.infosys.taskmanager.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}