package com.example.springmvc01.repository;

import com.example.springmvc01.entity.Book;
import com.example.springmvc01.entity.Reader;
import com.example.springmvc01.exception.BookNotFoundException;
import com.example.springmvc01.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ReaderRepository {

    private static final String READER_DATA_FILE_NAME = "readers";

    public static int AUTO_ID = 1000;

    private final FileUtil<Reader> fileUtil;

    public List<Reader> getAll() {
        return fileUtil.readDataFromFile(READER_DATA_FILE_NAME, Reader[].class);
    }

    public Reader findById(int id) throws BookNotFoundException {
        List<Reader> readers = getAll();
        if (readers == null || readers.isEmpty()) {
            throw new BookNotFoundException("Books not found");
        }
        return readers.stream().filter(b -> b.getId() == id).findFirst().get();
    }

}
