package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by joe on 11/10/2016.
 */
@Entity
@Table(name = "morefiles")
public class AnonFile implements Comparable {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String fileName;

    @Column(nullable = false)
    String originalFilename;

    @Column(nullable = false, unique = true)
    String password;

    @Column(nullable = false)
    String permanent;

    @Column(nullable = false)
    String comment;

    public AnonFile() {
    }

    public AnonFile(String fileName, String originalFilename, String password, String permanent, String comment) {
        this.fileName = fileName;
        this.originalFilename = originalFilename;
        this.password = password;
        this.permanent = permanent;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPermanent(String permanent) {
        this.permanent = permanent;
    }

    public String getPermanent() {
        return permanent;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;}

    @Override
    public int compareTo(java.lang.Object o) {
        AnonFile m = (AnonFile) o;
        return id - m.id;
    }
}
