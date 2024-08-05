
package com.ictproject_thirdyear.Lecspace.auth;
import com.ictproject_thirdyear.Lecspace.Security.JwtTokenProvider;
import com.ictproject_thirdyear.Lecspace.dto.LoginResponseDTO;
import com.ictproject_thirdyear.Lecspace.dto.RegisterRequestDTO;
import com.ictproject_thirdyear.Lecspace.dto.RegisterResponseDTO;
import com.ictproject_thirdyear.Lecspace.dto.loginRequestDTO;
import com.ictproject_thirdyear.Lecspace.entity.User;
import com.ictproject_thirdyear.Lecspace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegisterResponseDTO register(RegisterRequestDTO request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);

        RegisterResponseDTO response = new RegisterResponseDTO();
        response.setMessage("Registration successful");
        return response;
    }

    public LoginResponseDTO login(loginRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        String token = jwtTokenProvider.createToken(authentication);
        LoginResponseDTO response = new LoginResponseDTO();
        response.setAccessToken(token);
        return response;
    }

    public void logout(String token) {
        // Invalidate the token if you are using token blacklisting or other methods
    }
}
