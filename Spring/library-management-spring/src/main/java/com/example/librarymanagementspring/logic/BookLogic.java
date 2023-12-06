package com.example.librarymanagementspring.logic;

import com.example.librarymanagementspring.entity.Book;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookLogic {

    List<Book> books = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    public void inputNewBook() {
        System.out.println("Bạn muốn thêm mới bao nhiêu đầu sách: ");
        int booksNumber = new Scanner(System.in).nextInt();
        for (int i = 0; i < booksNumber; i++) {
            System.out.println("Nhập thông tin cho đầu sách thứ " + (i + 1));
            Book book = new Book();
            book.inputInfo();
            books.add(book);
        }
    }

    public void showBook() {
        System.out.println(books);
    }

    public boolean bookIsNotEmpty() {
        books.isEmpty();
        return false;
    }

}
