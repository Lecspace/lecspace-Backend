package com.lecspace.ictproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequestDTO {
    private int id;
    private String password;
    private String oldPassword;
}