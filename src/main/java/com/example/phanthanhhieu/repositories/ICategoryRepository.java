package com.example.phanthanhhieu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.phanthanhhieu.entities.Category;

@Repository
public interface ICategoryRepository extends
        JpaRepository<Category, Long> {
}
