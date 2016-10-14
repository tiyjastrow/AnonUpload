package com.theironyard.services;

import com.theironyard.entities.AnonFile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jakefroeb on 10/11/16.
 */
public interface AnonFileRepository extends CrudRepository<AnonFile, Integer> {

    List<AnonFile> findAllByOrderByIdAsc();
    List<AnonFile> findByPermanentOrderByIdAsc(boolean permanent);
    AnonFile findFirstByName(String name);
}
