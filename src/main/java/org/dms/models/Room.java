package org.dms.models;

public record Room(Integer roomNumber) {

    @Override
    public Integer roomNumber() {
        return roomNumber;
    }

    @Override
    public String toString() {
        return STR."Room Number : \{roomNumber}";
    }
}
