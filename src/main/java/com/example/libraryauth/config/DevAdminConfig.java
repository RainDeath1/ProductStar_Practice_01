
package com.example.libraryauth.config;

import com.example.libraryauth.entity.User;
import com.example.libraryauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class DevAdminConfig {

    @Bean
    public CommandLineRunner createAdmin(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            @Value("${demo.admin.password}") String password) {

        return args -> {
            if (!userRepository.existsByUsername("librarian")) {

                User admin = new User(
                        "librarian",
                        passwordEncoder.encode(password),
                        "ROLE_ADMIN"
                );

                userRepository.save(admin);
            }
        };
    }
}
