package info.nathanbutler.services;

import info.nathanbutler.entities.AnonFile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnonFileRepository extends CrudRepository<AnonFile, Integer> {
    @Query("SELECT MIN(id) FROM AnonFile WHERE isTrue = false")
    Integer findOldestFileIsFalse();
}
