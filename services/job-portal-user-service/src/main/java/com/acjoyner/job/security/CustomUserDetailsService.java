package com.acjoyner.job.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acjoyner.job.model.User;
import com.acjoyner.job.repository.UserRepository;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().toString());
        Collection<GrantedAuthority> grantedAuthorities = Collections.singletonList(authority);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), 
        user.getPassword(), grantedAuthorities);
    }
}

   


