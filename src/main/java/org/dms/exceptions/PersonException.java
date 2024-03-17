package org.dms.exceptions;

public class PersonException {
    public static class NotFoundException extends RuntimeException {
        public NotFoundException() {
            super("Key not found!");
        }

        public NotFoundException(String message) {
            super(message);
        }
    }
}
