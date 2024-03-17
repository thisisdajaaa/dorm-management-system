package org.dms.services.impl;

import org.dms.annotations.Autowired;
import org.dms.annotations.Component;
import org.dms.constants.ReportStatus;
import org.dms.exceptions.IssueReportException;
import org.dms.models.IssueReport;
import org.dms.repositories.spec.IIssueReportRepository;
import org.dms.services.spec.IIssueReportService;

@Component
public class IssueReportServiceImpl implements IIssueReportService {

    @Autowired
    private IIssueReportRepository issueReport;

    @Override
    public void acknowledgeIssueReport(int reportID){
        IssueReport foundIssueReport = issueReport.findById(reportID).orElseThrow(IssueReportException.NotFoundException::new);
        foundIssueReport.setReportStatus(ReportStatus.IN_PROGRESS);
        issueReport.save(foundIssueReport);
    }
}
