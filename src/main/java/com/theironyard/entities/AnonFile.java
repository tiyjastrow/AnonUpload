package com.theironyard.entities;

import com.theironyard.utilities.PasswordStorage;

import javax.persistence.*;

/**
 * Created by jakefroeb on 10/11/16.
 */
@Entity
@Table (name = "files")
public class AnonFile {

    @Id
    @GeneratedValue
    int id;

    @Column (nullable = false)
    String filename;

    @Column (nullable = false)
    String originalFilename;

    @Column (nullable = false)
    boolean permanent;

    @Column (nullable = false)
    String name;

    @Column (nullable = false)
    String password;

    public AnonFile(String filename, String originalFilename, String name, String password) throws Exception{
        this.filename = filename;
        this.originalFilename = originalFilename;
        this.permanent = false;
        this.name = name;
        this.password = PasswordStorage.createHash(password);
    }

    public AnonFile() {
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

    public boolean isPermanent() {
        return permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isValid(String password) throws Exception{
        if(PasswordStorage.verifyPassword(password,getPassword())){
            return true;
        }
        else{
            return false;
        }
    }
}
