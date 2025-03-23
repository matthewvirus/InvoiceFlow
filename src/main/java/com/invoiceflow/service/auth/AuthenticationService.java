package com.invoiceflow.service.auth;

import com.invoiceflow.dto.user.UserCreateDTO;
import com.invoiceflow.model.User;
import com.invoiceflow.repository.UserRepository;
import com.invoiceflow.dto.auth.AuthResponse;
import com.invoiceflow.dto.auth.AuthRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(UserCreateDTO request) {
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            log.error("User with email {} is already exists!", request.getEmail());
            throw new RuntimeException("User is exists!");
        });
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);
        log.info("User {} created successfully!", user.getEmail());
        return new AuthResponse(generateToken(user));
    }

    public AuthResponse authenticate(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    log.error("User {} not found.", request.getEmail());
                    return new RuntimeException("User not found.");
                });
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.error("Password for user {} incorrect!", user.getEmail());
            throw new RuntimeException("Invalid credentials.");
        }
        log.info("User {} authenticated successfully!", user.getEmail());
        return new AuthResponse(generateToken(user));
    }

    private String generateToken(User user) {
        return jwtService.generateToken(Map.of("role", user.getRole()), user.getEmail());
    }
}