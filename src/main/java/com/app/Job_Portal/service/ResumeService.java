package com.app.Job_Portal.service;

import com.app.Job_Portal.entities.JobSeeker;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface ResumeService {

    public String uploadResume(MultipartFile file, Long jobSeekerId) throws IOException;

    public InputStream getResume(Long id);
}
