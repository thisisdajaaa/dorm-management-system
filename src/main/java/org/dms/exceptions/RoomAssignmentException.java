package org.dms.exceptions;

public class RoomAssignmentException extends RuntimeException{
    public static class NotFoundException extends RuntimeException {
        public NotFoundException() {
            super("Room Assignment not possible!");
        }

        public NotFoundException(String message) {
            super(message);
        }
    }
}
