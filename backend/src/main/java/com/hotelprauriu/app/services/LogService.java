package com.hotelprauriu.app.services;

import com.hotelprauriu.app.entities.LogEntry;
import com.hotelprauriu.app.repositories.LogEntryRepository;
import org.springframework.stereotype.Service;

@Service
public class LogService {
  private final LogEntryRepository repo;

  public LogService(LogEntryRepository repo) {
    this.repo = repo;
  }

  public void log(String type, String message) {
    LogEntry entry = new LogEntry();
    entry.setType(type);
    entry.setMessage(message);
    repo.save(entry);
  }

  public Iterable<LogEntry> findAll() {
    return repo.findAllByOrderByTimestampDesc();
  }
}
