package com.example.phanthanhhieu.controllers;

import com.example.phanthanhhieu.services.BookService;
import com.example.phanthanhhieu.services.CategoryService;
import com.example.phanthanhhieu.viewmodels.BookGetVm;
import com.example.phanthanhhieu.viewmodels.BookPostVm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ApiController {
    private final BookService bookService;
    private final CategoryService categoryService;

    public ApiController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    // --- LẤY DANH SÁCH SÁCH ---
    @GetMapping("/books")
    public ResponseEntity<List<BookGetVm>> getAllBooks(Integer pageNo, Integer pageSize, String sortBy) {
        return ResponseEntity.ok(bookService.getAllBooks(
                pageNo == null ? 0 : pageNo,
                pageSize == null ? 20 : pageSize,
                sortBy == null ? "id" : sortBy)
                .stream()
                .map(BookGetVm::from)
                .toList());
    }

    // --- LẤY SÁCH THEO ID ---
    @GetMapping("/books/id/{id}")
    public ResponseEntity<BookGetVm> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id)
                .map(BookGetVm::from)
                .orElse(null));
    }

    // --- XÓA SÁCH ---
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }

    // --- TÌM KIẾM SÁCH ---
    @GetMapping("/books/search")
    public ResponseEntity<List<BookGetVm>> searchBooks(String keyword) {
        return ResponseEntity.ok(bookService.searchBook(keyword)
                .stream()
                .map(BookGetVm::from)
                .toList());
    }

    // --- THÊM SÁCH MỚI
    @PostMapping("/books")
    public ResponseEntity<Void> addBook(@RequestBody BookPostVm bookPostVm) {
        bookService.addBookFromVm(bookPostVm); 
        return ResponseEntity.ok().build();
    }

    // --- CẬP NHẬT SÁCH
    @PutMapping("/books/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable Long id, @RequestBody BookPostVm bookPostVm) {
        bookService.updateBookFromVm(id, bookPostVm);
        return ResponseEntity.ok().build();
    }
    
}