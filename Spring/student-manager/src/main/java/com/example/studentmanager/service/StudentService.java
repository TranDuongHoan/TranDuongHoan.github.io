package com.example.studentmanager.service;

import com.example.studentmanager.entity.Student;

import com.example.studentmanager.exception.StudentNotFoundException;
import com.example.studentmanager.model.request.StudentCreationRequest;
import com.example.studentmanager.model.response.StudentResponse;
import com.example.studentmanager.repository.StudentJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {
//    private final StudentRepository studentRepository;

    private final StudentJpaRepository studentJpaRepository;

    public List<StudentResponse> getStudent() {
//        List<Student> students = studentRepository.getStudent();
//        List<StudentResponse> result = new ArrayList<>();
        List<Student> students = studentJpaRepository.findAll();

        return students.stream()
                .map(student -> StudentResponse.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .address(student.getAddress())
                        .phone(student.getPhone())
                        .className(student.getClassName())
                        .build()
                )
                .collect(Collectors.toList());

//        for (int i = 0; i < students.size(); i++){
//            Student student = students.get(i);
//            StudentResponse studentResponse = StudentResponse.builder()
//                    .id(student.getId())
//                    .name(student.getName())
//                    .address(student.getAddress())
//                    .phone(student.getPhone())
//                    .className(student.getClassName())
//                    .build();
//                    result.add(studentDetailResponse);
//        }
//        return result;
    }

//    public List<Student> deleteStudent(int id) throws StudentNotFoundException {
//        return studentRepository.delete(id);
//    }

    public void createStudent(@Valid StudentCreationRequest studentCreationRequest) {
        Student student = Student.builder()
                .name(studentCreationRequest.getName())
                .address(studentCreationRequest.getAddress())
                .phone(studentCreationRequest.getPhone())
                .className(studentCreationRequest.getClassName())
                .build();
//        studentRepository.save(student);
        studentJpaRepository.save(student);
    }

    public void delete(Long id) {
        studentJpaRepository.deleteById(id);

//        List<Student> result = new ArrayList<>();
//        for (int i=0; i<students.size(); i++){
//            if (students.get(i).getId() == id){
//                continue;
//            }
//            result.add(students.get(i));
//        }

//        List<Student> result = students
//                .stream()
//                .filter(s -> s.getId() != id).collect(Collectors.toList());

//        List<Student> students = studentRepository.getStudent();
//        students.removeIf(s -> s.getId() == id);
//        studentRepository.save(students);
    }

    public StudentResponse getStudentDetails(Long id) throws StudentNotFoundException {

//       Optional<Student> studentOptional = studentJpaRepository.findById(id);
//       if (studentOptional.isEmpty()){
//           throw new StudentNotFoundException("Student with id " + id + " could not be found");
//       }
//
//        Student student = studentOptional.get();
//                return StudentResponse.builder()
//                    .id(student.getId())
//                    .name(student.getName())
//                    .address(student.getAddress())
//                    .phone(student.getPhone())
//                    .className(student.getClassName())
//                    .build();

        return studentJpaRepository.findById(id)
        .map(student -> StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .address(student.getAddress())
                .phone(student.getPhone())
                .className(student.getClassName())
                .build()
        )
        .orElseThrow(() ->
                new StudentNotFoundException("Student with id " + id + " could not be found")
        );

//        List<Student> students = studentRepository.getStudent();//
//        return students.stream().filter(s -> s.getId() == id)
//                .findFirst()
//                .map(student -> StudentResponse.builder()
//                        .id(student.getId())
//                        .name(student.getName())
//                        .address(student.getAddress())
//                        .phone(student.getPhone())
//                        .className(student.getClassName())
//                        .build())
//                .get();

//        for (int i=0; i<students.size(); i++){
//            if (students.get(i).getId() == id){
//                Student student = students.get(i);
//                return StudentResponse.builder()
//                    .id(student.getId())
//                    .name(student.getName())
//                    .address(student.getAddress())
//                    .phone(student.getPhone())
//                    .className(student.getClassName())
//                    .build();
//
//            }
//
//        }
//        return null;
    }


    public void updateStudent(Long id, StudentCreationRequest request) throws StudentNotFoundException {

         Optional<Student> studentOptional =  studentJpaRepository.findById(id);
        if (studentOptional.isEmpty()){
            throw new StudentNotFoundException("Student with id " + id + " could not be found");
        }
        Student student = studentOptional.get();
        student.setName(request.getName());
        student.setPhone(request.getPhone());
        student.setAddress(request.getAddress());
        student.setClassName(request.getClassName());

        studentJpaRepository.save(student);

//        List<Student> students = studentRepository.getStudent();
//        for (int i = 0; i < students.size(); i++) {
//            if (students.get(i).getId() == id) {
//                students.get(i).setName(request.getName());
//                students.get(i).setAddress(request.getAddress());
//                students.get(i).setPhone(request.getPhone());
//                students.get(i).setClassName(request.getClassName());
//                break;
//            }
//        }
//        studentRepository.save(students);
    }
}
