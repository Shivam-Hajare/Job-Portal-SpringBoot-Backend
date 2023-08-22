package com.app.Job_Portal.service;

import com.app.Job_Portal.entities.JobSeeker;
import com.app.Job_Portal.entities.Resume;
import com.app.Job_Portal.exceptions.ResourceNotFoundException;
import com.app.Job_Portal.repository.JobSeekerRepository;
import com.app.Job_Portal.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepo;

    @Autowired
    private JobSeekerRepository jobSeekerRepo;
    public String uploadResume(MultipartFile file, Long jobSeekerId) throws IOException {
        JobSeeker seeker = jobSeekerRepo.findById(jobSeekerId).orElseThrow(() -> new ResourceNotFoundException("Job seeker with given id doesn't exists"));

        if (file.isEmpty()) {
            return "file is empty";
        }

        long fileSize = file.getSize();
        String originalFilename = file.getOriginalFilename();

        if (originalFilename == null) {
            return "file name cannot be empty";
        }

        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        if (!fileExtension.equals("pdf")) {
            return "please upload pdf only";
        }

//            if(fileSize <= 5_000_000 ) {
//                return "please upload file upto 5mb";
//            }

        Resume resume = new Resume();
        resume.setJobseeker(seeker);
        resume.setResumeFile(file.getBytes());
        resumeRepo.save(resume);
        return "file uploaded";
    }


    public InputStream getResume(Long id) {
        return new ByteArrayInputStream(resumeRepo.findResumeByJobSeekerId(id));
    }


}
