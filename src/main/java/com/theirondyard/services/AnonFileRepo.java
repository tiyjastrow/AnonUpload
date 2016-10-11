package com.theirondyard.services;

import com.theirondyard.entities.AnonFile;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by joshuakeough on 10/11/16.
 */
public interface AnonFileRepo extends CrudRepository<AnonFile, Integer> {
}
