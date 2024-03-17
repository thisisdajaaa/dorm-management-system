package org.dms.models;

import org.dms.constants.RoomStatus;

public record Room(Integer roomNumber) {
    private static RoomStatus status = RoomStatus.AVAILABLE;
    public RoomStatus getStatus() {
        return status;
    }
    public void setStatus(RoomStatus status) {
        this.status = status;
    }
}
