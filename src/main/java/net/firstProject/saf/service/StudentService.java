package net.firstProject.saf.service;

import net.firstProject.saf.dto.StudentDto;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);
    StudentDto getStudentById(Long studentId);

}
