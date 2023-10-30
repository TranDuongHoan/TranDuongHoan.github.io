package com.example.springmvc01.repository;

import com.example.springmvc01.entity.Reader;
import com.example.springmvc01.model.request.ReaderCreationRequest;
import com.example.springmvc01.statics.ReaderLevel;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReaderRepository {

    private static final List<Reader> readers = new ArrayList<>();

    private static int AUTO_ID = 1000;

    private final ObjectMapper objectMapper;

    static {

        for (int i = 0; i < 10; i++) {


            Reader reader = Reader.builder()
                    .id(AUTO_ID++)
                    .name("Nguyễn Văn" + i)
                    .address("Trần Văn"  + i)
                    .levels(Arrays.asList(ReaderLevel.STUDENT, ReaderLevel.POSTGRADUATE, ReaderLevel.TEACHER))
                    .phone(i)
                    .build();
            readers.add(reader);
        }
    }

    public List<Reader> getAll() {
        return readers;
    }

    public void delete(int id) {
        for (int i = 0; i < readers.size(); i++) {
            if (readers.get(i).getId() == id) {
                readers.remove(i);
                return;
            }
        }
    }

    public void createReader(ReaderCreationRequest readerCreationRequest) {
        Reader reader = Reader.builder()
                .id(AUTO_ID++)
                .name(readerCreationRequest.getName())
                .address(readerCreationRequest.getAddress())
                .phone(readerCreationRequest.getPhone())
                .levels(readerCreationRequest.getLevels())
                .build();
        readers.add(reader);

    }
}
