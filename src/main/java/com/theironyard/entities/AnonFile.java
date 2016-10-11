package com.theironyard.entities;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class AnonFile {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String filename;

    @Column(nullable = false)
    String originalFilename;

    @Column(nullable = true)
    String deletepassword;

    public AnonFile() {
    }

    public AnonFile(String filename, String originalFilename) {
        this.filename = filename;
        this.originalFilename = originalFilename;
    }

    public int getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public String getDeletePassword() {
        return deletepassword;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public void setDeletePassword(String deletePassword) {
        this.deletepassword = deletePassword;
    }
}
