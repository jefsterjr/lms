package org.arcone.lmsapi.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arcone.lmsapi.auth.model.UserDetailsImpl;
import org.arcone.lmsapi.auth.model.dto.JWTResponseDTO;
import org.arcone.lmsapi.auth.model.dto.LoginDTO;
import org.arcone.lmsapi.auth.model.dto.MessageResponse;
import org.arcone.lmsapi.auth.model.dto.SignupDTO;
import org.arcone.lmsapi.user.service.UserService;
import org.arcone.lmsapi.util.config.security.JWTUtils;
import org.arcone.lmsapi.util.exceptions.ExistingEmailException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JWTUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO dto) throws JsonProcessingException {
        log.info("Authenticating user, {}", dto.username());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JWTResponseDTO(jwt,
                userDetails.id(),
                userDetails.getUsername(),
                userDetails.email(),
                userDetails.role()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupDTO signUpRequest) throws ExistingEmailException {
        log.info("Registering new user, {}", signUpRequest.email());
        if (userService.checkUserName(signUpRequest.email())) {
            throw new ExistingEmailException();
        }
        userService.signup(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
