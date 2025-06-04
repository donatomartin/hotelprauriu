package com.hotelprauriu.app.repositories;

import com.hotelprauriu.app.entities.LogEntry;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogEntryRepository extends CrudRepository<LogEntry, UUID> {
  Iterable<LogEntry> findAllByOrderByTimestampDesc();
}
