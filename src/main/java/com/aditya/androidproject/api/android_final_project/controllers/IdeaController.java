package com.aditya.androidproject.api.android_final_project.controllers;

import java.util.HashSet;
import java.util.Set;

import com.aditya.androidproject.api.android_final_project.forms.IdeaResponseForm;
import com.aditya.androidproject.api.android_final_project.models.Idea;
import com.aditya.androidproject.api.android_final_project.repository.IdeaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IdeaController {


    private final IdeaRepository ideaRepository;

    @Autowired
    public IdeaController(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }


    @PostMapping("/getAllIdeas")
    public IdeaResponseForm getAllIdeas(IdeaResponseForm ideaResponseForm) {
        IdeaResponseForm responseForm = new IdeaResponseForm();
        try {
            responseForm.setIdeas(new HashSet<Idea>(ideaRepository.findAll()));
            responseForm.setStatus("0");
            responseForm.setMessage("success");
        } catch (Exception e) {
            e.printStackTrace();
            responseForm.setIdeas(new HashSet<Idea>());
            responseForm.setStatus("0");
            responseForm.setMessage("success");
        }
        return responseForm;
    }


    @PostMapping("/getIdea/{title}")
    public IdeaResponseForm getAllIdeas(@PathVariable String mTitle) {
        IdeaResponseForm responseForm = new IdeaResponseForm();



    

    
        try {
            Set<Idea> ideasByTitle = ideaRepository.findIdeaBytitle(mTitle);

            if(ideasByTitle == null) {
                responseForm.setIdeas(new HashSet<>());
            } else {
                responseForm.setIdeas(ideasByTitle);
            }
            responseForm.setStatus("0");
            responseForm.setMessage("success");
        } catch (Exception e) {
            e.printStackTrace();
            responseForm.setIdeas(new HashSet<Idea>());
            responseForm.setStatus("0");
            responseForm.setMessage("success");
        }
        return responseForm;
    }

    @PostMapping("/{username}/deleteIdea")
    public IdeaResponseForm deleteIdea( @RequestParam Long id) {
        IdeaResponseForm responseForm = new IdeaResponseForm();
        Idea idea = ideaRepository.findById(id).orElseThrow();
        idea.getCreator().getPersonalIdeas().remove(idea);
        idea.setCreator(null);

        ideaRepository.save(idea);

        ideaRepository.deleteById(id);

        
        responseForm.setId("" + id);
        responseForm.setStatus("0");
       
        return responseForm;
    }
    
}