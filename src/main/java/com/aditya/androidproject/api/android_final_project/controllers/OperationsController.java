package com.aditya.androidproject.api.android_final_project.controllers;

import com.aditya.androidproject.api.android_final_project.forms.IdeaResponseForm;
import com.aditya.androidproject.api.android_final_project.forms.PersonLoginReturnForm;
import com.aditya.androidproject.api.android_final_project.forms.TodoForm;
import com.aditya.androidproject.api.android_final_project.models.Idea;
import com.aditya.androidproject.api.android_final_project.models.Person;
import com.aditya.androidproject.api.android_final_project.repository.IdeaRepository;
import com.aditya.androidproject.api.android_final_project.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperationsController {
    
    
    private final PersonRepository userAccessRepository;
    private final IdeaRepository ideaRepository;

    @Autowired
    public OperationsController(PersonRepository userAccessRepository, IdeaRepository ideaRepository) {
        this.userAccessRepository = userAccessRepository;
        this.ideaRepository = ideaRepository;
    }

    @PostMapping("/operations/registerIdea")
    public PersonLoginReturnForm assignIdeaToBrain(@RequestBody IdeaResponseForm newIdeaForm) {
        PersonLoginReturnForm returnForm= new PersonLoginReturnForm();
        try {
            Person person = userAccessRepository.findPersonByUserName(newIdeaForm.getUserName());

            Idea idea = new Idea();
            idea.setTitle(newIdeaForm.getTitle());
            idea.setContext(newIdeaForm.getContext());
            idea.setOriginalID(newIdeaForm.getOriginalID());
            idea.setContent(newIdeaForm.getContent());
            idea.setCreator(person);

            ideaRepository.save(idea);
            
person.getPersonalIdeas().add(idea);
Person savedPerson = userAccessRepository.save(person);
           
           
            returnForm.setStatus("0");
            returnForm.setMessage("idea registered");
            returnForm.setPerson(savedPerson);

            
        } catch (Exception e) {
            e.printStackTrace();
            returnForm.setStatus("102");
            returnForm.setMessage("idea registeration failed");
        }
        return returnForm;
    }

    @PostMapping("/operations/addToPersonalToDo")
    public PersonLoginReturnForm assignToDoToBrain(@RequestBody TodoForm todoForm) {
        PersonLoginReturnForm returnForm= new PersonLoginReturnForm();
        try {
            Long id= Long.valueOf(todoForm.getIdeaID());
            Idea idea = ideaRepository.findIdeaByid(id);
            Person person = userAccessRepository.findPersonByUserName(todoForm.getUserName());

            person.getSavedIdeasAsToDo().add(idea);

           
            returnForm.setStatus("0");
            returnForm.setMessage("idea registered");
            returnForm.setPerson(userAccessRepository.save(person));
            
        } catch (Exception e) {
            e.printStackTrace();
            returnForm.setStatus("103");
            returnForm.setMessage("todo failed");
        }

        return returnForm;
    }
    
    

  
}