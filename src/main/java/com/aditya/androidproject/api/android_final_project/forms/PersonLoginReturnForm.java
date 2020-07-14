package com.aditya.androidproject.api.android_final_project.forms;

import com.aditya.androidproject.api.android_final_project.models.Person;

import lombok.Data;

@Data
public class PersonLoginReturnForm {

    private String status;
    private String message;
    private Person person;
    
}