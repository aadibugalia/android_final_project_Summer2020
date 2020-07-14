package com.aditya.androidproject.api.android_final_project.forms;

import javax.persistence.Column;

import lombok.Data;

@Data
public class TodoForm {

    private String userName;
    
    @Column(name = "id")
    private String ideaID;
    
}