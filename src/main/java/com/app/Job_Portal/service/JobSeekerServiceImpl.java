package com.app.Job_Portal.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.Job_Portal.dto.EducationalDetailsDto;
import com.app.Job_Portal.dto.JobApplicationResponseDto;
import com.app.Job_Portal.dto.JobListDto;
import com.app.Job_Portal.dto.JobSeekerRequestDto;
import com.app.Job_Portal.dto.JobSeekerResponseDto;
import com.app.Job_Portal.dto.SkillDto;
import com.app.Job_Portal.entities.EducationalDetails;
import com.app.Job_Portal.entities.Job;
import com.app.Job_Portal.entities.JobApplication;
import com.app.Job_Portal.entities.JobSeeker;
import com.app.Job_Portal.entities.JobType;
import com.app.Job_Portal.entities.Resume;
import com.app.Job_Portal.entities.Skill;
import com.app.Job_Portal.entities.Status;
import com.app.Job_Portal.entities.User;
import com.app.Job_Portal.exceptions.InvalidInputException;
import com.app.Job_Portal.exceptions.ResourceNotFoundException;
import com.app.Job_Portal.repository.EducationalDetailsRepository;
import com.app.Job_Portal.repository.JobApplicationRepository;
import com.app.Job_Portal.repository.JobRepository;
import com.app.Job_Portal.repository.JobSeekerRepository;
import com.app.Job_Portal.repository.ResumeRepository;
import com.app.Job_Portal.repository.SkillRepository;
import com.app.Job_Portal.repository.UserRepository;


@Service
@Transactional
public class JobSeekerServiceImpl implements JobSeekerService {
    @Autowired
    private JobSeekerRepository jobSeekerRepo;

    @Autowired
    private JobRepository jobRepo;

    @Autowired
    private JobApplicationRepository jobApplicationRepo;

    @Autowired
    private SkillRepository skillRepo;

    @Autowired
    private EducationalDetailsRepository educationalDetailsRepo;

    @Autowired
    private ResumeRepository resumeRepo;
    
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<JobListDto> getAllJobs() {

        List<Job> jobLists = jobRepo.findAll();

        return jobLists.stream()
                .map((job) -> {
                    JobListDto jobHolder = mapper.map(job, JobListDto.class);
                    jobHolder.setRecruiterName(job.getPostedBy().getFirstName());
                    jobHolder.setCompanyName(job.getPostedBy().getCompanyName());

                    List<Skill> listOfSkills = jobRepo.findSkillsByJobId(job.getJobId());
                    listOfSkills.forEach(skill -> {
                        jobHolder.getSkillsForJob().add(mapper.map(skill, SkillDto.class));
                    });

                    return jobHolder;
                })
                .collect(Collectors.toList());
    }


