package com.example.springmvc01.service;

import com.example.springmvc01.entity.Reader;
import com.example.springmvc01.exception.BookNotFoundException;
import com.example.springmvc01.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;

    public List<Reader> getAll() {
        return readerRepository.getAll();
    }

    public Reader findById(int readerId) throws BookNotFoundException {
        return readerRepository.findById(readerId);
    }
}
