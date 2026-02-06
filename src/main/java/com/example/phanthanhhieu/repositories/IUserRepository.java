package com.example.phanthanhhieu.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.phanthanhhieu.entities.User;
@Repository
public interface IUserRepository extends JpaRepository<User, String> {
Optional<User> findByUsername(String username);
Optional<User> findByEmail(String email);
}
