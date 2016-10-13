package com.theironyard.services;

import com.theironyard.entities.AnonFile;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by joe on 11/10/2016.
 */
public interface AnonFileRepository extends CrudRepository<AnonFile, Integer> {
}
