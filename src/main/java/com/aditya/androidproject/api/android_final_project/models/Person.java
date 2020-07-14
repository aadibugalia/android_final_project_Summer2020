package com.aditya.androidproject.api.android_final_project.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.ManyToAny;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(exclude = "savedIdeasAsToDo")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;


    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(unique = true, nullable = false)
    private String userName;

    @Column( nullable = false)
    private String dob;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String country;


    
    @Column(nullable = false)
    @JsonIgnore
    private String password;


    @OneToMany

    private Set<Idea> savedIdeasAsToDo;

    @ManyToMany
    private Set<Person> peopleFollowed;


    @OneToMany
    private Set<Idea> personalIdeas;



    
}