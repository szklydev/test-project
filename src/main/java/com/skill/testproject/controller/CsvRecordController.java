package com.skill.testproject.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.skill.testproject.entity.CsvRecord;
import com.skill.testproject.service.CsvRecordService;

@RestController
@RequestMapping("/api/csv")
public class CsvRecordController {
    private final CsvRecordService service;
    @Autowired
    public CsvRecordController(CsvRecordService service) {
        this.service = service;
    }
    @PostMapping("/upload")
    public ResponseEntity<?> uploadCsv(@RequestParam("file") MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            List<CsvRecord> records = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            for (CSVRecord csvRecord : csvParser) {
                CsvRecord record = new CsvRecord();
                record.setSource(csvRecord.get("source"));
                record.setCodeListCode(csvRecord.get("codeListCode"));
                record.setCode(csvRecord.get("code"));
                record.setDisplayValue(csvRecord.get("displayValue"));
                record.setLongDescription(csvRecord.get("longDescription"));
                record.setFromDate(LocalDate.parse(csvRecord.get("fromDate"), formatter));
                String toDateString = csvRecord.get("toDate");
                if (!toDateString.isEmpty()) {
                    record.setToDate(LocalDate.parse(toDateString, formatter));
                }
                String sortingPriorityString = csvRecord.get("sortingPriority");
                if (!sortingPriorityString.isEmpty()) {
                    record.setSortingPriority(Integer.parseInt(sortingPriorityString));
                }
                records.add(record);
            }
            service.saveAll(records);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error reading CSV file");
        }
    }
    @GetMapping
    public List<CsvRecord> getAll() {
        return service.getAll();
    }
    @GetMapping("/{code}")
    public ResponseEntity<CsvRecord> getByCode(@PathVariable String code) {
        Optional<CsvRecord> record = service.getByCode(code);
        return record.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        service.deleteAll();
        return ResponseEntity.ok().build();
    }
}