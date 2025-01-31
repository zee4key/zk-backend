package kz.zee4key.coding.auth.service;

import kz.zee4key.coding.auth.dto.UserDTO;
import kz.zee4key.coding.auth.model.User;
import kz.zee4key.coding.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public UserDTO getCurrentUser(String username) {
        User user = userRepository.findByUsername(username);
        return convertToDTO(user);
    }

    public UserDTO updateUser(String username, UserDTO userDTO) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setEmail(userDTO.getEmail());
            // Don't update sensitive fields like password here
            user = userRepository.save(user);
            return convertToDTO(user);
        }
        throw new RuntimeException("User not found");
    }

    private UserDTO convertToDTO(User user) {
        if (user == null) return null;
        
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles());
        return dto;
    }
}