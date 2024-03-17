package org.dms.models;

import org.dms.annotations.AutoIncrement;
import org.dms.utils.ModelHelper;
import java.time.LocalDate;

public class KitchenKeyLog {
    @AutoIncrement
    private Integer id;
    private LocalDate borrowedStartDate;
    private LocalDate borrowedEndDate;
    private Key key;
    private Person person;

    public KitchenKeyLog(LocalDate borrowedStartDate, LocalDate borrowedEndDate, Key key, Person person) {
        ModelHelper.handleAutoIncrement(this);
        this.borrowedStartDate = borrowedStartDate;
        this.borrowedEndDate = borrowedEndDate;
        this.key = key;
        this.person = person;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getId() {
        return id;
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
