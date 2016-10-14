package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by Zach on 10/11/16.
 */
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

    String perm;

    String comment;

    String deletePass;

    public AnonFile() {
    }

    public AnonFile(String filename, String originalFilename, String perm, String comment, String deletePass) {
        this.filename = filename;
        this.originalFilename = originalFilename;
        this.perm = perm;
        this.comment = comment;
        this.deletePass = deletePass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDeletePass() {
        return deletePass;
    }

    public void setDeletePass(String deletePass) {
        this.deletePass = deletePass;
    }
}
