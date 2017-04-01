package info.nathanbutler.controllers;

import info.nathanbutler.entities.AnonFile;
import info.nathanbutler.services.AnonFileRepository;
import info.nathanbutler.utilities.PasswordStorage;
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
    AnonFileRepository files;//storing metadata about files

    //Todo add password protection to deletion code

    //write file out: creates a directory and creates a multipart file -- saves to a directory
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public void upload(MultipartFile file, HttpServletResponse response, Boolean isTrue, String fileDesc) throws IOException {

        File dir = new File("public/files");//the files will be stored in this directory
        dir.mkdirs();
        File f = File.createTempFile("file", file.getOriginalFilename(), dir);//static method points to createTemp
        FileOutputStream fos = new FileOutputStream(f);//write file out using this class
        fos.write(file.getBytes());
        fos.close();

        AnonFile anonFile = new AnonFile(f.getName(), file.getOriginalFilename(), fileDesc);
        if(isTrue != null){
            anonFile.setIsTrue(isTrue);
        };
        files.save(anonFile);

        Integer oldestFile = files.findOldestFileIsFalse();
        System.out.println(oldestFile);
        if(files.count() > 10) {
            files.delete(oldestFile);
        }
        response.sendRedirect("/");
    }

    @RequestMapping(path = "/files", method = RequestMethod.GET)
    public List<AnonFile> getFiles() {
        return (List<AnonFile>) files.findAll();
    }
}