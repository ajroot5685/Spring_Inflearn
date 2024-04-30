package com.example.testsecurity.service;

import com.example.testsecurity.dto.CustomUserDetails;
import com.example.testsecurity.entity.User;
import com.example.testsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Inmemory 방식으로 회원을 관리하려면 db로부터 접근하는 UserDetailsService 로직이 빈으로 등록되어 있으면 안된다.
 */
//@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userData = userRepository.findByUsername(username);

        if (userData != null) {

            return new CustomUserDetails(userData);
        }

        return null;
    }
}
