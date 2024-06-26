package org.dms.models;

import org.dms.annotations.AutoIncrement;
import org.dms.utils.ModelUtil;

import java.time.LocalDate;
import java.util.List;

import static java.lang.StringTemplate.STR;

public class RoomAssignment {
    @AutoIncrement
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Room room;
    private Person person;
    List<RoomRequest> roomRequests;

    public RoomAssignment(LocalDate startDate, LocalDate endDate, Person person, Room room) {
        ModelUtil.handleAutoIncrement(this);
        this.startDate = startDate;
        this.endDate = endDate;
        this.person = person;
        this.room = room;
    }
    public Integer getId() {
        return id;
    }

    public Room getRoom() {
        return this.room;
    }

    public Person getPerson() {
        return this.person;
    }

    @Override
    public String toString() {
        return STR."""
                Start Date: \{startDate}
                End Date : \{endDate}
                Room: \{room.getRoomNumber()}
                Name: \{this.person.getName()}
                ---------------""";
    }
}
