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

    public static class NoSecondaryKeyException extends  RuntimeException{
        public NoSecondaryKeyException(){super("No existing secondary key. Cannot create a new primary key!");}

        public NoSecondaryKeyException(String message){
            super(message);
        }
    }
}
