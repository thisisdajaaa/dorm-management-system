package org.dms.models;

import org.dms.annotations.AutoIncrement;
import org.dms.utils.ModelHelper;

import java.time.LocalDate;

public class RoomAssignment {
    @AutoIncrement
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Room room;
    public RoomAssignment(LocalDate startDate, LocalDate endDate) {
        ModelHelper.handleAutoIncrement(this);
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
