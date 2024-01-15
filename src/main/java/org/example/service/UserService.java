package org.example.service;

import org.example.dto.request.LogOutRequest;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.request.SendEmailRequest;

public interface UserService {
    String register(RegisterRequest request);

    void login(LoginRequest loginRequest);

    void sendEmail(SendEmailRequest sendEmailRequest);

    void logOut(LogOutRequest logOutRequest);
}
