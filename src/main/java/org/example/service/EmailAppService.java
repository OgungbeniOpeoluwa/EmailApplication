package org.example.service;

import org.example.data.model.EmailApp;
import org.example.dto.request.SendEmailRequest;

public interface EmailAppService {
    void logOut(String domainName);

    String createAccount(String domainName, Long id);

    EmailApp findUserDomainName(String domainName);

    void login(String domainName);

    void sendEmail(SendEmailRequest sendEmailRequest);
}
