package com.example.lab.service;

import com.example.lab.model.Author;
import com.example.lab.model.Book;
import com.example.lab.model.BookCategory;
import com.example.lab.model.Country;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> save (String name, String surname, Long country);
    Optional<Author> edit (Long id,String name, String surname, Long country);

    void deleteById(Long id);
}
