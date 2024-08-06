package com.COWORK.COWORKING.exceptions;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(String message) {
        super(message);
    }
}
