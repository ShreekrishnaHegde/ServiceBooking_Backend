package net.firstProject.saf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StudentDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Float pu_percentage;
    private String address;



}
