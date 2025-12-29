/*package com.bankapp.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bankapp.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        // TEMPORARY HARD-CODED USER (for learning)
        if ("shruti".equals(request.getUsername())
                && "password123".equals(request.getPassword())) {

            String token = jwtUtil.generateToken(request.getUsername());

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("role", "ROLE_USER");

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401)
                .body("Invalid username or password");
    }
}*/

package com.bankapp.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bankapp.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        String username = request.getUsername();
        String password = request.getPassword();

        String role = null;

        // TEMP USERS (for learning)
        if ("shruti".equals(username) &&
            "password123".equals(password)) {

            role = "USER";

        } else if ("admin".equals(username) &&
                   "admin123".equals(password)) {

            role = "ADMIN";
        }

        if (role != null) {

            String token = jwtUtil.generateToken(username, role);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("role", role);

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401)
                .body("Invalid username or password");
    }
}

