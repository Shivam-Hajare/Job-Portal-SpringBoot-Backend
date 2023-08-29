package com.app.Job_Portal.service;

import com.app.Job_Portal.dto.JobSeekerRequestDto;
import com.app.Job_Portal.dto.RecruiterSignUpDto;
import com.app.Job_Portal.entities.Admin;
import com.app.Job_Portal.entities.JobSeeker;
import com.app.Job_Portal.entities.Recruiter;
import com.app.Job_Portal.entities.User;
import com.app.Job_Portal.exceptions.ValidationRule;
import com.app.Job_Portal.repository.AdminRepository;
import com.app.Job_Portal.repository.JobSeekerRepository;
import com.app.Job_Portal.repository.RecruiterRepository;
import com.app.Job_Portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private RecruiterRepository recruiterRepo;

    @Autowired
    private JobSeekerRepository jobSeekerRepo;

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private UserRepository userRepo;

    public String registrationOfRecruiter(RecruiterSignUpDto recruiterDto) {
        //validation of Email via Global exception
        //validation of PhoneNumber via Custom exception
        ValidationRule.validationOfPhoneNumber(recruiterDto.getPhoneNo());


        Recruiter newRecruiter = new Recruiter();

        newRecruiter.setEmail(recruiterDto.getEmail());
        newRecruiter.setFirstName(recruiterDto.getFirstName());
        newRecruiter.setLastName(recruiterDto.getLastName());
        newRecruiter.setRecruiterBio(recruiterDto.getRecruiterBio());
        newRecruiter.setCompanyName(recruiterDto.getCompanyName());
        newRecruiter.setPhoneNo(recruiterDto.getPhoneNo());

        Optional<Admin> adminHolder = adminRepo.findById((long) 1);

        Admin admin = new Admin();
        if(adminHolder.isPresent()) {
            admin = adminHolder.get();
        }

        newRecruiter.setAdmin(admin);
        recruiterRepo.save(newRecruiter);

        User newUser = new User();

        newUser.setEmail(recruiterDto.getEmail());
        String cryptPassword=new BCryptPasswordEncoder().encode(recruiterDto.getPassword());
        newUser.setPassword(cryptPassword);
        newUser.setRecruiter(newRecruiter);
        newUser.setRole(recruiterDto.getRole());
        newUser.setAdmin(admin);
        userRepo.save(newUser);

        return "Registration Completed Successfully"
                ;
    }

    @Override
    public String registrationOfJobseeker(JobSeekerRequestDto seekerDto) {
        JobSeeker seekerProfile = new JobSeeker();
        seekerProfile.setFirstName(seekerDto.getFirstName());
        seekerProfile.setLastName(seekerDto.getLastName());
        seekerProfile.setEmail(seekerDto.getEmail());
        seekerProfile.setYearOfExperience(seekerDto.getYearOfExperience());
//		seekerProfile.setAdmin(new Admin((long)1));

        Optional<Admin> adminHolder = adminRepo.findById((long) 1);

        Admin admin = new Admin();
        if(adminHolder.isPresent()) {
            admin = adminHolder.get();
        }
        seekerProfile.setAdmin(admin);

        JobSeeker persitanceSeeker = jobSeekerRepo.save(seekerProfile);

        User newUser = new User();

        newUser.setEmail(seekerDto.getEmail());
        String cryptPassword=new BCryptPasswordEncoder().encode(seekerDto.getPassword());
        newUser.setPassword(cryptPassword);
        newUser.setJobSeeker(persitanceSeeker);
        newUser.setRole("ROLE_JOBSEEKER");
        newUser.setAdmin(admin);
        userRepo.save(newUser);

//		Optional<JobSeeker> persistedSeekerHolder = jobSeekerRepo.findByEmail(seekerProfile.getEmail());
//
//		if (persistedSeekerHolder.isEmpty()) {
//			return "something went wrong!! not able to save ";
//		}

//		JobSeeker persistedSeeker = persistedSeekerHolder.get();
//
//		seekerDto.getSkills().forEach(skill -> {
//			Optional<Skill>  skillHolder = skillRepo.findBySkillId(skill.getSkillId());
//			skillHolder.ifPresent(value -> persistedSeeker.getSkills().add(value));
//		});
//
//		seekerDto.getEduInfo().forEach(edu -> {
//			EducationalDetails educationalDetailsHolder = mapper.map(edu, EducationalDetails.class);
//			educationalDetailsHolder.setJobSeeker(persistedSeeker);
//			persistedSeeker.getEduInfo().add(educationalDetailsHolder);
//		});


//		jobSeekerRepo.save(seekerProfile);
        return "profile created succefully";
    }
}
