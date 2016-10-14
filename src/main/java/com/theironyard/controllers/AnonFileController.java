package com.theironyard.controllers;

import com.theironyard.entities.AnonFile;
import com.theironyard.services.AnonFileRepository;
import com.theironyard.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by halleyfroeb on 10/11/16.
 */
@RestController
public class AnonFileController {
    @Autowired
    AnonFileRepository files;
    //(String filename, String originalFilename, String comment, boolean permanent)

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public void upload(MultipartFile file, HttpServletResponse response, String comment, String permanent, String password) throws Exception {
        File dir = new File("public/files");
        dir.mkdirs();

        File f = File.createTempFile("file", file.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(file.getBytes());

        AnonFile anonFile = new AnonFile(f.getName(), file.getOriginalFilename(), comment, permanent, PasswordStorage.createHash(password));
        files.save(anonFile);

        response.sendRedirect("/");
    }

    @RequestMapping(path = "/files", method = RequestMethod.GET)
    public List<AnonFile> getFiles() throws IOException {

        List<AnonFile> anonFiles = files.findAllByOrderByIdAsc();
        List<AnonFile> toDelete = new ArrayList<>();

        for (AnonFile a : anonFiles){
            if(a.getPermanent() == null){
                toDelete.add(a);
            }
        }
        if (anonFiles.size()>10){
            String fileName = toDelete.get(0).getFilename();
            Path filePath = Paths.get("public/files/", fileName);
            Files.delete(filePath);
            Integer del = toDelete.get(0).getId();
            toDelete.remove(del);
            anonFiles.remove(del);
            files.delete(del);
        }


        return files.findAllByOrderByIdAsc();
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public void deleteFile(String password, Integer id, HttpServletResponse response) throws Exception {
        AnonFile file = files.findById(id);
        if (!PasswordStorage.verifyPassword(password, file.getPassword())){
            throw new Exception("wrong password!");
        }else{
            files.delete(id);
            response.sendRedirect("/");
        }
    }
}

