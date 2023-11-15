package com.example.springmvc01.util;

import com.example.springmvc01.entity.Book;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;



@Slf4j
@AllArgsConstructor
public class FileUtil<T> {

    private final Gson gson;

    public List<T> readDataFromFile(String filePath, Class<Book[]> targetClass) {
        String fileDataStr = "";
        // java NIO
        File folder;
        try {
            folder = new ClassPathResource("data" + File.separator + filePath + ".json").getFile();

            if (folder.isFile()) {
                fileDataStr = new String(Files.readAllBytes(Paths.get(folder.getAbsolutePath())));
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return Arrays.asList(gson.fromJson(fileDataStr, targetClass));
    }

    public void writeDataToFile(String filePath, List<T> data) {
        File folder;
        Writer writer = null;
        try {
            folder = new ClassPathResource("data" + File.separator + filePath + ".json").getFile();
            writer = new FileWriter(folder);
            gson.toJson(data, writer);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

}

