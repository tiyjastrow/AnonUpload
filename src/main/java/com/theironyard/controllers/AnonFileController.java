package com.theironyard.controllers;

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
import java.io.IOException;
import java.util.List;

@RestController
public class AnonFileController {
    @Autowired
    AnonFileRepository files;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public void upload(MultipartFile file, HttpServletResponse response, String comment, String check, String password) throws IOException, PasswordStorage.CannotPerformOperationException {
        File dir = new File("public/files");
        dir.mkdirs();
        File f = File.createTempFile("file", file.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(file.getBytes());
        fos.close();
        AnonFile anonFile = new AnonFile(f.getName(), file.getOriginalFilename(), comment, check, password);
        files.save(anonFile);

        List<AnonFile> deletableList = files.findAllByPermanenceIsNull();
        if (deletableList.size() > 10) {
            for (AnonFile tempFile : files.findAllByOrderByIdAsc()) {
                if (tempFile.getPermanence() == null) {
                    files.delete(tempFile);
                }
            }
        }
        response.sendRedirect("/");
    }

    @RequestMapping(path = "/files", method = RequestMethod.GET)
    public List<AnonFile> getFiles() {
        return (List<AnonFile>) files.findAll();
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public void delete(HttpServletResponse response, Integer deleteId, String deletePass) throws Exception {
        if (deleteId == null) {
            throw new Exception("null id");
        }
        if (files.findOne(deleteId).verifyPassword(deletePass)) {
            files.delete(deleteId);
        }
        response.sendRedirect("/");
    }
}
