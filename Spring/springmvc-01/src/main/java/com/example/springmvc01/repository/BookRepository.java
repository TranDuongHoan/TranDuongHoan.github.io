package com.example.springmvc01.repository;

import com.example.springmvc01.entity.Book;
import com.example.springmvc01.model.request.BookCreationRequest;
import com.example.springmvc01.model.request.BookUpdateRequest;
import com.example.springmvc01.statics.BookCategory;
import com.example.springmvc01.util.FileUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Slf4j
@Repository
@RequiredArgsConstructor
public class BookRepository {

    private static final List<Book> books = new ArrayList<>();

    private static int AUTO_ID = 1000;

    private final FileUtil<Book> fileUtil;

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
        List<Book> list = fileUtil.readDataFromFile("books", Book[].class);
        log.info(list.toString());
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
                .categories(bookCreationRequest.getCategories())
                .publishedYear(bookCreationRequest.getPublishedYear())
                .build();

        List<Book> books = getAll();
        books.add(book);

    }



    public void updateBook(BookUpdateRequest book) {
        for (int i = 0; i < books.size(); i++){
            if (books.get(i).getId() == book.getId()){
                books.get(i).setName(book.getName());
                books.get(i).setAuthor(book.getAuthor());
                books.get(i).setDescription(book.getDescription());
                books.get(i).setPublishedYear(book.getPublishedYear());
                books.get(i).setCategories(book.getCategories());
                break;
            }
        }
    }

    public Book findById(int id) {
        for (int i = 0; i < books.size(); i++){
            if (books.get(i).getId() == id){
                return books.get(i);
            }
        }
        return null;
//        return books.stream().filter(b -> b.getId() == id).findFirst().get();
    }





}
