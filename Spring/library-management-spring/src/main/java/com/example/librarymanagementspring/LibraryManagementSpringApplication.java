package com.example.librarymanagementspring;

import com.example.librarymanagementspring.logic.MenuLogic;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@AllArgsConstructor
@SpringBootApplication
public class LibraryManagementSpringApplication implements CommandLineRunner {
    MenuLogic menuLogic;

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementSpringApplication.class, args);
    }

    public void run(String... args) throws Exception {
        menuLogic.run();
    }
}
