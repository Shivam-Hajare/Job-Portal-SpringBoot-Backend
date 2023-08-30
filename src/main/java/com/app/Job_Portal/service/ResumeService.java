package com.app.Job_Portal.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface ResumeService {

    public String uploadResume(MultipartFile file, Long jobSeekerId) throws IOException;

    public InputStream getResume(Long id);

    public String removeResume(Long jobseekerId);
}
