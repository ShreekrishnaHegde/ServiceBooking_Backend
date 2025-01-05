package net.firstProject.sbs.service;

import net.firstProject.sbs.dto.SignupRequestDto;
import net.firstProject.sbs.dto.UserDto;
import org.springframework.stereotype.Service;


public interface AuthService {
    UserDto signupClient(SignupRequestDto signupRequestDto);

    Boolean presentByEmail(String email);

    UserDto signupCompany(SignupRequestDto signupRequestDto);
}
