package kz.zee4key.coding.auth.service;

import kz.zee4key.coding.auth.dto.LoginRequest;
import kz.zee4key.coding.auth.dto.SignupRequest;
import kz.zee4key.coding.auth.model.Role;
import kz.zee4key.coding.auth.model.User;
import kz.zee4key.coding.auth.repository.UserRepository;
import kz.zee4key.coding.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public User registerUser(SignupRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRoles(Set.of(Role.USER));
        
        return userRepository.save(user);
    }

    public String authenticateUser(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        }
        throw new RuntimeException("Invalid username/password");
    }
}