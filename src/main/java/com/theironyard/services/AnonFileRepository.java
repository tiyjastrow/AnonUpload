package com.theironyard.services;


import com.theironyard.entities.AnonFile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnonFileRepository extends CrudRepository<AnonFile, Integer> {

    AnonFile findFirstByPermanentIsNull();
    List<AnonFile> findAllByPermanentIsNull();


}
