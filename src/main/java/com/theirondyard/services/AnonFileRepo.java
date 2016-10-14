package com.theirondyard.services;

import com.theirondyard.entities.AnonFile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by joshuakeough on 10/11/16.
 */
public interface AnonFileRepo extends CrudRepository<AnonFile, Integer> {

    AnonFile findById(Integer id);
}
