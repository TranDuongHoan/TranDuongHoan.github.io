package com.example.librarymanagementspring.config;

import com.example.librarymanagementspring.entity.Reader;
import com.example.librarymanagementspring.logic.BookLogic;
import com.example.librarymanagementspring.logic.MenuLogic;
import com.example.librarymanagementspring.logic.ReaderLogic;
import com.example.librarymanagementspring.logic.RentalLogicManagement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
//
//@Configuration
//public class BeanConfig {
//
//    @Bean
//    public MenuLogic menuLogic(){
//        return new MenuLogic(bookLogic(),readerLogic(),rentalLogicManagement());
//    }
//
//    @Bean
//    public BookLogic bookLogic(){
//        return new BookLogic();
//    }
//
//    @Bean
//    public ReaderLogic readerLogic(){
//        return new ReaderLogic();
//    }
//
//    @Bean
//    public RentalLogicManagement rentalLogicManagement(){
//        return new RentalLogicManagement(bookLogic(),readerLogic());
//    }
//}
