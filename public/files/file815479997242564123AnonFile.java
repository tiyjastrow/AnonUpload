package com.theirondyard.entities;

import javax.persistence.*;

/**
 * Created by joshuakeough on 10/11/16.
 */
@Entity
@Table(name = "files")

public class AnonFile {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String originalFilename;

    private String comment;

    public AnonFile() {
    }

    public AnonFile(String filename, String originalFilename, String comment) {
        this.filename = filename;
        this.originalFilename = originalFilename;
        this.comment = comment;
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

    public void setOriginalfilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
