package net.firstProject.saf.service;

import net.firstProject.saf.dto.StudentDto;
import net.firstProject.saf.entity.Student;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);
    StudentDto getStudentById(Long studentId);
    List<StudentDto> getAllStudents();
    StudentDto updateStudent(Long studentId,StudentDto updatedStudent);
    void deleteStudent(Long studentId);
    void deleteAllStudents();
    List<StudentDto> sortByFirstName();
}
