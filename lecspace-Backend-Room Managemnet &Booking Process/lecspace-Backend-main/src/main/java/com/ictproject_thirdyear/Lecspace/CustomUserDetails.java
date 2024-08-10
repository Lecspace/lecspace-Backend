package com.ictproject_thirdyear.Lecspace;


import com.ictproject_thirdyear.Lecspace.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public abstract class CustomUserDetails implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

}
