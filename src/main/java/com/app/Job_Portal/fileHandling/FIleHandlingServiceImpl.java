package com.app.Job_Portal.fileHandling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Service
public class FIleHandlingServiceImpl {

    @Autowired
    FileHandlingRepo fileHandlingRepos;

    public String uploadImage(MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();

            // copy this part
            long fileSize = file.getSize();
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

             if(!fileExtension.equals("pdf")) {
                 return "please upload pdf only";
             }

            // You can process the file further, like saving it to disk or performing validations.

            System.out.println(" FILEEXTESION IS " + fileExtension  );
            System.out.println("size of file " + fileSize);


            // and copy this part may be
            byte[] temp = file.getBytes();
       ResumeTempo resumeObject = new ResumeTempo();
       resumeObject.setRes(temp);
       resumeObject.setName("abcd");
       fileHandlingRepos.save(resumeObject);
            return "file uploaded";


        } else {
            // Handle the case where no file was uploaded.
            return "file was not uploaded";
        }
    }


    public InputStream restoreImage(Long id) {

        try {
            ResumeTempo temp =   fileHandlingRepos.findById(id).get();
            System.out.println("success");
             return new ByteArrayInputStream(temp.getRes());
//            return Base64.getEncoder().encodeToString(temp.getRes());
        }catch (Exception e) {
            System.out.println("Exception " + e);
            System.out.println("failure");
            return null;
        }
    }
}
