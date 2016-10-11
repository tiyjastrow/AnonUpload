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

    @Column(nullable = false)
    String deletepassword = "";

    @Column(nullable = false)
    boolean hasDeletePassword = false;

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

    public String getDeletepassword() {
        return deletepassword;
    }

    public boolean isHasDeletePassword() {
        return hasDeletePassword;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public void setDeletepassword(String deletepassword) {
        this.deletepassword = deletepassword;
    }

    public void setHasDeletePassword(boolean hasDeletePassword) {
        this.hasDeletePassword = hasDeletePassword;
    }

    public void toggleHasDeletePassword() {
        this.hasDeletePassword = !hasDeletePassword;
    }
}
