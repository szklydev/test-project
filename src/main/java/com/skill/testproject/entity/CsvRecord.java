package com.skill.testproject.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "csv_records")
public class CsvRecord {

    @Id
    private String code;
    private String source;
    private String codeListCode;
    private String displayValue;
    private String longDescription;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer sortingPriority;

    // Getters  
    public String getCode() {
        return code;
    }

    public String getSource() {
        return source;
    }

    public String getCodeListCode() {
        return codeListCode;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public Integer getSortingPriority() {
        return sortingPriority;
    }

    // Setters  
    public void setCode(String code) {
        this.code = code;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setCodeListCode(String codeListCode) {
        this.codeListCode = codeListCode;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public void setSortingPriority(Integer sortingPriority) {
        this.sortingPriority = sortingPriority;
    }
}
