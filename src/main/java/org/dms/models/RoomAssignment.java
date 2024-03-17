package org.dms.models;

import org.dms.annotations.AutoIncrement;

import java.time.LocalDate;

public class RoomAssignment {
    @AutoIncrement
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Room room;

    public RoomAssignment(Integer id, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
