package net.firstProject.saf.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private long id;
    private int roll_number;
    private String firstName;
    private String lastName;
    private int  pu_percentage;
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
