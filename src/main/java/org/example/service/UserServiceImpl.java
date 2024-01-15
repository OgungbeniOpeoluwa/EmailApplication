package org.example.service;

import org.example.data.model.User;
import org.example.data.repository.UserRepository;
import org.example.dto.request.LogOutRequest;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.request.SendEmailRequest;
import org.example.exception.InvalidDetailsFormat;
import org.example.exception.InvalidLoginDetails;
import org.example.exception.UserExistException;
import org.example.util.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailAppService emailAppService;


    @Override
    public String register(RegisterRequest request) {
        User user = new User();
        if(!Verification.verifyPhoneNumber(request.getPhoneNumber()))throw new InvalidDetailsFormat("Invalid phone Number");
        if(!Verification.verifyPassword(request.getPassword()))throw new InvalidDetailsFormat("Weak password");
        user.setName(request.getName());
        user.setAge(request.getAge());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        userRepository.save(user);
        return emailAppService.createAccount(request.getDomainName(),user.getId());
    }

    @Override
    public void login(LoginRequest loginRequest) {
        Long userId = emailAppService.login(loginRequest.getDomainName());
        verifyPassword(userId, loginRequest);

    }

    @Override
    public void sendEmail(SendEmailRequest sendEmailRequest) {
        emailAppService.sendEmail(sendEmailRequest);
    }

    @Override
    public void logOut(LogOutRequest logOutRequest) {
       emailAppService.logOut(logOutRequest.getDomainName()); 
    }

    private void verifyPassword(Long id, LoginRequest loginRequest){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) throw new UserExistException("User doesn't exist");
        if(!user.get().getPassword().equals(loginRequest.getPassword())) {
            emailAppService.logOut(loginRequest.getDomainName());
            throw new InvalidLoginDetails("Invalid login details");
        }
    }
}
