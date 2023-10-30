package com.example.springmvc01.repository;

import com.example.springmvc01.entity.Book;
import com.example.springmvc01.model.request.BookCreationRequest;
import com.example.springmvc01.statics.BookCategory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private static final List<Book> books = new ArrayList<>();

    private static int AUTO_ID = 1000;

    private final ObjectMapper objectMapper;

    static {

        for (int i = 0; i < 10; i++) {
//            Book book = new Book();
//            book.setId(i);
//            book.setName("Sách " + i);
//            book.setAuthor("Nguyễn Văn " + i);
//            book.setDescription("Mô tả thứ " + i);
//            book.setPublishedYear(i);
//            book.setCategories(Arrays.asList(BookCategory.KID));

            Book book = Book.builder()
                    .id(AUTO_ID++)
                    .name("Sách " + i)
                    .author("Nguyễn Văn " + i)
                    .description("Mô tả thứ " + i)
                    .categories(Arrays.asList(BookCategory.KID))
                    .publishedYear(i)
                    .build();

            books.add(book);
        }

        Book book = new Book();
        book.setId(100);
        book.setName("Gió đầu mùa");
        book.setAuthor("Thạch Lam");
        book.setDescription("Êm dịu");
        book.setPublishedYear(2019);
        book.setCategories(Arrays.asList(BookCategory.SHORT_STORY, BookCategory.NOVEL));
        books.add(book);
    }

    public List<Book> getAll() {
        return books;
    }

    public void delete(int id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.remove(i);
                return;
            }
        }
    }

    public void createBook(BookCreationRequest bookCreationRequest) {
//        Book book = objectMapper.convertValue(bookCreationRequest, Book.class);
//        book.setId(AUTO_ID++);
        Book book = Book.builder()
                .id(AUTO_ID++)
                .name(bookCreationRequest.getName())
                .author(bookCreationRequest.getAuthor())
                .description(bookCreationRequest.getDescription())
//                .categories(Arrays.asList(BookCategory.KID))
                .publishedYear(bookCreationRequest.getPublishedYear())
                .build();
        books.add(book);

    }
}
