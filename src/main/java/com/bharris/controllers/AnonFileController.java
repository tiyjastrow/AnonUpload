package com.bharris.controllers;

import com.bharris.entities.AnonFile;
import com.bharris.services.AnonFileRepository;
import com.bharris.utilities.PasswordStorage;
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

// force git
@RestController
public class AnonFileController {
    @Autowired
    AnonFileRepository files;

    @RequestMapping(path = "/files", method = RequestMethod.GET)
    public List<AnonFile> getFiles() {
        return (List<AnonFile>) files.findAll();
    }

    @RequestMapping(path="/save-item", method=RequestMethod.POST)
    public void saveItem(int id){
        files.updateFile(id);
    }

    @RequestMapping(path="/delete-item", method=RequestMethod.POST)
    public void deleteItem(int id, String password) throws Exception {
        AnonFile f = files.findOne(id);
        if(f.getPasswordHash() == null){
            files.delete(id);
        }else if(f.verifyPassword(password)){
            files.delete(id);
        }else{
            throw new Exception("Not Correct Password");
        }
    }

    @RequestMapping(path="/upload", method = RequestMethod.POST)
    public void upload(MultipartFile file, HttpServletResponse response, String password, String altName) throws IOException, PasswordStorage.CannotPerformOperationException {
        File dir = new File("public/files");
        dir.mkdirs();
        File f = File.createTempFile("file", file.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(file.getBytes());
        fos.close();

        if(files.count() >= 10){
            files.delete(files.getOldestFileThatIsFalse());
        }
        if(password.isEmpty() && altName.isEmpty()) {
            AnonFile anonFile = new AnonFile(f.getName(), file.getOriginalFilename());
            files.save(anonFile);
        }else if(password.isEmpty() && altName !=null) {
            AnonFile anonFile = new AnonFile(f.getName(), altName);
            files.save(anonFile);
        }else if(password != null && altName.isEmpty()) {
            AnonFile anonFile = new AnonFile(f.getName(), file.getOriginalFilename(), password);
            files.save(anonFile);
        } else if (password != null && altName != null) {
            AnonFile anonFile = new AnonFile(f.getName(), altName, password);
            files.save(anonFile);
        }

        response.sendRedirect("/");
    }
}
