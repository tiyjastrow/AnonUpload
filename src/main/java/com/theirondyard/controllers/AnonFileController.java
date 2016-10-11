package com.theirondyard.controllers;

import com.theirondyard.entities.AnonFile;
import com.theirondyard.services.AnonFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by joshuakeough on 10/11/16.
 */
@RestController
public class AnonFileController {
    @Autowired
    AnonFileRepo files;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public void upload(MultipartFile file, HttpServletResponse response) throws IOException {
        File dir = new File("public/files");
        dir.mkdirs();

        File f = File.createTempFile("file", file.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(file.getBytes());

        AnonFile anonfile = new AnonFile(f.getName(), file.getOriginalFilename());
        files.save(anonfile);

        response.sendRedirect("/");
    }

    @RequestMapping(path = "/files", method = RequestMethod.GET)
    public List<AnonFile> getfiles() {
        return (List<AnonFile> )files.findAll();
    }

}
