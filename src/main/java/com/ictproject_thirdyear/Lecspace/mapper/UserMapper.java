package com.ictproject_thirdyear.Lecspace.mapper;
import com.ictproject_thirdyear.Lecspace.dto.UserDTO;
import com.ictproject_thirdyear.Lecspace.entity.User;
import com.ictproject_thirdyear.Lecspace.dto.UserDTO;
import com.ictproject_thirdyear.Lecspace.entity.User;

public class UserMapper {
    //public UserMapper() {
    //}

    public static User maptoUser(UserDTO userDTO)
    {
        User user = new User();
        user.setName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        return user;
    }

    public static UserDTO maptoUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(user.getName());
        userDTO.setEmail(user.getEmail());
        //userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}

