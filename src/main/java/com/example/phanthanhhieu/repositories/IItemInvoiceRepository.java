package com.example.phanthanhhieu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.phanthanhhieu.entities.ItemInvoice;

@Repository
public interface IItemInvoiceRepository extends
        JpaRepository<ItemInvoice, Long> {
}
