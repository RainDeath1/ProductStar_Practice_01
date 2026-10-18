
package com.example.libraryauth;

import com.example.libraryauth.entity.User;
import com.example.libraryauth.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SecurityIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void cleanDatabase() {
        userRepository.deleteAll();
    }

    private String basicAuth(String username, String password) {
        String credentials = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(
                credentials.getBytes(StandardCharsets.UTF_8)
        );
    }

    @Test
    void anonymousUserCannotAccessProtectedEndpoints()
            throws Exception {

        mockMvc.perform(get("/user/hello"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/admin/hello"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void registrationCreatesUserWithBCryptPassword()
            throws Exception {

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                      "username": "reader",
                      "password": "12345678"
                    }
                    """))
                .andExpect(status().isCreated());

        User user = userRepository.findByUsername("reader")
                .orElseThrow();

        assertEquals("ROLE_USER", user.getRole());
        assertNotEquals("12345678", user.getPassword());
        assertTrue(passwordEncoder.matches(
                "12345678", user.getPassword()
        ));
    }

    @Test
    void duplicateRegistrationReturnsBadRequest()
            throws Exception {

        String json = """
                {
                  "username": "reader",
                  "password": "12345678"
                }
                """;

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void userCanAccessUserButNotAdmin()
            throws Exception {

        userRepository.save(new User(
                "reader",
                passwordEncoder.encode("12345678"),
                "ROLE_USER"
        ));

        String auth = basicAuth("reader", "12345678");

        mockMvc.perform(get("/user/hello")
                        .header("Authorization", auth))
                .andExpect(status().isOk());

        mockMvc.perform(get("/admin/hello")
                        .header("Authorization", auth))
                .andExpect(status().isForbidden());
    }

    @Test
    void adminCanAccessBothEndpoints()
            throws Exception {

        userRepository.save(new User(
                "librarian",
                passwordEncoder.encode("adminTest123"),
                "ROLE_ADMIN"
        ));

        String auth = basicAuth("librarian", "adminTest123");

        mockMvc.perform(get("/admin/hello")
                        .header("Authorization", auth))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user/hello")
                        .header("Authorization", auth))
                .andExpect(status().isOk());
    }
}
