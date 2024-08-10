package com.ictproject_thirdyear.Lecspace.dto;
import com.ictproject_thirdyear.Lecspace.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long userId;
    private String email;
    private String userName;
    private String password;
    private User.Role role;

}