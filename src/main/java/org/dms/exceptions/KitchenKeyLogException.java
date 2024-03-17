package org.dms.exceptions;

public class KitchenKeyLogException {
    public static class NotFoundException extends RuntimeException {
        public NotFoundException() {
            super("Kitchen key log not found!");
        }

        public NotFoundException(String message) {
            super(message);
        }
    }

    public static class NotAllowedException extends RuntimeException {
        public NotAllowedException() {
            super("Person must be a student!");
        }

        public NotAllowedException(String message) {
            super(message);
        }
    }
}
