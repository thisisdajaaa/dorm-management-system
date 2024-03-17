package org.dms.services.spec;

public interface IIssueReportService {
    //This method is used when Admin acknowledges the Issue Report
    //by marking the report status as IN-PROGRESS
    public void acknowledgeIssueReport(int reportID);
}
