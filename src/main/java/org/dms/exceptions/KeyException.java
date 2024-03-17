package org.dms.exceptions;

public class KeyException {
    public static class NotFoundException extends RuntimeException {
        public NotFoundException() {
            super("Key not found!");
        }

        public NotFoundException(String message) {
            super(message);
        }
    }

    public static class PrimaryException extends RuntimeException {
        public PrimaryException() {
            super("Assigned key must be a primary key!");
        }

        public PrimaryException(String message) {
            super(message);
        }
    }
}
