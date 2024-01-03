package com.epam.gymcrm.application;

public interface UserService {

    String calculateUsername(String firstName, String lastName);

    String generatePassword();


}
