package org.example.service;

import org.example.data.model.EmailApp;
import org.example.data.repository.EmailAppRepository;
import org.example.data.repository.UserRepository;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.request.SendEmailRequest;
import org.example.exception.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource(locations ="/test.properties")
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailAppRepository emailAppRepository;

    private RegisterRequest request;
    @AfterEach
    public void doThisAfter(){
        emailAppRepository.deleteAll();
        userRepository.deleteAll();
    }
    @BeforeEach
    public void setup(){
        request = new RegisterRequest();
        request.setName("opeoluwa");
        request.setAge("18");
        request.setPhoneNumber("07066221008");
        request.setPassword("1234");
        request.setDomainName("delighted");
    }
    @Test
    public void testThatWhenAUserRegisterWithAWrongPasswordFormatItThrowsException(){
        request.setPassword("opeoluwa123");
        assertThrows(InvalidDetailsFormat.class,()->userService.register(request));
    }
    @Test
    public void testThatARegisterUserCantRegisterTwice(){
        RegisterRequest request = new RegisterRequest();
        request.setName("opeoluwa");
        request.setAge("18");
        request.setPhoneNumber("08097568990");
        request.setPassword("opeoluwa123");
        request.setDomainName("opeoluaagnes");
        userService.register(request);
        assertThrows(DomainNameException.class,()->userService.register(request));
    }
    @Test
    public void testThatWhenUserRegisterWithInvalidNumberItThrowsException(){
        RegisterRequest request = new RegisterRequest();
        request.setName("opeoluwa");
        request.setAge("18");
        request.setPhoneNumber("12344");
        request.setPassword("opeoluwa123");
        request.setDomainName("opeoluaagnes");
        assertThrows(InvalidDetailsFormat.class,()->userService.register(request));
    }

    @Test
    public void testThatWhenUserLoginWithAWrongPasswordItThrowsAnException(){
        RegisterRequest request = new RegisterRequest();
        request.setName("opeoluwa");
        request.setAge("18");
        request.setPhoneNumber("07066221008");
        request.setPassword("opeoluwa123");
        request.setDomainName("delighted");
        userService.register(request);
        LoginRequest loginRequest = new LoginRequest("delighted@vision.com","opeoluwa23");
        assertThrows(InvalidLoginDetails.class,()->userService.login(loginRequest));
    }
    @Test
    public void testThatWhenRegisterUserSendEmailToUnRegisterUserThrowsAnException(){
        RegisterRequest request = new RegisterRequest();
        request.setName("opeoluwa");
        request.setAge("18");
        request.setPhoneNumber("07066221008");
        request.setPassword("opeoluwa123");
        request.setDomainName("delighted");
        userService.register(request);
        LoginRequest loginRequest = new LoginRequest("delighted@vision.com","opeoluwa123");
        userService.login(loginRequest);
        SendEmailRequest sendEmailRequest = new SendEmailRequest("delighted@vision.com",
                "I will like to apply for the secretary job offer",
                "I will like to apply for the secretary job offer,please consider my request",
                "opeoluwa@gmail.com","send");
        assertThrows(InvalidEmailException.class,()->userService.sendEmail(sendEmailRequest));
    }

    @Test
    public void testThatWhenAUserRecieveAEmailAndDeleteTheMailInInbox (){
        userService.register(request);
        LoginRequest loginRequest = new LoginRequest("delighted@vision.com","opeoluwa123");
        userService.login(loginRequest);
        SendEmailRequest sendEmailRequest = new SendEmailRequest("delighted@vision.com",
                "I will like to apply for the secretary job offer",
                "I will like to apply for the secretary job offer,please consider my request",
                "opeoluwa@gmail.com","send");
        userService.sendEmail(sendEmailRequest);
    }

}