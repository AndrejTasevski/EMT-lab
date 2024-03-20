package com.example.lab.service.impl;

import com.example.lab.model.Author;
import com.example.lab.model.Book;
import com.example.lab.model.Country;
import com.example.lab.repository.AuthorRepository;
import com.example.lab.repository.CountryRepository;
import com.example.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(String name, String surname, Long country) {
        Country country1 = this.countryRepository.findById(country).orElseThrow(RuntimeException::new);
        Author author = new Author(name,surname,country1);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public Optional<Author> edit(Long id, String name, String surname, Long country) {
        Country country1 = this.countryRepository.findById(country).orElseThrow(RuntimeException::new);
        Author author = this.authorRepository.findById(id).orElseThrow(RuntimeException::new);
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country1);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
