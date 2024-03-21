package org.dms.models;
import org.dms.constants.RoomStatus;
import static java.lang.StringTemplate.STR;


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

    public Integer getRoomNumber() {
        return this.roomNumber;
    }
    @Override
    public String toString() {
        return STR. """
           Room Number : \{ roomNumber }
           Room Status : \{ status }
           ---------------""" ;
    }
}
