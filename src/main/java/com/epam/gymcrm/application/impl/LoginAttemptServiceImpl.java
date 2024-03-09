package com.epam.gymcrm.application.impl;


import com.epam.gymcrm.application.LoginAttemptService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


@Service
public class LoginAttemptServiceImpl implements LoginAttemptService {

    public static final int MAX_ATTEMP = 3;

    private LoadingCache<String, Integer> attemptsCache;

    @Autowired
    private HttpServletRequest request;

    public LoginAttemptServiceImpl(){
        super();
        attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
            @Override
            public Integer load(final String key) throws Exception {
                return 0;
            }
        });
    }

    @Override
    public void loginFailed(final String key){
        int attempts;

        try {
            attempts = attemptsCache.get(key);
        } catch (final ExecutionException e){
            attempts = 0;
        }

        attempts++;
        attemptsCache.put(key, attempts);

    }

    @Override
    public boolean isBlocked(){
        try {
            return attemptsCache.get(getClientIP()) >= MAX_ATTEMP;
        } catch (final ExecutionException e){
            return false;
        }
    }

    private String getClientIP(){
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if(xfHeader != null){
            return xfHeader.split(",")[0];
        }

        return request.getRemoteAddr();
    }



}
