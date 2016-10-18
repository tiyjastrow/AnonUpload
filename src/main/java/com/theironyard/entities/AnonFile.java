package com.theironyard.entities;

import javax.persistence.*;


@Entity
@Table(name="files")
public class AnonFile {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable=false)
    String originalFileName;
    @Column(nullable=false)
    String realFileName;
    @Column
    String comment;
    @Column (nullable=false)
    Boolean permFile;
    @Column
    String password;

    public AnonFile() {
    }

    public AnonFile(String originalFileName, String realFileName, String comment, Boolean permFile, String password) {
        this.originalFileName = originalFileName;
        this.realFileName = realFileName;
        this.comment = comment;
        this.permFile = permFile;
        this.password = password;
    }

    public Boolean getPermFile() {
        return permFile;
    }

    public void setPermFile(Boolean permFile) {
        this.permFile = permFile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getRealFileName() {
        return realFileName;
    }

    public void setRealFileName(String realFileName) {
        this.realFileName = realFileName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}