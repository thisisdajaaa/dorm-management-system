package org.dms.models;

import org.dms.annotations.AutoIncrement;
import org.dms.utils.ModelUtil;
import java.time.LocalDate;

public class RoomRequest {
    @AutoIncrement
    private Integer id;
    private LocalDate requestDate;
    private boolean isResolved;

    public RoomRequest(LocalDate requestDate)
    {
        ModelUtil.handleAutoIncrement(this);
        this.requestDate = requestDate;
        isResolved = false;
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
}
