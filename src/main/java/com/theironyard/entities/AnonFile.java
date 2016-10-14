package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by jeremypitt on 10/11/16.
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

    @Column
    String isPermanent;

    @Column
    String comment;

    String delPassword;

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

    public String getIsPermanent() {
        return isPermanent;
    }

    public void setIsPermanent(String isPermanent) {
        this.isPermanent = isPermanent;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDelPassword() {
        return delPassword;
    }

    public void setDelPassword(String delPassword) {
        this.delPassword = delPassword;
    }

    public AnonFile() {

    }

    public AnonFile(String filename, String originalFilename, String isPermanent, String comment, String delPassword) {

        this.filename = filename;
        this.originalFilename = originalFilename;
        this.isPermanent = isPermanent;
        this.comment = comment;
        this.delPassword = delPassword;
    }
}