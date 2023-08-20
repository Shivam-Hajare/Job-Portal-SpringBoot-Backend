package com.app.Job_Portal.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Job_Portal.dto.LoginResponseDTO;
import com.app.Job_Portal.dto.RegistrationDTO;
import com.app.Job_Portal.entities.ApplicationUser;
import com.app.Job_Portal.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

//    @PostMapping("/register/jobseeker")
//    public ApplicationUser registerJobSeeker(@RequestBody RegistrationDTO body) {
//        return authenticationService.registerUser(body.getUsername(), body.getPassword(), "jobseeker");
//    }
    @PostMapping("/register/jobseeker")
    public ApplicationUser registerJobSeeker(@RequestBody RegistrationDTO body) {
        return authenticationService.registerUser(body, "jobseeker");
    }
    
//    @PostMapping("/register/recruiter")
//    public ApplicationUser registerRecruiter(@RequestBody RegistrationDTO body) {
//        return authenticationService.registerUser(body.getUsername(), body.getPassword(), "recruiter");
//    }
    @PostMapping("/register/recruiter")
    public ApplicationUser registerRecruiter(@RequestBody RegistrationDTO body) {
        return authenticationService.registerUser(body, "recruiter");
    }
    
    @PostMapping("/login/jobseeker")
    public LoginResponseDTO loginJobSeeker(@RequestBody RegistrationDTO body) {
        return authenticationService.loginJobSeeker(body.getUsername(), body.getPassword());
    }
    
    @PostMapping("/login/recruiter")
    public LoginResponseDTO loginRecruiter(@RequestBody RegistrationDTO body) {
        return authenticationService.loginRecruiter(body.getUsername(), body.getPassword());
    }
}
