package info.nathanbutler.entities;

import info.nathanbutler.utilities.PasswordStorage;

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
    Boolean isTrue;

    @Column(nullable = false)
    String fileDesc;

//    @Column(nullable = false)
//    private String password;

    public AnonFile(String filename, String originalFilename, String fileDesc)  {
        this.filename = filename;
        this.originalFilename = originalFilename;
        this.isTrue = false;
        this.fileDesc = fileDesc;
    }

    public AnonFile() {
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    public Boolean getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(Boolean isTrue) {
        this.isTrue = isTrue;
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

//    public void setPassword(String password) throws PasswordStorage.CannotPerformOperationException{
//        this.password = PasswordStorage.createHash(password);
//
//    }
//
//    private String getPasswordHash() {
//        return password;
//    }
//
//    public boolean verifyPassword(String Password) throws PasswordStorage.InvalidHashException, PasswordStorage.CannotPerformOperationException {
//
//        return PasswordStorage.verifyPassword(password, getPasswordHash());
//    }
}