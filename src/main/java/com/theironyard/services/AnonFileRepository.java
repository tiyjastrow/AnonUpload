package com.theironyard.services;

import com.theironyard.entities.AnonFile;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ericcollier on 10/11/16.
 */
public interface AnonFileRepository extends CrudRepository<AnonFile, Integer> {
}
