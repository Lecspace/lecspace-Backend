package com.ictproject_thirdyear.Lecspace.controller;
import com.ictproject_thirdyear.Lecspace.auth.AuthenticationService;
import com.ictproject_thirdyear.Lecspace.dto.RegisterRequestDTO;
import com.ictproject_thirdyear.Lecspace.dto.RegisterResponseDTO;
import com.ictproject_thirdyear.Lecspace.dto.loginRequestDTO;
import com.ictproject_thirdyear.Lecspace.dto.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        RegisterResponseDTO response = authenticationService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody loginRequestDTO request) {
        LoginResponseDTO response = authenticationService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        authenticationService.logout(token);
        return ResponseEntity.ok("Logged out successfully");
    }
}
