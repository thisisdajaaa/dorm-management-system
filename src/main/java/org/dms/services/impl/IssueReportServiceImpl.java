package org.dms.services.impl;

import org.dms.annotations.Autowired;
import org.dms.annotations.Component;
import org.dms.constants.ReportStatus;
import org.dms.exceptions.IssueReportException;
import org.dms.models.IssueReport;
import org.dms.repositories.spec.IIssueReportRepository;
import org.dms.services.spec.IIssueReportService;

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
}
