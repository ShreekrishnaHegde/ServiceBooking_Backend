package net.firstProject.sbs.entity;

import jakarta.persistence.*;
import lombok.*;
import net.firstProject.sbs.Enums.UserRole;
import net.firstProject.sbs.dto.UserDto;

//We use Getters when accessing private variables of a class from outside that class.
//We can achieve this by creating a "public" method inside the class which returns the value of the variable.
//Now using getters we can also validate the value before assigning it.
//We can provide different access levels to the fields; for example, the get (read-access) may be public,
// while the set (write-access) could be protected
@Getter
@Setter
//@NoArgsConstructor generates a no-argument constructor for a class,
//resembling the default constructor.
@NoArgsConstructor
//This annotation generates a constructor that initializes all fields in the class.
//It includes all fields, regardless of their accessibility modifiers
@AllArgsConstructor
//It is used to specify that a class is an entity and is mapped to a database table.
@Entity
//By default, the entity name is the same as the class name.
//However, we can  customize the table name using the @Table annotation.
@Table(name="users ")
@Data
public class User {
//    The @Id annotation in Java is used in the context of JPA to specify the primary key of an entity.
    @Id
//    This annotation is generally used in conjunction with @Id annotation
//    to automatically generate unique values for primary key columns within our database tables.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Long is not a primitive datatype here
    private Long id;


    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String lastname;
    @Column
    private String phone;
    @Column
    private UserRole role;

    public UserDto getDto() {
        UserDto userDto=new UserDto();
        userDto.setId(id);
        userDto.setName(name);
        userDto.setEmail(email);
        userDto.setRole(role);

        return userDto;
    }
}