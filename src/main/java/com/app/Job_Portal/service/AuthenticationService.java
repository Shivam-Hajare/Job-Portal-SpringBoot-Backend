package com.app.Job_Portal.service;



import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.Job_Portal.dto.LoginResponseDTO;
import com.app.Job_Portal.dto.RegistrationDTO;
import com.app.Job_Portal.entities.ApplicationUser;
import com.app.Job_Portal.entities.JobSeeker;
import com.app.Job_Portal.entities.Recruiter;
import com.app.Job_Portal.entities.Role;
import com.app.Job_Portal.exceptions.ResourceNotFoundException;
import com.app.Job_Portal.repository.JobSeekerRepository;
import com.app.Job_Portal.repository.RecruiterRepository;
import com.app.Job_Portal.repository.RoleRepository;
import com.app.Job_Portal.repository.UserRepository;

@Service
@Transactional
public class AuthenticationService {

	@Autowired
	private ModelMapper mapper;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private RecruiterRepository recruiterRepository;
    
    public ApplicationUser registerUser(RegistrationDTO body, String role) {
    	String username=body.getUsername();
    	String password=body.getPassword();
    	
    	String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority(role)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        ApplicationUser user = new ApplicationUser(0, username, encodedPassword, authorities);
        user = userRepository.save(user);
//
//        // Associate the user with a specific role (JobSeeker or Recruiter)
        if ("jobseeker".equals(role)) {
            JobSeeker jobSeeker = new JobSeeker();
            jobSeeker.setFirstName(body.getFirstName());
            jobSeeker.setLastName(body.getLastName());
            jobSeeker.setEmail(user.getUsername());
            jobSeeker.setUser(user);
            jobSeekerRepository.save(jobSeeker);
        } else if ("recruiter".equals(role)) {
            Recruiter recruiter = new Recruiter();
            recruiter.setFirstName(body.getFirstName());
            recruiter.setLastName(body.getLastName());
            recruiter.setEmail(user.getUsername());
            recruiter.setUser(user);
            recruiterRepository.save(recruiter);
        }

        return user;
	}
    
    public ApplicationUser registerUser(String username, String password, String role) {

    	 String encodedPassword = passwordEncoder.encode(password);
         Role userRole = roleRepository.findByAuthority(role)
                 .orElseThrow(() -> new RuntimeException("Role not found"));

         Set<Role> authorities = new HashSet<>();
         authorities.add(userRole);

         ApplicationUser user = new ApplicationUser(0, username, encodedPassword, authorities);
         user = userRepository.save(user);
//
//         // Associate the user with a specific role (JobSeeker or Recruiter)
         if ("jobseeker".equals(role)) {
             JobSeeker jobSeeker = new JobSeeker();
             jobSeeker.setEmail(user.getUsername());
             jobSeeker.setUser(user);
             jobSeekerRepository.save(jobSeeker);
         } else if ("recruiter".equals(role)) {
             Recruiter recruiter = new Recruiter();
             recruiter.setEmail(user.getUsername());
             recruiterRepository.save(recruiter);
         }

         return user;
    }


    public LoginResponseDTO loginUser(String username, String password, String role) {

        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            System.out.println(token);
            
             ApplicationUser user=userRepository.findByUsername(username).get();
             int userId=user.getUserId();
             Long jobSeekerId=null;
             Long recruiterId=null;
             
             if(role.equals("jobseeker"))
              jobSeekerId=user.getJobSeeker().getJobSeekerId();
             
             else if(role.equals("recruiter"))
              recruiterId=user.getRecruiter().getRecruiterId();
             
            return new LoginResponseDTO(token,userId,jobSeekerId,recruiterId, role);

        } catch (AuthenticationException e) {
            throw  new ResourceNotFoundException("Wrong credential!!!!"+e.getMessage());
        }
    }

    public LoginResponseDTO loginJobSeeker(String username, String password) {
        return loginUser(username, password, "jobseeker");
    }

    public LoginResponseDTO loginRecruiter(String username, String password) {
        return loginUser(username, password, "recruiter");
    }


	
}
