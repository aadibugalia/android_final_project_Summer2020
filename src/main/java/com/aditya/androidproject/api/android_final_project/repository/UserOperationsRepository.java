package com.aditya.androidproject.api.android_final_project.repository;


import com.aditya.androidproject.api.android_final_project.models.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOperationsRepository extends JpaRepository<Person, Long> {

    // Optional<Person> searchPerson(String userName);
    // Optional<Person> updateToDo(String userName, String password, long ideaID);
    // Optional<Person> updatedFollowing(String userName, String password, long personID);


}