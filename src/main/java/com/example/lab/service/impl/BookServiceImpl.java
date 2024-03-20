package com.example.lab.service.impl;

import com.example.lab.model.Author;
import com.example.lab.model.Book;
import com.example.lab.model.BookCategory;
import com.example.lab.model.dto.BookDto;
import com.example.lab.repository.AuthorRepository;
import com.example.lab.repository.BookRepository;
import com.example.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(String name, BookCategory category, Long author, int availableCopies) {
        Author author1 = this.authorRepository.findById(author).orElseThrow(RuntimeException::new);
        Book book = new Book(name,category,author1,availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(RuntimeException::new);
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, String name, BookCategory category, Long author, int availableCopies) {
        Author author1 = this.authorRepository.findById(author).orElseThrow(RuntimeException::new);
        Book book = this.bookRepository.findById(id).orElseThrow(RuntimeException::new);
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author1);
        book.setAvailableCopies(availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = findById(id).orElseThrow(RuntimeException::new);
        Author author = authorRepository.findById(bookDto.getAuthor()).orElseThrow(RuntimeException::new);
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public boolean rentById(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(RuntimeException::new);
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        this.bookRepository.save(book);
        return true;
    }
}
