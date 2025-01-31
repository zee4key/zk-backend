package kz.zee4key.coding.auth.controller;

import kz.zee4key.coding.auth.dto.UserDTO;
import kz.zee4key.coding.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getCurrentUser(Authentication authentication) {
        UserDTO user = userService.getCurrentUser(authentication.getName());
        return ResponseEntity.ok(user);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserDTO> updateProfile(@RequestBody UserDTO userDTO, Authentication authentication) {
        UserDTO updatedUser = userService.updateUser(authentication.getName(), userDTO);
        return ResponseEntity.ok(updatedUser);
    }
}