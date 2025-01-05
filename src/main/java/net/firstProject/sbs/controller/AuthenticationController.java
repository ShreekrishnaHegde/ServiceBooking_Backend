package net.firstProject.sbs.controller;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import net.firstProject.sbs.dto.AuthenticationRequest;
import net.firstProject.sbs.dto.SignupRequestDto;
import net.firstProject.sbs.dto.UserDto;
import net.firstProject.sbs.entity.User;
import net.firstProject.sbs.repository.UserRepository;
import net.firstProject.sbs.service.AuthService;
import net.firstProject.sbs.service.jwt.UserDetailsServiceImpl;
import net.firstProject.sbs.util.JwtUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin("*")
@RestController
//@RequestMapping("/api/students")
@AllArgsConstructor

public class AuthenticationController {

    private AuthService studentService;
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;


    public static final String TOKEN_PREFIX="Bearer";

    public static final String HEADER_STRING="Authorization";

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

    @PostMapping("/authenticate")
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException, JSONException {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            ));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password",e);
        }
        final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt=jwtUtil.generateToken(userDetails.getUsername());

        User user= userRepository.findFirstByEmail(authenticationRequest.getUsername());

        response.getWriter().write(new JSONObject()
                .put("userId",user.getId())
                .put("role",user.getRole())
                .toString()
        );
        response.addHeader("Access-Control-Expose-Headers","Authorization");
        response.addHeader("Access-Control-Allow_Headers","Authorization"+
                "X-PINGOTHER,ORIGIN,X-REQUESTED_WITH,CONTENT-TYPE,ACCEPT,X-CUSTOMER_HEADER");
        response.addHeader(HEADER_STRING,TOKEN_PREFIX+jwt);
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
