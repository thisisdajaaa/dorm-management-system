package org.dms.exceptions;

public class AuthenticationException {
    public static class NotAllowedException extends RuntimeException {
        public NotAllowedException() {
            super("Authentication not allowed!");
        }

        public NotAllowedException(String message) {
            super(message);
        }
    }
}
