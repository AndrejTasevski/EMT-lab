package com.example.lab.model.dto;

import com.example.lab.model.BookCategory;
import lombok.Data;


@Data
public class BookDto {

    private String name;

    private BookCategory category;

    private Long author;
    private int availableCopies;

    public BookDto(String name, BookCategory category, Long author, int availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public BookDto() {

    }
}
