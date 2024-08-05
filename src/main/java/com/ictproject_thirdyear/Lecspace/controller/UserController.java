//Handles user-related operations such as registration, login, logout, and profile management.

/**package com.ictproject_thirdyear.Lecspace.controller;

import com.ictproject_thirdyear.Lecspace.dto.UserDTO;
import com.ictproject_thirdyear.Lecspace.service.UserServe;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServe userServe;

    //Build add user REST API
    @PostMapping
    public ResponseEntity<UserDTO> createUser (@RequestBody UserDTO userDTO)
    {
        // Assume only admins can create users
        UserDTO savedUser = userServe.createUser(userDTO,true);
        return  new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    //Build get user REST API
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id")long userId)
    {
        UserDTO userDTO = userServe.getUserById(userId,false); // Assume retrieving user without password
        return  ResponseEntity.ok(userDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(@RequestParam(value = "name", required = false) String name,
                                                     @RequestParam(value = "email", required = false) String email,
                                                     @RequestParam(value = "role", required = false) String role) {
        List<UserDTO> users = userServe.searchUsers(name, email, role);
        return ResponseEntity.ok(users);
    }

    // Get users by role
    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserDTO>> getUsersByRole(@PathVariable("role") String role) {
        List<UserDTO> users = userServe.searchUsers(null, null, role);
        return ResponseEntity.ok(users);
    }
}**/

