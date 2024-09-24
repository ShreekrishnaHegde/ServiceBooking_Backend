package net.firstProject.saf.repository;

import net.firstProject.saf.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//Extends keyword is used to extend the functionality of the parent class to the subclass.But here it is interface.
//StudentRepository interface inherits the property from its parent interface JPaRepository

//We know that Jpa is used to interact with database by mapping java classes to database tables
//and java datatypes to SQL datatypes. Jpa Repository contains methods to perform CRUD operations on the
//database.But it requires the type of entity and type of  primary key to perform operations. Hence, we are
//passing entity type that is Student and type of primary key that is Long.
public interface StudentRepository extends JpaRepository<Student,Long> {


        @Query(value = "SELECT first_name FROM saf ORDER BY first_name ASC", nativeQuery = true)
        List<String> sortByfirstName();
}


//We can also define custom methods in the interface
//public interface StudentRepository extends JpaRepository<Student, Long> {
//    @Query("SELECT s FROM Student s WHERE s.name = ?1")
//    List<Student> findByName(String name);
//}