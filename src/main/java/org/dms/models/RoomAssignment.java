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
    private Person person;

    public RoomAssignment(LocalDate startDate, LocalDate endDate) {
        ModelUtil.handleAutoIncrement(this);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public void assignRoomToPerson(Room room, Person person){
        this.room = room;
        this.person = person;
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
}
