package com.aditya.androidproject.api.android_final_project.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;



@Entity
@Data
@EqualsAndHashCode(exclude = "creator")
public class Idea {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  
    private long id;

    @Column(unique = false, nullable = true)
    private String originalID;


    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String context;

    
    @Column(nullable = false)
    private String content;

   
    @ManyToOne 
    @JsonIgnore
    private Person  creator;

  

}