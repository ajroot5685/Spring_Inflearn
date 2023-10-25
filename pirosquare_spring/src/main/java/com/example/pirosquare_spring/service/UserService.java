package com.example.pirosquare_spring.service;

import com.example.pirosquare_spring.domain.User;
import com.example.pirosquare_spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long create(User user){
        userRepository.save(user);

        return user.getUid();
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public User findOne(Long userId){
        return userRepository.find(userId);
    }

    public List<User> findUsersWithPosts(int offset, int limit){
        return userRepository.findAllWithPosts(offset, limit);
    }
}
