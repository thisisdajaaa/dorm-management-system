package org.dms.exceptions;

public class RoomException {
    public static class NotFoundException extends RuntimeException {
        public NotFoundException() {
            super("Room not found!");
        }

        public NotFoundException(String message) {
            super(message);
        }
    }
}
