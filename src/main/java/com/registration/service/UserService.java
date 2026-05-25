package com.registration.service;

import com.registration.model.User;
import com.registration.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Persists a new user.
     * Throws {@link IllegalArgumentException} if the email is already registered.
     */
    @Transactional
    public User register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException(
                "Email already registered: " + user.getEmail()
            );
        }
        return userRepository.save(user);
    }
}
