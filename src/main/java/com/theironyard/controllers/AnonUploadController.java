package com.theironyard.controllers;

import com.theironyard.entities.AnonFile;
import com.theironyard.services.AnonFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RestController
public class AnonUploadController {
    @Autowired
    AnonFileRepository files;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public void upload(MultipartFile file, boolean permanent, String shortFileName, String deletepassword, HttpServletResponse response) throws Exception {
        File dir = new File("public/files");
        dir.mkdirs();
        File f;

        if (permanent) {
            f = File.createTempFile("file", file.getOriginalFilename(), dir);
        }
        else {
            f = File.createTempFile("tempfile", file.getOriginalFilename(), dir);
        }

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(file.getBytes());

        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("temp");
            }
        };

        File[] tempFiles = dir.listFiles(filter);

        Arrays.sort( tempFiles, new Comparator() {
                public int compare(Object o1, Object o2) {
                    return new Long(new Long(((File) o2).lastModified())).compareTo(((File)o1).lastModified());
                }

            }
        );

        for(int i=0;i<tempFiles.length;i++)
        {
            if (i >= 10) {
                tempFiles[i].delete();
                AnonFile deleteThis = files.findByFilename(tempFiles[i].getName());
                files.delete(deleteThis);
            }
        }

        AnonFile anonFile = new AnonFile(f.getName(), shortFileName);
        if (! deletepassword.isEmpty()) {
            anonFile.setDeletepassword(deletepassword);
            anonFile.toggleHasDeletePassword();
        }
        files.save(anonFile);

        response.sendRedirect("/");
    }

    @RequestMapping(path = "/files", method = RequestMethod.GET)
    public List<AnonFile> getFiles() {
        return (List<AnonFile>) files.findAll();
    }

    @RequestMapping(path = "/files/{id}", method = RequestMethod.GET)
    public AnonFile getFiles(@PathVariable("id") int id) {
        return files.findOne(id);
    }

    @RequestMapping(path = "/delete-file", method = RequestMethod.POST)
    public void deleteFile(int id, HttpServletResponse response) throws Exception {
        AnonFile af = files.findOne(id);
        File dir = new File("public/files");
        File [] fileArray = dir.listFiles();
        for (File f : fileArray) {
            if (f.getName().equals(af.getFilename())) {
                f.delete();
            }
        }
        files.delete(id);

        response.sendRedirect("/");
    }

    @RequestMapping(path = "/delete-file-pass", method = RequestMethod.POST)
    public void deletePasswordFile(String deletepassword, HttpServletResponse response) throws Exception {
        AnonFile af;
        if (! deletepassword.isEmpty()) {
            af = files.findByDeletepassword(deletepassword);
            if (af != null) {
                File dir = new File("public/files");
                File[] fileArray = dir.listFiles();
                for (File f : fileArray) {
                    if (f.getName().equals(af.getFilename())) {
                        f.delete();
                    }
                }
                files.delete(af.getId());
            }
        }

        response.sendRedirect("/");
    }
}
