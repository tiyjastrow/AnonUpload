package com.theironyard.entities;

import javax.persistence.*;

@Entity
@Table(name="files")
public class AnonFile {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String filename;

    @Column(nullable = false)
    String originalFilename;

    @Column(nullable = true)
    String permanent;

    @Column(nullable = false)
    String comment;

    @Column(nullable = true)
    String password;

    public AnonFile(String filename, String originalFilename, String permanent, String comment, String password) {
        this.filename = filename;
        this.originalFilename = originalFilename;
        this.permanent = permanent;
        this.comment = comment;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public AnonFile() {
    }

    public String getPermanent() {
        return permanent;
    }

    public void setPermanent(String permanent) {
        this.permanent = permanent;
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
}
