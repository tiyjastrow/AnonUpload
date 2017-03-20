package com.theironyard.entities;

import com.theironyard.utilities.PasswordStorage;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class AnonFile {
    @Id
    @GeneratedValue
    Integer id;

    @Column(nullable = false)
    String filename;

    @Column(nullable = false)
    String originalFilename;

    @Column(nullable = false, unique = true)
    String linkText;

    @Column
    String permanence;

    @Column
    String password;

    public AnonFile() {
    }

    public AnonFile(String filename, String originalFilename, String linkText, String permanence, String password) throws PasswordStorage.CannotPerformOperationException {
        this.filename = filename;
        this.originalFilename = originalFilename;
        this.linkText = linkText;
        this.permanence = permanence;
        setPassword(password);
    }
    private String getPasswordHash() {
        return password;
    }

    public void setPassword(String password) throws PasswordStorage.CannotPerformOperationException {
        this.password = PasswordStorage.createHash(password);
    }

    public Boolean verifyPassword(String password) throws PasswordStorage.InvalidHashException, PasswordStorage.CannotPerformOperationException {
        return PasswordStorage.verifyPassword(password, getPasswordHash());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public String getPermanence() {
        return permanence;
    }

    public void setPermanence(String permanence) {
        this.permanence = permanence;
    }
}
