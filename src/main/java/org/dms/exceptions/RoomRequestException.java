package org.dms.exceptions;

public class RoomRequestException {
    public static class NotFoundException extends RuntimeException{
        public NotFoundException(){super("No Room Request Found");}
        public NotFoundException(String message){super(message);}
    }
}
