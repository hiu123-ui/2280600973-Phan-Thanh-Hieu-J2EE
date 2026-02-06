package com.example.phanthanhhieu.validators;

import com.example.phanthanhhieu.entities.Category;
import com.example.phanthanhhieu.validators.annotations.ValidCategoryId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCategoryIdValidator implements
        ConstraintValidator<ValidCategoryId, Category> {
    @Override
    public boolean isValid(Category category,
            ConstraintValidatorContext context) {
        return category != null && category.getId() != null;
    }
}