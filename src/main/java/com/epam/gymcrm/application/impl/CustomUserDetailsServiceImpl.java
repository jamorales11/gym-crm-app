package com.epam.gymcrm.application.impl;

import com.epam.gymcrm.application.LoginAttemptService;
import com.epam.gymcrm.domain.repository.UserRepository;
import com.epam.gymcrm.infrastructure.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service("userDetailsService")
@Transactional
@Slf4j
public class CustomUserDetailsServiceImpl implements UserDetailsService {


    private UserRepository userRepository;

    private LoginAttemptService loginAttemptService;

    public CustomUserDetailsServiceImpl(UserRepository userRepository, LoginAttemptService loginAttemptService) {
        this.userRepository = userRepository;
        this.loginAttemptService = loginAttemptService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (loginAttemptService.isBlocked()) {
            log.info("3 failed login attempts. User "+ username + " has been blocked for 5 minutes");
            throw new RuntimeException("blocked");
        }

        try {

            UserEntity userEntity = userRepository.findByUsername(username)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("User not found with username: "+ username));


            Set<GrantedAuthority> authorities = userEntity
                    .getRoles()
                    .stream()
                    .map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());



            return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.getIsActive(),
                    true, true, true,authorities);

        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }

}
