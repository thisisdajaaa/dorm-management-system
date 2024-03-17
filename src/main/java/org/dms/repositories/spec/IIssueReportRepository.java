package org.dms.repositories.spec;

import org.dms.models.IssueReport;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IIssueReportRepository {

    public void save(IssueReport issueReport);
    public Optional<IssueReport> findById(Integer id);
    List<Map.Entry<Integer, IssueReport>> findAll();
}
