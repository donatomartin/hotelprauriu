package com.hotelprauriu.app.services;

import com.hotelprauriu.app.entities.Log;
import com.hotelprauriu.app.repositories.LoggerRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoggerService {

    private final LoggerRepository loggerRepository;

    public LoggerService(LoggerRepository loggerRepository) {
        this.loggerRepository = loggerRepository;
    }

    public void log(Log log) {
        loggerRepository.save(log);
    }

    public List<Log> getLogs() {
        return loggerRepository.findAllOrdered();
    }

    public List<Log> findLogsByAction(String searchText) {
        return loggerRepository.findAllByAction(searchText);
    }

    public void deleteLogs() {
        loggerRepository.deleteAll();
    }

    public void deleteLogs(String action) {
        loggerRepository.deleteByAction(action);
    }
}