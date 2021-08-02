package com.sg.classroster.service;

public class ClassRosterDataValidationException extends Exception {

    // message I am going to provide when I catch the exception.
    public ClassRosterDataValidationException(String message) {
        super(message);
    }

    public ClassRosterDataValidationException(String message,
                                              Throwable cause) {
        super(message, cause);
    }

}