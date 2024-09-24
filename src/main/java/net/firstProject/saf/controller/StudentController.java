package net.firstProject.saf.controller;


import lombok.AllArgsConstructor;
import net.firstProject.saf.dto.StudentDto;
import net.firstProject.saf.entity.Student;
import net.firstProject.saf.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor

public class StudentController {

    private StudentService studentService;

    //ADD Student REST API

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto savedStudent = studentService.createStudent(studentDto);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    //Get Student REST API
    @GetMapping("{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long studentId){
        StudentDto studentDto=studentService.getStudentById(studentId);
        return  ResponseEntity.ok(studentDto);
    }

    @GetMapping
    //Get ALl Students REST API
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        List<StudentDto> students= studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    //Update Student REST API
    @PutMapping("{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("id") Long studentId,@RequestBody StudentDto updatedStudent){
       StudentDto studentDto= studentService.updateStudent(studentId,updatedStudent);
       return ResponseEntity.ok(studentDto);
    }

    //Delete Student REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id")Long studentId){
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok("Student deleted successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllStudents(){
        studentService.deleteAllStudents();
        return ResponseEntity.ok("Deleted all data.");
    }


    @GetMapping("/sort")
    //Get ALl Students REST API
    public ResponseEntity<List<String>> sortByfirstName(){
        List<String> list=studentService.sortByfirstName();
        return ResponseEntity.ok(list);
    }

}
/*
When the request hits the endpoint , if it is a post request the createStudent method will be executed.
The @PostMapping annotation  specifies that the annotated method should handle HTTP POST requests.
The method returns a ResponseEntity object, which encapsulates the response status
(e.g.HTTP status code) and the response body (in this case, a StudentDto object).
The @RequestBody annotation on the student parameter indicates that the request body should be
deserialized into a Student object. Means JSON data in the POST request is converted into java object.
Hence, the body of JSON is now stored in studentDto object of class StudentDto.Now,studentDto is passed into
createStudent method which saves this info. in the SQL database.If data saves successfully,the method
will return HTTPStatus code along with savedStudent data.
 */
