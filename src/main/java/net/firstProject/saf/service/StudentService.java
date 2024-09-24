package net.firstProject.saf.service;

import net.firstProject.saf.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);
    StudentDto getStudentById(Long studentId);
    List<StudentDto> getAllStudents();
}
