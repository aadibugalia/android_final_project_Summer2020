package com.aditya.androidproject.api.android_final_project.repository;


import com.aditya.androidproject.api.android_final_project.models.Person;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    // @Query("select s from Person person where person.userName = ?1 and person.password = ?2")
     Person findPersonByUserNameAndPassword(String userName, String password);
     Person findPersonByEmail(String email);
     Person findPersonByUserName(String userName);
//    // Optional<Person> attemptRegister(Person newPerson);
    // Optional<Person> attemptForgotPassword(String userName, String email);
    // Optional<Person> attemptEditProfile(Person editPerson);


}
