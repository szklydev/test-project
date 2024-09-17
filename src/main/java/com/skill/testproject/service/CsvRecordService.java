package com.skill.testproject.service;

import com.skill.testproject.entity.CsvRecord;
import com.skill.testproject.repository.CsvRecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class CsvRecordService {
    private final CsvRecordRepository repository;
    @Autowired
    public CsvRecordService(CsvRecordRepository repository) {
        this.repository = repository;
    }
    public void saveAll(List<CsvRecord> records) {
        repository.saveAll(records);
    }
    public List<CsvRecord> getAll() {
        return repository.findAll();
    }
    public Optional<CsvRecord> getByCode(String code) {
        return repository.findById(code);
    }
    public void deleteAll() {
        repository.deleteAll();
    }
}