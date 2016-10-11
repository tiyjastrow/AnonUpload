package com.theironyard.services;

import com.theironyard.entities.AnonFile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by halleyfroeb on 10/11/16.
 */
public interface AnonFileRepository extends CrudRepository<AnonFile, Integer> {

    int countByFilename(String filename);

    ("SELECT FROM files ORDER BY files_id DESC ")
    List<AnonFile> findByRecentFiles(Integer id);
}
