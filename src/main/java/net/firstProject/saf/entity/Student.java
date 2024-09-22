package net.firstProject.saf.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name="saf")


public class Student {
//    The @Id annotation in Java is used in the context of JPA to specify the primary key of an entity.
    @Id
//    This annotation is generally used in conjunction with @Id annotation
//    to automatically generate unique values for primary key columns within our database tables.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Long is not a primitive datatype here.
    private Long id;

    @Column(name = "roll_number")
    private int roll_number;

    @Column(name= "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email_id",nullable = false,unique = true)
    private String email;

    @Column(name = "pu_percentage",nullable = false)
    private Float  pu_percentage;

    @Column(name ="address")
    private String address;

}

//Getter and Setter
//public class Example {
//    private String name;
//
//    //getter
//    public String getName() {
//        return name;
//    }
//
//    //setter
//    public void setName(String name) {
//        this.name = name;
//    }
//}

//ALlArgsConstructor

//public class Person {
//
//    private String firstname;
//    private String lastname;
//
//    public Person(String firstname, String lastname) {
//        this.firstname = firstname;
//        this.lastname = lastname;
//    }
//
//}
