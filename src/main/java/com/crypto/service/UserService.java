package com.crypto.service;

import com.crypto.model.User;
import com.crypto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@EnableScheduling

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public ResponseEntity<User> findById(String id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(String id) {
       this.userRepository.findById(id).ifPresent(userRepository::delete);
    }

    public void update(String id,User user) {
        this.userRepository.findById(id).ifPresent(userRepository::save);
    }
}