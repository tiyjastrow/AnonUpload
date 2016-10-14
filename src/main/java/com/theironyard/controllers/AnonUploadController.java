package com.theironyard.controllers;

import com.sun.tools.internal.ws.processor.model.Response;
import com.theironyard.entities.AnonFile;
import com.theironyard.services.AnonFileRepository;
import com.theironyard.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by jakefroeb on 10/11/16.
 */
@RestController
public class AnonUploadController {
    @Autowired
    AnonFileRepository files;

    @RequestMapping(path="/upload", method = RequestMethod.POST)
    public void upload(MultipartFile file, HttpServletResponse response, boolean permanent, String comment, String password)throws Exception{
        File dir = new File("public/files");
        dir.mkdirs();
        File f = File.createTempFile("file", file.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(file.getBytes());
        AnonFile anonFile = new AnonFile(f.getName(), file.getOriginalFilename(),comment,password);
        if(permanent){
            anonFile.setPermanent(true);
        }
        files.save(anonFile);
        List<AnonFile> tempFiles = files.findByPermanentOrderByIdAsc(false);
        if(tempFiles.size() == 10){
            String fileName = tempFiles.get(0).getFilename();
            Path filePath = Paths.get("public/files/", fileName);
            Files.delete(filePath);
            files.delete(tempFiles.get(0).getId());
        }
        response.sendRedirect("/");
    }
    @RequestMapping(path="/delete", method = RequestMethod.POST)
    public void delete(String password, String name, HttpServletResponse response)throws Exception {
        AnonFile file = files.findFirstByName(name);
        if (file != null) {
            if(file.isValid(password)) {
                String fileName = file.getFilename();
                Path filePath = Paths.get("public/files/", fileName);
                Files.delete(filePath);
                files.delete(file);
            }
        }
        response.sendRedirect("/");
    }

    @RequestMapping(path="/files", method = RequestMethod.GET)
    public List<AnonFile> getFiles(){
        return (List<AnonFile>) files.findAll();
    }
}
