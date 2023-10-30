package com.example.springmvc01.service;

import com.example.springmvc01.entity.Book;
import com.example.springmvc01.model.request.BookCreationRequest;
import com.example.springmvc01.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    public void deleteBook(int id) {
        bookRepository.delete(id);
    }

    public void createBook(BookCreationRequest bookCreationRequest) {
        bookRepository.createBook(bookCreationRequest);
    }

}
