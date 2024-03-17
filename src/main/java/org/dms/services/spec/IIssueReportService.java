package org.dms.services.spec;

import org.dms.annotations.Component;
import org.dms.models.IssueReport;

import java.util.List;
import java.util.Map;

public interface IIssueReportService {
    //This method is used when Admin acknowledges the Issue Report
    //by marking the report status as IN-PROGRESS
    public void acknowledgeIssueReport(int reportID);
    public IssueReport findById(int reportID);
    public void save(IssueReport report);
    public List<Map.Entry<Integer, IssueReport>> findAll();
}
