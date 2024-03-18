package org.dms.repositories.impl;
import org.dms.annotations.Component;
import org.dms.models.IssueReport;
import org.dms.repositories.spec.IIssueReportRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class IssueReportImpl implements IIssueReportRepository {
    private final static Map<Integer, IssueReport> issueReports = new HashMap<>();
    @Override
    public void save(IssueReport issueReport) {
        issueReports.put(issueReport.getId(), issueReport);
    }

    @Override
    public Optional<IssueReport> findById(Integer id) {
        return Optional.ofNullable(issueReports.get(id));
    }

    @Override
    public List<Map.Entry<Integer, IssueReport>> findAll() {
        return issueReports.entrySet().stream().toList();
    }
}
