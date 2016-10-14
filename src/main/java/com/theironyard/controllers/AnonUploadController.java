package com.theironyard.controllers;

import com.theironyard.PasswordStorage;
import com.theironyard.entities.AnonFile;
import com.theironyard.services.AnonFileRepository;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeremypitt on 10/11/16.
 */
@RestController
public class AnonUploadController {
    @Autowired
    AnonFileRepository files;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public void upload(MultipartFile file, HttpServletResponse response, String isPermanent, String comment, String delPassword) throws Exception {
        File dir = new File("public/files");
        dir.mkdirs();
        List<AnonFile> allFiles = (List) files.findAll();
        List<AnonFile> tempFiles = new ArrayList<>();
        List<AnonFile> permFiles = new ArrayList<>();

        for (AnonFile f : allFiles) {
            if (f.getIsPermanent() == null) {
                tempFiles.add(f);
            } else if (f.getIsPermanent() != null) {
                permFiles.add(f);
            }
        }

        if (tempFiles.size() >= 5) {
            AnonFile oldFile = tempFiles.get(0);
            for (AnonFile a : tempFiles) {
                if (a == oldFile) {
                    String fileName = a.getFilename();
                    Path filePath = Paths.get("public/files/", fileName);
                    Files.delete(filePath);
                }
            }
            files.delete(oldFile.getId());
        }

        File f = File.createTempFile("file", file.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(file.getBytes());

        if (delPassword.equals("")) {
            delPassword = null;
        }

        AnonFile anonFile = new AnonFile(f.getName(), file.getOriginalFilename(), isPermanent, comment, PasswordStorage.createHash(delPassword));
        files.save(anonFile);


        response.sendRedirect("/");
    }

    @RequestMapping(path = "/perm-files", method = RequestMethod.GET)
    public List<AnonFile> getPermFiles() {
        return (List<AnonFile>) files.findByIsPermanent(true);
    }

    @RequestMapping(path = "/files", method = RequestMethod.GET)
    public List<AnonFile> getFiles() {
        return (List<AnonFile>) files.findAll();
    }

    @RequestMapping(path = "/delete-file", method = RequestMethod.POST)
    public void deleteFile(HttpServletResponse response, String delID, String delPassInput) throws Exception {
        List<AnonFile> allFiles = (List<AnonFile>) files.findAll();

        for(AnonFile a: allFiles){
            if (a.getId() == Integer.parseInt(delID) && PasswordStorage.verifyPassword(delPassInput, a.getDelPassword())){
                String fileName = a.getFilename();
                Path filePath = Paths.get("public/files/", fileName);
                Files.delete(filePath);
                files.delete(a.getId());
            }
        }
        response.sendRedirect("/");
    }
}
