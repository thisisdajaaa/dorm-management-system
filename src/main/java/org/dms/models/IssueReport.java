package org.dms.models;

import org.dms.annotations.AutoIncrement;
import org.dms.constants.ReportStatus;
import org.dms.constants.Severity;
import org.dms.utils.ModelUtil;
import java.time.LocalDate;

import static java.lang.StringTemplate.STR;

public class IssueReport {
    @AutoIncrement
    private Integer id;
    private String description;
    private LocalDate reportDate;
    private ReportStatus reportStatus;
    private Severity severity;
    private  RoomAssignment roomAssignment;

    public IssueReport(String description, LocalDate reportDate, Severity severity, RoomAssignment roomAssignment) {
        ModelUtil.handleAutoIncrement(this);
        this.description = description;
        this.reportDate = reportDate;
        this.severity = severity;
        this.roomAssignment = roomAssignment;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public RoomAssignment getRoomAssignment() {
        return roomAssignment;
    }

    public void setRoomAssignment(RoomAssignment roomAssignment) {
        this.roomAssignment = roomAssignment;
    }

    @Override
    public String toString() {
        return STR."""
                Issue Report
                Description : \{description}
                Date : \{reportDate.toString()}
                Status: \{reportStatus}
                Severity: \{severity}
                Room Number : \{roomAssignment.getRoom().getRoomNumber()}
                """;
    }
}
