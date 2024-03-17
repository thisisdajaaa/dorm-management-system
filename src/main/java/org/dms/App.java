package org.dms;

import org.dms.configs.Injector;
import org.dms.configs.Seeder;
import org.dms.constants.ReportStatus;
import org.dms.models.IssueReport;
import org.dms.services.spec.IIssueReportService;
import org.dms.services.spec.IKeyService;
import org.dms.services.spec.IKitchenKeyLogService;
import org.dms.services.spec.IPersonService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.lang.StringTemplate.STR;

public class App {
    public static void main(String[] args) {
        Injector.startApplication(App.class);

        // Inject the seeder and run it
        Seeder seeder = Injector.getService(Seeder.class);
        seeder.run();

        // Utilize the key service
        IPersonService personService = Injector.getService(IPersonService.class);
        IKeyService keyService = Injector.getService(IKeyService.class);
        IKitchenKeyLogService kitchenKeyLogService = Injector.getService(IKitchenKeyLogService.class);
        IIssueReportService issueReportService = Injector.getService(IIssueReportService.class);

        // Example usage
        // System.out.println(keyService.findAll());
        // System.out.println(personService.findAll());
        // System.out.println(personService.findById(1).getEmail());

        // Example usage of adding kitchen key log
        // kitchenKeyLogService.addKitchenKeyLog(LocalDate.now(),4, 3);

        //Example usage of setting of Acknowledging an Issue Report
//        IssueReport issueReport = new IssueReport("My sink is clogging", LocalDate.now());
//        issueReportService.save(issueReport);
//        System.out.println("OUTPUT IS HEREEEEEEEEEEEEEE!!!!!!!!!=====================================");
//        List<Map.Entry<Integer, IssueReport>> listOfIssuesReport = issueReportService.findAll();
//        for (Map.Entry<Integer, IssueReport> listofIssuesReported : listOfIssuesReport) {
//            System.out.println(STR. "Description: \{ listofIssuesReported.getValue().getDescription() } Date: \{ listofIssuesReported.getValue().getReportDate() } Report Status: \{ listofIssuesReported.getValue().getReportStatus() }" );
//        }
//        issueReport.setReportStatus(ReportStatus.IN_PROGRESS);
//        List<Map.Entry<Integer, IssueReport>> listOfIssuesReport2 = issueReportService.findAll();
//        for (Map.Entry<Integer, IssueReport> listofIssuesReported : listOfIssuesReport2) {
//            System.out.println(STR. "Description: \{ listofIssuesReported.getValue().getDescription() } Date: \{ listofIssuesReported.getValue().getReportDate() } Report Status: \{ listofIssuesReported.getValue().getReportStatus() }" );
//        }
    }
}
