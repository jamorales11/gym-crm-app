package com.epam.gymcrm.domain.exceptions;

public class WrongCredentialsException extends Exception{
    public WrongCredentialsException(String errorMessage) {
        super(errorMessage);
    }
}
