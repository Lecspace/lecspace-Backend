/**package com.ictproject_thirdyear.Lecspace.service.impl;

import com.ictproject_thirdyear.Lecspace.dto.UserDTO;
import com.ictproject_thirdyear.Lecspace.entity.User;
import com.ictproject_thirdyear.Lecspace.exception.ResourceNotFoundException;
import com.ictproject_thirdyear.Lecspace.mapper.UserMapper;
import com.ictproject_thirdyear.Lecspace.repository.UserRepository;
import com.ictproject_thirdyear.Lecspace.service.UserServe;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServeImpl implements UserServe {

@Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO,boolean isAdmin) {
        User user = UserMapper.maptoUser(userDTO);
        if (isAdmin) {
            // Only allow setting password during creation for admins
            user.setPassword(userDTO.getPassword());
        }
        User savedUser = userRepository.save(user);
        return UserMapper.maptoUserDTO(savedUser);


    }

    @Override
    public UserDTO getUserById(long userId,boolean isAdmin) {
    User user =userRepository.findById(userId)
    .orElseThrow(() ->
    new ResourceNotFoundException("Employee is not exist with the given ID:"+ userId));

        if (!isAdmin) {
            // If not admin, don't expose password
            user.setPassword(null);
        }
    return UserMapper.maptoUserDTO((user));
    }



@Override

public List<UserDTO> searchUsers(String name, String email, String role) {
    List<User> users = userRepository.findAll();

    if (name != null && !name.isEmpty()) {
        users = users.stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    if (email != null && !email.isEmpty()) {
        users = users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList());
    }

    if (role != null && !role.isEmpty()) {
        users = users.stream()
                .filter(user -> user.getRole().name().equalsIgnoreCase(role))
                .collect(Collectors.toList());
    }

    return users.stream()
            .map(UserMapper::maptoUserDTO)
            .collect(Collectors.toList());
}
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}**/