    @Override
    public List<JobListDto> getJobsWithGivenType(String jobType) {

        List<Job> jobLists = jobRepo.findAll();

        JobType typeOfJob = JobType.valueOf(jobType);

        return jobLists.stream()
                .map((job) -> {
                    JobListDto jobHolder;
                    if (job.getJobType().equals(typeOfJob)) {
                        jobHolder = mapper.map(job, JobListDto.class);
                        jobHolder.setRecruiterName(job.getPostedBy().getFirstName());
                        jobHolder.setCompanyName(job.getPostedBy().getCompanyName());

                        List<Skill> listOfSkills = jobRepo.findSkillsByJobId(job.getJobId());
                        listOfSkills.forEach(skill -> {
                            jobHolder.getSkillsForJob().add(mapper.map(skill, SkillDto.class));
                        });

                    } else {
                        jobHolder = null;
                    }
                    return jobHolder;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    @Override
    public List<JobListDto> getJobsWithGivenTitle(String title) {

        List<Job> jobLists = jobRepo.findAll();

        return jobLists.stream()
                .map((job) -> {
                    JobListDto jobHolder;
                    if (job.getJobTitle().toLowerCase().contains(title.toLowerCase())) {
                        jobHolder = mapper.map(job, JobListDto.class);
                        jobHolder.setRecruiterName(job.getPostedBy().getFirstName());
                        jobHolder.setCompanyName(job.getPostedBy().getCompanyName());

                        List<Skill> listOfSkills = jobRepo.findSkillsByJobId(job.getJobId());
                        listOfSkills.forEach(skill -> {
                            jobHolder.getSkillsForJob().add(mapper.map(skill, SkillDto.class));
                        });

                    } else {
                        jobHolder = null;
                    }
                    return jobHolder;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    @Override
    public List<JobApplicationResponseDto> getAppliedJobs(Long jobSeekerId) {

        JobSeeker seeker = jobSeekerRepo.findById(jobSeekerId).orElseThrow(() -> new ResourceNotFoundException("job seeker with given id not found"));

        List<JobApplication> listOfApplications = seeker.getJobApplications();
System.out.println("req ss");
        return listOfApplications.stream()
                .map((application) -> {
                    JobApplicationResponseDto responseDtoHolder = mapper.map(application.getJob(), JobApplicationResponseDto.class);

                    responseDtoHolder.setApplicationId(application.getApplicationId());
                    responseDtoHolder.setCompanyName(application.getJob().getPostedBy().getCompanyName());
                    responseDtoHolder.setAppliedDate(application.getAppliedDate());
                    responseDtoHolder.setStatus(application.getStatus());
                    responseDtoHolder.setPostedBy(application.getJob().getPostedBy().getFirstName() + " " + application.getJob().getPostedBy().getLastName());
                    responseDtoHolder.setSkillsForJob(application.getJob().getSkills());
                    
                    List<Skill> listOfSkills = jobRepo.findSkillsByJobId(responseDtoHolder.getJobId());
                    listOfSkills.forEach(skill -> {
                        System.out.println(skill.getName());
                        responseDtoHolder.getSkillsRequired().add(mapper.map(skill, SkillDto.class));
                    });
                    
                    return responseDtoHolder;
                })
                .collect(Collectors.toList());
    }


    @Override
    public List<JobApplicationResponseDto> getAllJobsWithGivenStatus(Long jobSeekerId, String status) {
        JobSeeker seeker = jobSeekerRepo.findById(jobSeekerId).orElseThrow(() -> new ResourceNotFoundException("job seeker with given id not found"));

        List<JobApplication> listOfApplications = seeker.getJobApplications();

        return listOfApplications.stream()
                .map((application) -> {
                    JobApplicationResponseDto responseDtoHolder = mapper.map(application.getJob(), JobApplicationResponseDto.class);

                    responseDtoHolder.setApplicationId(application.getApplicationId());
                    responseDtoHolder.setCompanyName(application.getJob().getPostedBy().getCompanyName());
                    responseDtoHolder.setAppliedDate(application.getAppliedDate());
                    responseDtoHolder.setStatus(application.getStatus());
                    responseDtoHolder.setPostedBy(application.getJob().getPostedBy().getFirstName() + " " + application.getJob().getPostedBy().getLastName());

                    List<Skill> listOfSkills = jobRepo.findSkillsByJobId(responseDtoHolder.getJobId());
                    listOfSkills.forEach(skill -> {
                        System.out.println(skill.getName());
                        responseDtoHolder.getSkillsRequired().add(mapper.map(skill, SkillDto.class));
                    });
                    return responseDtoHolder;
                })
                .filter(application -> application.getStatus().toString().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }


    @Override
    public String applyForAJob(Long jobId, Long jobSeekerId) {

        JobSeeker seeker = jobSeekerRepo.findById(jobSeekerId).orElseThrow(() -> new ResourceNotFoundException("job seeker with given id not found"));
        Job job = jobRepo.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("selected Job doesn't exists"));

        // check if already applied for the job
        for (JobApplication applicationHolder : job.getApplications()) {
            if (applicationHolder.getJobSeeker().getJobSeekerId().equals(seeker.getJobSeekerId())) {
                return "Already applied for this job";
            }
        }

        JobApplication application = new JobApplication();
        application.setJobSeeker(seeker);
        application.setJob(job);
        application.setStatus(Status.PENDING);
        application.setAppliedDate(LocalDate.now());
        seeker.submitAnApplication(application, job);

        jobApplicationRepo.save(application);

        return "Succefully applied for a job";

    }


    @Override
    public String withDrawAnApplication(Long jobId, Long jobSeekerId) {
        JobSeeker seeker = jobSeekerRepo.findById(jobSeekerId).orElseThrow(() -> new ResourceNotFoundException("job seeker with given id not found"));
        Job job = jobRepo.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("selected Job doesn't exists"));

        boolean removeAnApplication = false;
        JobApplication application = new JobApplication();

        // check applied or not
        for (JobApplication applicationHolder : job.getApplications()) {
            if (applicationHolder.getJobSeeker().getJobSeekerId().equals(seeker.getJobSeekerId())) {
                // pending application can be withdrawn
                if (applicationHolder.getStatus().equals(Status.PENDING)) {
                    application = applicationHolder;
                    removeAnApplication = true;
                    break;
                } else {
                    // already selected or rejected application cannot be withdrawn
                    return "cannot withdrawn already selected or rejected application";
                }

            }
        }

        // application exists and can be removed
        if (removeAnApplication) {
            seeker.withDrawAnApplication(application, job);
            return "application withdrawn succefully";
        }

        // application does not exists
        return "selected application does not exists ";
    }


    @Override
    public String createProfile(JobSeekerRequestDto seekerDto) {
        JobSeeker seekerProfile = new JobSeeker();
        seekerProfile.setFirstName(seekerDto.getFirstName());
        seekerProfile.setLastName(seekerDto.getLastName());
        seekerProfile.setEmail(seekerDto.getEmail());
        seekerProfile.setYearOfExperience(seekerDto.getYearOfExperience());
        jobSeekerRepo.save(seekerProfile);

        Optional<JobSeeker> persistedSeekerHolder = jobSeekerRepo.findByEmail(seekerProfile.getEmail());

        if (persistedSeekerHolder.isEmpty()) {
            return "something went wrong!! not able to save ";
        }

        JobSeeker persistedSeeker = persistedSeekerHolder.get();

        seekerDto.getSkills().forEach(skill -> {
            Optional<Skill>  skillHolder = skillRepo.findBySkillId(skill.getSkillId());
            skillHolder.ifPresent(value -> persistedSeeker.getSkills().add(value));
        });

        seekerDto.getEduInfo().forEach(edu -> {
            EducationalDetails educationalDetailsHolder = mapper.map(edu, EducationalDetails.class);
            educationalDetailsHolder.setJobSeeker(persistedSeeker);
            persistedSeeker.getEduInfo().add(educationalDetailsHolder);
        });


        jobSeekerRepo.save(seekerProfile);
        return "profile created succefully";
    }


    @Override
    public String updateProfile(JobSeekerRequestDto seekerDto, Long jobSeekerId) {

        JobSeeker seeker = jobSeekerRepo.findById(jobSeekerId).orElseThrow(() -> new ResourceNotFoundException("job seeker with given id not found"));

        seeker.setFirstName(seekerDto.getFirstName());
        seeker.setLastName(seekerDto.getLastName());
        seeker.setYearOfExperience(seekerDto.getYearOfExperience());
        
        List<EducationalDetailsDto> educationalDetailsDtos = seekerDto.getEduInfo();

        educationalDetailsDtos.forEach((dto)-> {
                   if(dto.getPercentage()<0 || dto.getPercentage() > 100) {
                       throw new InvalidInputException("percentage should be in between 0 and 100");
                   }
               });
        
        List<SkillDto> skillDtosList = seekerDto.getSkills();
        List<Skill> skills = new ArrayList<>();
        for (SkillDto skillDto : skillDtosList) {

            if(skillRepo.findById(skillDto.getSkillId()).isPresent()) {
                skills.add(skillRepo.findBySkillId(skillDto.getSkillId()).get());
            }

        }
        seeker.setSkills(skills);

        // Update educational details
        if (educationalDetailsDtos != null) {

            // Clear previous educational details
            seeker.getEduInfo().clear();

            List<EducationalDetails> educationalDetails = educationalDetailsDtos.stream()
                    .map(dto -> {
                        EducationalDetails seekerEducation = mapper.map(dto, EducationalDetails.class);
                        seekerEducation.setJobSeeker(seeker);
                        return seekerEducation;
                    })
                    .collect(Collectors.toList());
            educationalDetailsRepo.saveAll(educationalDetails);
            jobSeekerRepo.save(seeker);
        }

        return "profile updated succefully";
    }

    @Override
    public String deleteProfile(Long jobSeekerId) {
        JobSeeker seeker = jobSeekerRepo.findById(jobSeekerId).orElseThrow(() -> new ResourceNotFoundException("job seeker with given id Doesn't exists"));
        for (EducationalDetails eduDetail : seeker.getEduInfo()) {
            eduDetail.setJobSeeker(null);
        }
        for (Skill skill : seeker.getSkills()) {
            skill.getJobSeekers().remove(seeker);
        }
        for (JobApplication jobApp : seeker.getJobApplications()) {
            jobApp.setJobSeeker(null);
        }

        Optional<Resume> resume =  resumeRepo.findResumeByJobSeekerId(jobSeekerId);
        if(resume.isPresent()) {
            resumeRepo.delete(resume.get());
        }
        User user=userRepo.findByEmail(seeker.getEmail()).orElseThrow(()->new ResourceNotFoundException("job seeker with given id doesn't exists "));
        userRepo.delete(user);
        jobSeekerRepo.delete(seeker);
        return "jobseeker removed succefully";
    }

    @Override
    public JobSeekerResponseDto getProfile(Long jobSeekerId) {
        JobSeeker seeker = jobSeekerRepo.findById(jobSeekerId).orElseThrow(() -> new ResourceNotFoundException("job seeker with given id doesn't exists"));
        JobSeekerResponseDto responseDto = mapper.map(seeker, JobSeekerResponseDto.class);

//        seeker.getJobApplications().forEach(application -> {
//            responseDto.setApplications(mapper.map(application, JobApplicationResponseDto.class));
//        });
        seeker.getSkills().forEach(skill -> {
            responseDto.setSkills(mapper.map(skill, SkillDto.class));
        });
        seeker.getEduInfo().forEach(edu -> {
            responseDto.setEduInfo(mapper.map(edu, EducationalDetailsDto.class));
        });

        return responseDto;
    }



}
