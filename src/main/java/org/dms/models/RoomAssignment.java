package org.dms.models;

import org.dms.annotations.AutoIncrement;
import org.dms.utils.ModelUtil;

import java.time.LocalDate;

public class RoomAssignment {
    @AutoIncrement
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Room room;

    public RoomAssignment(LocalDate startDate, LocalDate endDate) {
        ModelUtil.handleAutoIncrement(this);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return STR."""
                Start Date: \{startDate}
                End Date : \{endDate}
                Room: \{room.roomNumber()}
                """;
    }
}
