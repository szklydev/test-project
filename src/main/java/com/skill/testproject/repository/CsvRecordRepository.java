package com.skill.testproject.repository;

import com.skill.testproject.entity.CsvRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsvRecordRepository extends JpaRepository<CsvRecord, String> {
}