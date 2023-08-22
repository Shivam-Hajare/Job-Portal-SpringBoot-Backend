package com.app.Job_Portal.fileHandling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileHandlingController {

    @Autowired
    FIleHandlingServiceImpl fileService;
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam MultipartFile img ) throws IOException {
        System.out.println("inside upload");
        return fileService.uploadImage(img);

    }

    @GetMapping("/show/{id}")
    public void restoreImage(@PathVariable Long id ,
                               HttpServletResponse res) throws IOException {
        System.out.println("this show resume");
                fileService.restoreImage(id);
                InputStream resource = fileService.restoreImage(id);
         res.setContentType(String.valueOf(MediaType.APPLICATION_PDF));
        StreamUtils.copy(resource, res.getOutputStream());
//        InputStream
    }

    //   /show/id/xxx.pdf
}
