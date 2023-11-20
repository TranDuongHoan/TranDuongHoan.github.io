package com.example.springmvc01.service;

import com.example.springmvc01.entity.Book;
import com.example.springmvc01.exception.BookNotFoundException;
import com.example.springmvc01.model.request.BookCreationRequest;
import com.example.springmvc01.model.request.BookUpdateRequest;
import com.example.springmvc01.model.response.BookDetailResponse;
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

    public List<Book> deleteBook(int id) throws BookNotFoundException {
        return bookRepository.delete(id);
    }

    public List<Book> createBook(BookCreationRequest bookCreationRequest) {
        Book book = Book.builder()
                .id(BookRepository.AUTO_ID++)
                .name(bookCreationRequest.getName())
                .author(bookCreationRequest.getAuthor())
                .description(bookCreationRequest.getDescription())
                .categories(bookCreationRequest.getCategories())
                .publishedYear(bookCreationRequest.getPublishedYear())
                .build();
        return bookRepository.createBook(book);
    }

    public BookDetailResponse findById(int id) throws BookNotFoundException {
        Book book = bookRepository.findById(id);
        return BookDetailResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .description(book.getDescription())
                .publishedYear(book.getPublishedYear())
                .categories(book.getCategories())
                .build();
    }

    public List<Book> updateBook(BookUpdateRequest book) throws BookNotFoundException {
        return bookRepository.updateBook(book);
    }
}
