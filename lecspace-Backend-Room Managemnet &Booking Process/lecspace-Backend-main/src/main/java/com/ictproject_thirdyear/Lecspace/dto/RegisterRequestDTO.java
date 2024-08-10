package com.ictproject_thirdyear.Lecspace.dto;
import com.ictproject_thirdyear.Lecspace.entity.User.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class RegisterRequestDTO {
        private String userName;
        private String email;
        private String password;
        private Role role;
    }


