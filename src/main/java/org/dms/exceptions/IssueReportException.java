package org.dms.exceptions;

public class IssueReportException {

    public static class NotFoundException extends RuntimeException{
        public NotFoundException(){super("Issue Report Not Found");}
        public NotFoundException(String message){super(message);}

    }
}
