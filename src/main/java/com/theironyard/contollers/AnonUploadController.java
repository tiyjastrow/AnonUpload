package com.theironyard.contollers;

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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zach on 10/11/16.
 */

@RestController
public class AnonUploadController {

    @Autowired
    AnonFileRepository files;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public void upload(MultipartFile file, HttpServletResponse response, String htmlCheckbox, String comment, String deletePass) throws Exception {
        File dir = new File("public/files");
        dir.mkdirs();
        List<AnonFile> allFiles = (List<AnonFile>) files.findAll();
        List<AnonFile> listOfTenFiles = new ArrayList<>();

        saveNewFile(file, htmlCheckbox, comment, deletePass, dir, allFiles, listOfTenFiles);

        deleteFilesOverLimit(listOfTenFiles);

        response.sendRedirect("/");
    }

    @RequestMapping(path = "/files", method = RequestMethod.GET)
    public List<AnonFile> getFiles() {
        return (List<AnonFile>) files.findAll();
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public void deleteFile(HttpServletResponse response, String deleteId, String deletePassInput) throws IOException, PasswordStorage.InvalidHashException, PasswordStorage.CannotPerformOperationException {
        deleteFile(deleteId, deletePassInput);
        response.sendRedirect("/");
    }

    private void saveNewFile(MultipartFile file, String htmlCheckbox, String comment, String deletePass, File dir, List<AnonFile> allFiles, List<AnonFile> listOfTenFiles) throws IOException, PasswordStorage.CannotPerformOperationException {
        File f = File.createTempFile("file", file.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(file.getBytes());

        AnonFile anonfile = new AnonFile(f.getName(), file.getOriginalFilename(), htmlCheckbox, comment, PasswordStorage.createHash(deletePass));
        files.save(anonfile);

        for (AnonFile a : allFiles) {
            if (a.getPerm() == null) {
                listOfTenFiles.add(a);
            }
        }
    }

    private void deleteFile(String deleteId, String deletePassInput) throws PasswordStorage.CannotPerformOperationException, PasswordStorage.InvalidHashException, IOException {
        List<AnonFile> anonFileList = (List<AnonFile>) files.findAll();
        for (AnonFile a : anonFileList) {
            if (a.getId() == Integer.parseInt(deleteId) && PasswordStorage.verifyPassword(deletePassInput, a.getDeletePass())) {
                String fileName = a.getFilename();
                Path filePath = Paths.get("public/files/", fileName);
                Files.delete(filePath);
                files.delete(a.getId());
            }
        }
    }

    private void deleteFilesOverLimit(List<AnonFile> listOfTenFiles) throws IOException {
        if (listOfTenFiles.size() > 9) {
            AnonFile oldFile = listOfTenFiles.get(0);

            for (AnonFile a : listOfTenFiles) {
                if (a == oldFile) {
                    String fileName = a.getFilename();
                    Path filePath = Paths.get("public/files/", fileName);
                    Files.delete(filePath);
                }
            }
            files.delete(oldFile.getId());
        }
    }

}


