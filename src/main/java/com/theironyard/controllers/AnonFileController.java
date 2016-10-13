package com.theironyard.controllers;

import com.theironyard.entities.AnonFile;
import com.theironyard.services.AnonFileRepository;
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
import java.util.ArrayList;

/**
 * Created by joe on 11/10/2016.
 */

@RestController
public class AnonFileController {

    //String fileCount;

    @Autowired
    AnonFileRepository morefiles;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public void upload(HttpServletResponse response, MultipartFile file, String password, String permanent, String comment)throws IOException{

        File dir = new File("public/files");
        dir.mkdirs();

        File f  = File.createTempFile("file", file.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(file.getBytes());

        //AnonFile anonFile = new AnonFile(f.getName(), file.getOriginalFilename());
        AnonFile anonFile = new AnonFile(f.getName(), file.getOriginalFilename(),password, permanent, comment);
        morefiles.save(anonFile);

        response.sendRedirect("/");
    }

    @RequestMapping(path ="/files", method = RequestMethod.GET)
    public ArrayList<AnonFile> getFiles(){
        return (ArrayList<AnonFile>) morefiles.findAll();
    }
}
