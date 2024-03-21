package org.dms.models;

import org.dms.annotations.AutoIncrement;
import org.dms.utils.ModelUtil;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Optional;

public class KitchenKeyLog {
    @AutoIncrement
    private Integer id;
    private LocalDate borrowedStartDate;
    private LocalDate borrowedEndDate;
    private Key key;
    private Person person;

    public KitchenKeyLog(LocalDate borrowedStartDate, Key key, Person person) {
        ModelUtil.handleAutoIncrement(this);
        this.borrowedStartDate = borrowedStartDate;
        this.key = key;
        this.person = person;
        this.borrowedEndDate = null;
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

    @Override
    public String toString() {
        String borrowedEndDateStatus = "";
        Optional<LocalDate> borrowedEndDate = Optional.ofNullable(getBorrowedEndDate());

        if(borrowedEndDate.isEmpty())
            borrowedEndDateStatus = "Key is still currently being borrowed";
        else
            borrowedEndDateStatus = borrowedEndDate.toString()
                    ;
        return STR."""
                Person Borrowing : \{person.getName()}
                Key Status : \{key.getKeyStatus()}
                Borrowed Date: \{borrowedStartDate}
                Borrowed End Date : \{borrowedEndDateStatus}
                """;
    }
}
