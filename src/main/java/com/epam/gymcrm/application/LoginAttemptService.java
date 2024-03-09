package com.epam.gymcrm.application;

public interface LoginAttemptService {

    void loginFailed(final String key);

    boolean isBlocked();

}
