package org.dms.services.impl;

import org.dms.annotations.Autowired;
import org.dms.annotations.Component;
import org.dms.constants.ReportStatus;
import org.dms.constants.Severity;
import org.dms.exceptions.IssueReportException;
import org.dms.models.IssueReport;
import org.dms.models.RoomAssignment;
import org.dms.repositories.spec.IIssueReportRepository;
import org.dms.services.spec.IIssueReportService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class IssueReportServiceImpl implements IIssueReportService {

    @Autowired
    private IIssueReportRepository issueReport;

    @Override
    public void acknowledgeIssueReport(int reportID){
        IssueReport foundIssueReport = findById(reportID);
        foundIssueReport.setReportStatus(ReportStatus.IN_PROGRESS);
        save(foundIssueReport);
    }

    @Override
    public IssueReport findById(int reportID)
    {
        return issueReport.findById(reportID).orElseThrow(IssueReportException.NotFoundException::new);
    }

    @Override
    public void save(IssueReport report) {
        issueReport.save(report);
    }

    @Override
    public List<Map.Entry<Integer, IssueReport>> findAll() {
        return issueReport.findAll();
    }

    @Override
    public void addIssueReport(String description, LocalDate reportDate, Severity severity, RoomAssignment roomAssignment) {
        save(new IssueReport(description, reportDate, severity,roomAssignment));
    }
}
