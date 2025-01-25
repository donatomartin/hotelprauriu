package com.hotelprauriu.app.services;

import com.hotelprauriu.app.entities.Log;
import com.hotelprauriu.app.repositories.LoggerRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {

    private final LoggerRepository loggerRepository;

    public LoggerService(LoggerRepository loggerRepository) {
        this.loggerRepository = loggerRepository;
    }

    public void log(Log log) {
        loggerRepository.save(log);
    }

    public Page<Log> getLogs(Pageable pageable) {
        return loggerRepository.findAllOrdered(pageable);
    }

    public Page<Log> findLogsByAction(String searchText, Pageable pageable) {
        return loggerRepository.findAllByAction(searchText, pageable);
    }

    public void deleteLogs() {
        loggerRepository.deleteAll();
    }

    public void deleteLogs(String action) {
        loggerRepository.deleteByAction(action);
    }
}