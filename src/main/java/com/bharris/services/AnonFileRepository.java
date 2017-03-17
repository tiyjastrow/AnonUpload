package com.bharris.services;


import com.bharris.entities.AnonFile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface AnonFileRepository extends CrudRepository<AnonFile, Integer> {
    @Transactional
    @Modifying
    @Query("update AnonFile a set toSave = CASE toSave WHEN TRUE THEN FALSE\n" +
            "    ELSE TRUE END WHERE id=?1")
    void updateFile(int id);

    @Query("Select Min(id) from AnonFile WHERE toSave = false")
    Integer getOldestFileThatIsFalse();

}
