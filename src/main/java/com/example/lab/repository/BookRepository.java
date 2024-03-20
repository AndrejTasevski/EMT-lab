package com.example.lab.repository;

import com.example.lab.model.Author;
import com.example.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    public Book deleteBookByName(String name);
}
