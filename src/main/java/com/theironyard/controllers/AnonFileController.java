package com.theironyard.controllers;

import com.theironyard.entities.AnonFile;
import com.theironyard.services.AnonFileRepository;
import com.theironyard.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    public void upload(MultipartFile file, HttpServletResponse response, String permanent, String comment, String password)
            throws IOException, PasswordStorage.CannotPerformOperationException {
        int notPermanentCounter = files.findAllByPermanentIsNull().size() + 1;
        File dir = new File("public/files");
        dir.mkdirs();
        File f = File.createTempFile("file", file.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(file.getBytes());
        fos.close();

        AnonFile tempFile = new AnonFile(f.getName(), file.getOriginalFilename(), permanent, comment, PasswordStorage.createHash(password));

        if (notPermanentCounter <= 10) {
            files.save(tempFile);
        } else if (tempFile.getPermanent() == null) {
            AnonFile deleteFile = files.findFirstByPermanentIsNull();
            files.delete(deleteFile);
            files.save(tempFile);
            System.out.println("Too many non-permanent files! Deleting oldest, non-permanent entry.");
            notPermanentCounter--;
        } else if (tempFile.getPermanent().equals("true")) {
            files.save(tempFile);
        }
        response.sendRedirect("/");
    }

    @RequestMapping(path = "/files", method = RequestMethod.GET)
    public List<AnonFile> getFiles() {
        return (List<AnonFile>) files.findAll();
    }

    @RequestMapping(path = "/deleteFile", method = RequestMethod.POST)
    public void deleteFile(Integer id,  String password) throws PasswordStorage.InvalidHashException, PasswordStorage.CannotPerformOperationException {
        AnonFile anonFile = files.findOne(id);
        if (PasswordStorage.verifyPassword(password, anonFile.getPassword())) {
            files.delete(anonFile);
        }
    }

}
