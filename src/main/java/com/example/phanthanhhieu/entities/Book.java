package com.example.phanthanhhieu.entities;

import com.example.phanthanhhieu.validators.annotations.ValidCategoryId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", length = 50, nullable = false)
    @Size(min = 1, max = 50, message = "Title must be between 1 and 50 characters")
    @NotBlank(message = "Title must not be blank")
    private String title;
    @Column(name = "author", length = 50, nullable = false)
    @Size(min = 1, max = 50, message = "Author must be between 1 and 50 characters")
    @NotBlank(message = "Author must not be blank")
    private String author;
    @Column(name = "price")
    @Positive(message = "Price must be greater than 0")
    private Double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ValidCategoryId
    @ToString.Exclude
    private Category category;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<ItemInvoice> itemInvoices = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public List<ItemInvoice> getItemInvoices() { return itemInvoices; }
    public void setItemInvoices(List<ItemInvoice> itemInvoices) { this.itemInvoices = itemInvoices; }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        Book book = (Book) o;
        return getId() != null && Objects.equals(getId(), book.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
