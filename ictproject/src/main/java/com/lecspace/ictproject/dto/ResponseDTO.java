package com.lecspace.ictproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO <T>{
    private boolean success;
    private T data;
    private String message;
}