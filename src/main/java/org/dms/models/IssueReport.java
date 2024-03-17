package org.dms.models;

import org.dms.annotations.AutoIncrement;
import org.dms.constants.ReportStatus;
import org.dms.utils.ModelUtil;

import java.time.LocalDate;

public class IssueReport {
    @AutoIncrement
    private Integer id;
    private String description;
    private LocalDate reportDate;
    private ReportStatus reportStatus;

    public IssueReport(String description, LocalDate reportDate) {
        ModelUtil.handleAutoIncrement(this);
        this.description = description;
        this.reportDate = reportDate;
        reportStatus = ReportStatus.OPEN;
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

    public ReportStatus getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(ReportStatus reportStatus) {
        this.reportStatus = reportStatus;
    }
}
