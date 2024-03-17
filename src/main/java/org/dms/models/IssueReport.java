package org.dms.models;

import org.dms.annotations.AutoIncrement;
import org.dms.utils.ModelHelper;

import java.time.LocalDate;

public class IssueReport {
    @AutoIncrement
    private Integer id;
    private String description;
    private LocalDate reportDate;

    public IssueReport(String description, LocalDate reportDate) {
        ModelHelper.handleAutoIncrement(this);
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
