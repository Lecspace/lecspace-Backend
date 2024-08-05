package com.ictproject_thirdyear.Lecspace.service;

import com.ictproject_thirdyear.Lecspace.dto.UserDTO;

import java.util.List;

public interface UserServe {
    UserDTO createUser(UserDTO userDTO,boolean isAdmin);

    UserDTO getUserById(long userId,boolean isAdmin);
    List<UserDTO> searchUsers(String name, String email, String role);
}
