package org.dms.models;

import org.dms.annotations.AutoIncrement;
import org.dms.constants.RequestType;
import org.dms.utils.ModelUtil;
import java.time.LocalDate;

public class RoomRequest {
    @AutoIncrement
    private Integer id;
    private LocalDate requestDate;
    private boolean isResolved;

    private RequestType requestType;

    private RoomAssignment roomAssignment;

    public RoomRequest(LocalDate requestDate, RoomAssignment roomAssignment,
                       RequestType requestType)
    {
        ModelUtil.handleAutoIncrement(this);
        this.requestDate = requestDate;
        isResolved = false;
        this.roomAssignment = roomAssignment;
        this.requestType = requestType;
    }

    public Integer getId() {
        return id;
    }
    public LocalDate getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }
    public boolean isResolved() {
        return isResolved;
    }
    public void setResolved(boolean resolved) {
        isResolved = resolved;
    }

    @Override
    public String toString() {
        return STR."""
                Change Request Date: \{requestDate}
                Has this request been resolved: \{isResolved}
                """;
    }

    public RoomAssignment getRoomAssignment() {
        return roomAssignment;
    }

    public RequestType getRequestType() {
        return requestType;
    }
}
