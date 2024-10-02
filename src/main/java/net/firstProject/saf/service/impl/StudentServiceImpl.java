package net.firstProject.saf.service.impl;

import exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import net.firstProject.saf.dto.StudentDto;
import net.firstProject.saf.entity.Student;
import net.firstProject.saf.mapper.StudentMapper;
import net.firstProject.saf.repository.StudentRepository;
import net.firstProject.saf.service.StudentService;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    @Override
    public StudentDto createStudent(StudentDto studentDto) {

        Student student= StudentMapper.mapToStudent(studentDto);
        Student savedStudent=studentRepository.save(student);
        return StudentMapper.mapToStudentDto(savedStudent);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student=studentRepository.findById(studentId)
                .orElseThrow(()->new ResourceNotFoundException("Student does not exist"));
        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students=studentRepository.findAll();
        return students.stream().map((student -> StudentMapper.mapToStudentDto(student)))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto updatedStudent) {
        Student student=studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student does not exist"));
        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setEmail(updatedStudent.getEmail());

        Student newStudent=studentRepository.save(student);
        return StudentMapper.mapToStudentDto(newStudent);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student=studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student does not exist"));
        studentRepository.deleteById(studentId);
    }

    @Override
    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }

    @Override
    public List<String> sortByfirstName() {
        List<String> sortedNames=studentRepository.sortByfirstName();
        return sortedNames;
    }


}
/*
createStudent method takes studentDto as argument and returns the object of type StudentDto.
StudentMapper class has mapToStudent method which takes studentDto as argument, and returns an object of Student type,
after initializing all the fields of the  object to the data sent by the client.Since id is not provided it is
initialized to null.Since these statements are assigned to "student" object, now student object contains all info.
sent by the client.Then this object is passed into the student repository which contains save method to save t
he details of the student in the database.
While saving data, Id filed is assigned a value.These field along with new ID value is now stored in
savedStudent object and using mapToStudentDto method the saved student is returned.
 */
