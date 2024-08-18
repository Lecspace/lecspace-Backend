package com.lecspace.ictproject.dto;

import com.lecspace.ictproject.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;
    private String contact;

}