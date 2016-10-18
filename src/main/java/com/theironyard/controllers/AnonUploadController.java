package com.theironyard.controllers;

import com.theironyard.entities.AnonFile;
import com.theironyard.services.AnonFileRepository;
import com.theironyard.utilities.PasswordStorage;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;


@RestController
public class AnonUploadController {


    @Autowired
    AnonFileRepository files;

    @PostConstruct
    public void init() throws SQLException {
        Server.createWebServer().start();
    }

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile file, HttpServletResponse response, String comment, Boolean permFile,
                         String password, String deletePassword) throws Exception {
        File dir = new File("public/files");
        dir.mkdirs();

        File uploadedFile = File.createTempFile("file", file.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(uploadedFile);
        fos.write(file.getBytes());

        AnonFile anonFile = new AnonFile(file.getOriginalFilename(), uploadedFile.getName(), comment, permFile,
                PasswordStorage.createHash(password));
        if (comment == null) {
            anonFile.setComment(file.getOriginalFilename());
        }
        if (permFile != null) {
            anonFile.setPermFile(true);
        } else {
            anonFile.setPermFile(false);
        }

        files.save(anonFile);

        if (files.countByPermFileFalse() > 10) {

            AnonFile deleteAnonFile = (files.findFirstByPermFileFalseOrderByIdAsc());
            files.delete(deleteAnonFile);
            File deleteFile = new File("/public/files/" + deleteAnonFile.getRealFileName());
            deleteFile.delete();

        }

        response.sendRedirect("/");
        return "redirect/";

    }



    @RequestMapping(path = "/files", method = RequestMethod.GET)
    public Iterable<AnonFile> getFiles() {
        return (files.findAll());
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public String delete(String deletePassword) throws Exception {
        List<AnonFile> anonFiles = (List<AnonFile>) files.findAll();
        for (AnonFile a : anonFiles) {
            if (deletePassword == "" || deletePassword == null) {
                throw new Exception("Enter a valid password!");
            } else if (PasswordStorage.verifyPassword(deletePassword, a.getPassword())) {
                int id = a.getId();
                AnonFile deleteAnonFile = files.findOne(id);
                files.delete(id);
                File deleteFile = new File("public/files/" + deleteAnonFile.getRealFileName());
                deleteFile.delete();
            }
        }

        return "redirect:/";
    }

}
