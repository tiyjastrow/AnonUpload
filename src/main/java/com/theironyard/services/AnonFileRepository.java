package com.theironyard.services;


import com.theironyard.entities.AnonFile;
import org.springframework.data.repository.CrudRepository;

public interface AnonFileRepository extends CrudRepository<AnonFile, Integer> {
    public AnonFile findByFilename(String filename);
    public AnonFile findByDeletepassword(String deletepassword);
}
