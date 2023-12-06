package com.example.librarymanagementspring.logic;

import com.example.librarymanagementspring.entity.Book;
import com.example.librarymanagementspring.entity.Reader;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReaderLogic {

    List<Reader> readers = new ArrayList<>();

    public List<Reader> getReaders() {
        return readers;
    }

    public void inputNewReader() {
        System.out.println("Bạn muốn thêm mới bao nhiêu người đọc: ");
        int readersNumber = new Scanner(System.in).nextInt();
        for (int i = 0; i < readersNumber; i++) {
            System.out.println("Nhập thông tin cho người đọc thứ " + (i + 1));
            Reader reader = new Reader();
            reader.inputInfo();
            readers.add(reader);
        }
    }

    public void showReader() {
        System.out.println(readers);
    }

    public boolean readerIsNotEmpty() {
        readers.isEmpty();
        return false;
    }


}
