package com.epam.gymcrm.exceptions;

public class WrongCredentialsException extends Exception{
    public WrongCredentialsException(String errorMessage) {
        super(errorMessage);
    }
}
