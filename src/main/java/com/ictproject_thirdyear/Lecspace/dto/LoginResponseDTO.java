package com.ictproject_thirdyear.Lecspace.dto;
import lombok.Data;

    @Data
    public class LoginResponseDTO
    {
        private String accessToken;
        private String refreshToken;
    }

