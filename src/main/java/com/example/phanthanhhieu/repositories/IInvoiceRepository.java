package com.example.phanthanhhieu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.phanthanhhieu.entities.Invoice;

@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice, Long> {
}