package com.aditya.androidproject.api.android_final_project.repository;

import java.util.Set;

import com.aditya.androidproject.api.android_final_project.models.Idea;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long> {
    
    Idea findIdeaByid(Long id);
    Set<Idea>  findIdeaBytitle(String title);
}