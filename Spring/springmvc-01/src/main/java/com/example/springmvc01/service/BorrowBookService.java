package com.example.springmvc01.service;

import com.example.springmvc01.entity.Book;
import com.example.springmvc01.entity.BookBorrowed;
import com.example.springmvc01.entity.Reader;
import com.example.springmvc01.exception.BookNotFoundException;
import com.example.springmvc01.model.request.BorrowBookCreationRequest;
import com.example.springmvc01.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class BorrowBookService {

    private final BookRepository bookRepository;
    private final ReaderService readerService;

    public List<BookBorrowed> createBorrowBook(BorrowBookCreationRequest request) throws BookNotFoundException {
        Book book = bookRepository.findById(request.getBookId());
        if (book == null) {
            throw new BookNotFoundException("Khong tim thay cuon sach can muon");
        }

        Reader reader = readerService.findById(request.getReaderId());
        if (reader == null) {
            throw new BookNotFoundException("Khong tim thay ban doc cho muon");
        }

        // tìm xem trong danh sách mượn ông reader này đã mượn cuốn book hay chưa
        // --> nếu rồi --> kiểm tra số lượng đã mượn + số lượng muốn mượn thêm có vượt quá hay không --> nếu không thì thêm mới như thường
        // --> nếu chưa --> thêm mới như thường

        BookBorrowed bookBorrowed = BookBorrowed.builder()
                .reader(reader)
                .book(book)
                .quantity(request.getQuantity())
                .borrowedDate(LocalDate.now())
                .build();

        // lấy ra danh sách lượt mượn đã có
        // thêm vào danh sách đã có lúc trước
        // trả ra danh sách

        return new ArrayList<>(Arrays.asList(bookBorrowed));

    }
}
