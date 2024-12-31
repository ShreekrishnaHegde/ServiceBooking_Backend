package net.firstProject.sbs.service.impl;



import lombok.Data;
import net.firstProject.sbs.Enums.UserRole;
import net.firstProject.sbs.dto.SignupRequestDto;
import net.firstProject.sbs.dto.UserDto;
import net.firstProject.sbs.entity.User;
import net.firstProject.sbs.repository.UserRepository;
import net.firstProject.sbs.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto signupClient(SignupRequestDto signupRequestDto) {
        User user=new User();
        user.setName(signupRequestDto.getName());
        user.setEmail(signupRequestDto.getEmail());
        user.setLastname(signupRequestDto.getLastname());
        user.setPhone(signupRequestDto.getPhone());
        user.setPassword(signupRequestDto.getPassword());
        user.setRole(UserRole.CLIENT);

        return userRepository.save(user).getDto();
    }
    @Override
    public Boolean presentByEmail(String email) {
        return userRepository.findFirstByEmail(email)!=null;
    }

    @Override
    public UserDto signupCompany(SignupRequestDto signupRequestDto) {
        User user=new User();
        user.setName(signupRequestDto.getName());
        user.setEmail(signupRequestDto.getEmail());
        user.setPhone(signupRequestDto.getPhone());
        user.setPassword(signupRequestDto.getPassword());
        user.setRole(UserRole.COMPANY);

        return userRepository.save(user).getDto();
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
