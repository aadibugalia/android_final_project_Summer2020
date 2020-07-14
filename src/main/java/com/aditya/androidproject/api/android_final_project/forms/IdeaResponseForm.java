package com.aditya.androidproject.api.android_final_project.forms;

import java.util.Set;

import com.aditya.androidproject.api.android_final_project.models.Idea;

import lombok.Data;

@Data
public class IdeaResponseForm {
    private Set<Idea> ideas;

    private String userName;
    private String title;
    private String context;
    private String content;
    private String id;

    private String status;
    private String message;
}