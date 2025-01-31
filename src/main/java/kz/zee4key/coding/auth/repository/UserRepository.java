package kz.zee4key.coding.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kz.zee4key.coding.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}