package com.app.Job_Portal;

import com.app.Job_Portal.entities.Admin;
import com.app.Job_Portal.entities.User;
import com.app.Job_Portal.repository.AdminRepository;
import com.app.Job_Portal.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

	// just a utility to populate the data
	@Bean
	CommandLineRunner run(AdminRepository adminRepo, UserRepository userRepo) {
		return args -> {
			System.out.println("this code is running");

			if(!adminRepo.findById((long)1).isPresent()) {
				Admin admin = new Admin((long)1, "shivam@gmail.com","shivam","hajare","shivam123");
				Admin persistedAdmin = adminRepo.save(admin);

				User user = new User();
				user.setAdmin(persistedAdmin);
				user.setEmail(admin.getEmail());
				user.setPassword(admin.getPassword());
				user.setRole("ROLE_ADMIN");
				userRepo.save(user);
			}


		};
	}

}
