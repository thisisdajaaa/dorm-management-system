package org.dms.models;

import org.dms.annotations.AutoIncrement;

import java.time.LocalDate;

public class IssueReport {
    @AutoIncrement
    private Integer id;
    private String description;
    private LocalDate reportDate;

    public IssueReport(Integer id, String description, LocalDate reportDate) {
        this.id = id;
        this.description = description;
        this.reportDate = reportDate;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }
}
