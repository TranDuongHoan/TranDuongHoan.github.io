package com.example.springdatajpa02.repository;

import com.example.springdatajpa02.entity.Student;
import com.example.springdatajpa02.model.response.StudentDataResponse;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentJpaRepository extends PagingAndSortingRepository<Student, Long> {

    List<Student> findByName(String name);

    List<Student> findByNameLikeIgnoreCase(String name);

    List<Student> findByNameLikeIgnoreCaseAndAddressLikeIgnoreCase(String name, String address);

    // JPQL (JPA Query Language) --> thao tác với Entity
    @Query(value = "select s from Student s where s.name like :ho_va_ten and s.address like :address")
    // select * from students where name like ... and address like ...
    // :ho_va_ten và cái :address gọi là NAMED_PARAMETER
    List<Student> searchByNameAndAddress(String ho_va_ten, String address);


    // SQL (native SQL) --> thao tác trực tiếp với DB không thông qua entity
    @Query(value = "select id, name, address, phone from students s where s.name like :ho_va_ten and s.address like :address", nativeQuery = true)
    // select * from students where name like ... and address like ...
    // :ho_va_ten và cái :address gọi là NAMED_PARAMETER
    List<StudentDataResponse> searchByNameAndAddressVer2(String ho_va_ten, String address);

//    @Modifying
//    @Query("update Student s set s.name = :name, s.phone = :phone where s.id = :id")
//    void updateNameOfStudent(Long id, String name, String phone);

    @Modifying
    @Query("update Student s set s.name = :name where s.id = :id")
    void updateNameOfStudent(String name, Long id);


    @Modifying
    @Query("delete from Student s where s.name like :name and s.phone like :phone")
    void deleteStudent(String name, String phone);


}
