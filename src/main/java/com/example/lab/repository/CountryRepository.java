package com.example.lab.repository;

import com.example.lab.model.Author;
import com.example.lab.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
