package org.dms.exceptions;

public class PersonException {
    public static class NotFoundException extends RuntimeException {
        public NotFoundException() {
            super("Person not found!");
        }

        public NotFoundException(String message) {
            super(message);
        }
    }

    public static class AlreadyExistsException extends RuntimeException {
        public AlreadyExistsException() {
            super("Person already exists!");
        }

        public AlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class BadRequestException extends RuntimeException {
        public BadRequestException() {
            super("Request body is invalid!");
        }

        public BadRequestException(String message) {
            super(message);
        }
    }
}
