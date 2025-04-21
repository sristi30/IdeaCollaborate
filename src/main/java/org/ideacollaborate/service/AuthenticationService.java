package org.ideacollaborate.service;

import org.ideacollaborate.model.entity.Employee;
import org.ideacollaborate.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthenticationService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationService(EmployeeRepository employeeRepository,
                                 PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // Validate employee credentials and generate an OAuth token.
    public String authenticate(String employeeId, String password) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found"));

        if (!passwordEncoder.matches(password, employee.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate an OAuth token after successful authentication
        return jwtService.generateToken(employeeId);
    }

    public String signup(String employeeId, String name, String email, String password) {
        if (employeeRepository.findByEmployeeId(employeeId).isPresent()) {
            throw new RuntimeException("Employee already registered with ID: " + employeeId);
        }

        Employee employee = new Employee(employeeId, name, email, passwordEncoder.encode(password), LocalDateTime.now());
        employeeRepository.save(employee);
        return jwtService.generateToken(employeeId);  // generate token right after signup (optional, but recommended)
    }
}

