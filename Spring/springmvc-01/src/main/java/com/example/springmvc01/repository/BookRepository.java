package com.example.springmvc01.repository;

import com.example.springmvc01.entity.Book;
import com.example.springmvc01.exception.BookNotFoundException;
import com.example.springmvc01.model.request.BookUpdateRequest;
import com.example.springmvc01.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookRepository {

    private static final String BOOK_DATA_FILE_NAME = "books";

    public static int AUTO_ID = 1000;

    private final FileUtil<Book> fileUtil;

    public List<Book> getAll() {
        return fileUtil.readDataFromFile(BOOK_DATA_FILE_NAME, Book[].class);
    }

    public List<Book> delete(int id) throws BookNotFoundException {
        List<Book> books = getAll();
        if (CollectionUtils.isEmpty(books)) {
            throw new BookNotFoundException("Books not found");
        }
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.remove(i);
                fileUtil.writeDataToFile(BOOK_DATA_FILE_NAME, books);
                return books;
            }
        }
        return null;
    }

    public List<Book> createBook(Book book) {
        List<Book> books = getAll();
        if (CollectionUtils.isEmpty(books)) {
            books = new ArrayList<>();
        }
        books.add(book);
        fileUtil.writeDataToFile(BOOK_DATA_FILE_NAME, books);
        return books;
    }

    public Book findById(int id) throws BookNotFoundException {
        List<Book> books = getAll();
        if (books == null || books.isEmpty()) {
            throw new BookNotFoundException("Books not found");
        }
        return books.stream().filter(b -> b.getId() == id).findFirst().get();
    }

    public List<Book> updateBook(BookUpdateRequest book) throws BookNotFoundException {
        List<Book> books = getAll();
        if (CollectionUtils.isEmpty(books)) {
            throw new BookNotFoundException("Books not found");
        }

        Optional<Book> bookCanUpdate = books.stream().filter(b -> b.getId() == book.getId()).findFirst();
        if (bookCanUpdate.isEmpty()) {
            throw new BookNotFoundException("Books not found");
        }

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == book.getId()) {
                books.get(i).setName(book.getName());
                books.get(i).setAuthor(book.getAuthor());
                books.get(i).setDescription(book.getDescription());
                books.get(i).setPublishedYear(book.getPublishedYear());
                books.get(i).setCategories(book.getCategories());
                fileUtil.writeDataToFile(BOOK_DATA_FILE_NAME, books);
                return books;
            }
        }
        return null;
    }

}
