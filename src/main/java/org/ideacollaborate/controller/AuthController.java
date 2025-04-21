package org.ideacollaborate.controller;

import org.ideacollaborate.model.dto.AuthResponse;
import org.ideacollaborate.model.dto.LoginRequest;
import org.ideacollaborate.model.dto.SignupRequest;
import org.ideacollaborate.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = authenticationService.authenticate(loginRequest.getEmployeeId(), loginRequest.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest signupRequest) {
        String token = authenticationService.signup(
                signupRequest.getEmployeeId(),
                signupRequest.getName(),
                signupRequest.getEmail(),
                signupRequest.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));  // directly returning token on signup
    }
}