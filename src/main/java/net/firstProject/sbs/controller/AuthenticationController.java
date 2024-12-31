package net.firstProject.sbs.controller;


import lombok.AllArgsConstructor;
import net.firstProject.sbs.dto.SignupRequestDto;
import net.firstProject.sbs.dto.UserDto;
import net.firstProject.sbs.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
//@RequestMapping("/api/students")
@AllArgsConstructor

public class AuthenticationController {

    private AuthService studentService;
    @Autowired
    private AuthService authService;

    @PostMapping("/client/sign-up")
    public ResponseEntity<?> signupClient(@RequestBody SignupRequestDto signupRequestDto){
        if(authService.presentByEmail(signupRequestDto.getEmail())){
            return new ResponseEntity<>("Client Already exists with email", HttpStatus.NOT_ACCEPTABLE);

        }
        UserDto createdUser=authService.signupClient(signupRequestDto);
        return new ResponseEntity<>(createdUser,HttpStatus.OK);
    }

    @PostMapping("/company/sign-up")
    public ResponseEntity<?> signupCompany(@RequestBody SignupRequestDto signupRequestDto){
        if(authService.presentByEmail(signupRequestDto.getEmail())){
            return new ResponseEntity<>("Company Already exists with email", HttpStatus.NOT_ACCEPTABLE);

        }
        UserDto createdUser=authService.signupCompany(signupRequestDto);
        return new ResponseEntity<>(createdUser,HttpStatus.OK);
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
