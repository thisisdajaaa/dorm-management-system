package org.dms.models;

import org.dms.constants.RoomStatus;

import java.util.Objects;
import java.util.Optional;

public class Room {
    private Integer roomNumber;
    private RoomStatus status;
    public Room(Integer roomNumber) {
        this.roomNumber = roomNumber;
        status = RoomStatus.AVAILABLE;
    }
    public RoomStatus getStatus() {
        return status;
    }
    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public Integer getRoomNumber(){
        return this.roomNumber;
    }
}
