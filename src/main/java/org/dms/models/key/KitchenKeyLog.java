package org.dms.models.key;

import java.time.LocalDate;

public class KitchenKeyLog {
    private LocalDate borrowedStartDate;
    private LocalDate borrowedEndDate;

    public KitchenKeyLog(LocalDate borrowedStartDate, LocalDate borrowedEndDate) {
        this.borrowedStartDate = borrowedStartDate;
        this.borrowedEndDate = borrowedEndDate;
    }

    public LocalDate getBorrowedStartDate() {
        return borrowedStartDate;
    }

    public void setBorrowedStartDate(LocalDate borrowedStartDate) {
        this.borrowedStartDate = borrowedStartDate;
    }

    public LocalDate getBorrowedEndDate() {
        return borrowedEndDate;
    }

    public void setBorrowedEndDate(LocalDate borrowedEndDate) {
        this.borrowedEndDate = borrowedEndDate;
    }
}
