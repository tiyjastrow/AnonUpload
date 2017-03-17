package com.bharris.entities;

import com.bharris.utilities.PasswordStorage;

import javax.persistence.*;

@Entity
@Table(name="files")
public class AnonFile {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable=false)
    private String filename;

    @Column(nullable = false)
    private String originalFilename;

    @Column(nullable = false)
    private boolean toSave = false;

    @Column
    private String password;


    public AnonFile(String filename, String originalFilename) {
        this.filename = filename;
        this.originalFilename = originalFilename;
    }

    public AnonFile(String filename, String originalFilename, String password) throws PasswordStorage.CannotPerformOperationException {
        this.filename = filename;
        this.originalFilename = originalFilename;
        setPassword(password);
    }

    public void setPassword(String password) throws PasswordStorage.CannotPerformOperationException {
        this.password = PasswordStorage.createHash(password);
    }

    public boolean verifyPassword(String password) throws PasswordStorage.InvalidHashException, PasswordStorage.CannotPerformOperationException {
        return PasswordStorage.verifyPassword(password, getPasswordHash());
    }


    public AnonFile(){}

    public int getId() {
        return id;
    }

    public boolean isToSave() {
        return toSave;
    }

    public void setToSave(boolean toSave) {
        this.toSave = toSave;
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

    public String getPasswordHash() {
        return password;
    }
}
