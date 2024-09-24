package net.firstProject.saf.controller;


import lombok.AllArgsConstructor;
import net.firstProject.saf.dto.StudentDto;
import net.firstProject.saf.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor

public class EmployeeController {

    private StudentService studentService;

    //ADD Student REST API

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto savedStudent = studentService.createStudent(studentDto);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
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
