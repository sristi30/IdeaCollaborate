package org.ideacollaborate.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.ideacollaborate.model.entity.Employee;

import java.util.Date;

public class TokenUtil {

    private static final String SECRET_KEY = "yourSecretKey"; // Use a more secure key in production
    private static final long EXPIRATION_TIME = 3600000L; // 1 hour expiration

    public static String extractEmployeeIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();  // assuming employeeId is in the `sub` (subject) field
    }

    public static String generateToken(Employee employee) {
        return Jwts.builder()
                .setSubject(employee.getEmployeeId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // Parse the token and extract the employee ID
    public static String getEmployeeIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // Validate the token
    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
