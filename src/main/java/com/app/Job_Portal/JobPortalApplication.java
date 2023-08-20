package com.app.Job_Portal;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.Job_Portal.entities.ApplicationUser;
import com.app.Job_Portal.entities.Role;
import com.app.Job_Portal.repository.RoleRepository;
import com.app.Job_Portal.repository.UserRepository;

@SpringBootApplication
public class JobPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPortalApplication.class, args);
	}
	@Bean
	public ModelMapper mapper()
	{
		ModelMapper mapper=new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper;
	}
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
	    return args -> {
	        if (roleRepository.findByAuthority("ADMIN").isPresent()) return;

	        Role adminRole = roleRepository.save(new Role("ADMIN"));
	        Role userRole = roleRepository.save(new Role("USER"));
	        Role jobseekerRole = roleRepository.save(new Role("JOBSEEKER"));
	        Role recruiterRole = roleRepository.save(new Role("RECRUITER"));

	        Set<Role> adminRoles = new HashSet<>();
	        adminRoles.add(adminRole);

	        ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncoder.encode("password"), adminRoles);
	        userRepository.save(admin);

	        Set<Role> userRoles = new HashSet<>();
	        userRoles.add(userRole);

	        ApplicationUser user = new ApplicationUser(2, "user", passwordEncoder.encode("password"), userRoles);
	        userRepository.save(user);

	        Set<Role> jobseekerRoles = new HashSet<>();
	        jobseekerRoles.add(jobseekerRole);

	        ApplicationUser jobseeker = new ApplicationUser(3, "jobseeker", passwordEncoder.encode("password"), jobseekerRoles);
	        userRepository.save(jobseeker);

	        Set<Role> recruiterRoles = new HashSet<>();
	        recruiterRoles.add(recruiterRole);

	        ApplicationUser recruiter = new ApplicationUser(4, "recruiter", passwordEncoder.encode("password"), recruiterRoles);
	        userRepository.save(recruiter);
	    };
	}

}
