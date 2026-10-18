
package com.example.libraryauth.service;

import com.example.libraryauth.entity.User;
import com.example.libraryauth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(String username, String password) {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException(
                    "Логин не может быть пустым");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException(
                    "Пароль не может быть пустым");
        }

        username = username.trim();

        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException(
                    "Пользователь уже существует");
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(
                username,
                encodedPassword,
                "ROLE_USER"
        );

        userRepository.save(user);
    }
}
