package com.aditya.androidproject.api.android_final_project.controllers;

import java.util.HashSet;

import com.aditya.androidproject.api.android_final_project.forms.IdeaResponseForm;
import com.aditya.androidproject.api.android_final_project.forms.PersonLoginForm;
import com.aditya.androidproject.api.android_final_project.forms.PersonLoginReturnForm;
import com.aditya.androidproject.api.android_final_project.forms.PersonRegisterationForm;
import com.aditya.androidproject.api.android_final_project.models.Idea;
import com.aditya.androidproject.api.android_final_project.models.Person;

import com.aditya.androidproject.api.android_final_project.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserAccessContrioller {

    private final PersonRepository userAccessRepository;
   

    @Autowired
    public UserAccessContrioller(PersonRepository userAccessRepository) {
        this.userAccessRepository = userAccessRepository;
    }


    @PostMapping("/attemptLogin")
    public PersonLoginReturnForm AttemptLogin(@RequestBody PersonLoginForm personLoginForm) {
        Person person = null;
        try {
            person = userAccessRepository.findPersonByUserNameAndPassword(personLoginForm.getUserName(),
                    personLoginForm.getPassword());
        } catch (Exception e) {

        }
        PersonLoginReturnForm returnForm = new PersonLoginReturnForm();

        if (person != null) {
            returnForm.setStatus("0");
            returnForm.setMessage("success");
            returnForm.setPerson(person);
        } else {
            returnForm.setStatus("99");
            returnForm.setMessage("not found");

        }

        return returnForm;

    }

    @PostMapping("/registerUser")
    @ResponseBody
    public PersonLoginReturnForm RegisterLogin(@RequestBody PersonRegisterationForm personRegisterationForm) {

        PersonLoginReturnForm returnForm = new PersonLoginReturnForm();
        // if(!userAccessRepository.exists(Example.of(mPerson.getEmail()))){
        // returnForm.setStatus("0");
        // returnForm.setMessage("User Created Successfully");
        // returnForm.setPerson(userAccessRepository.save(mPerson));

        // }else{
        // returnForm.setStatus("100");
        // returnForm.setMessage("User already exists");

        // }

        if (userAccessRepository.findPersonByEmail(personRegisterationForm.getEmail()) == null) {
            if (userAccessRepository.findPersonByUserName(personRegisterationForm.getUserName()) == null) {

                Person mPerson = new Person();
                mPerson.setCountry(personRegisterationForm.getCountry());
                mPerson.setDob(personRegisterationForm.getDob());
                mPerson.setEmail(personRegisterationForm.getEmail());
                mPerson.setFirstName(personRegisterationForm.getFirstName());
                mPerson.setLastName(personRegisterationForm.getLastName());
                mPerson.setPassword(personRegisterationForm.getPassword());
                mPerson.setUserName(personRegisterationForm.getUserName());
                mPerson.setPeopleFollowed(new HashSet<Person>());
                mPerson.setSavedIdeasAsToDo(new HashSet<Idea>());

                returnForm.setPerson(userAccessRepository.save(mPerson));
                returnForm.setStatus("0");
                returnForm.setMessage("User Created Successfully");

            } else {
                returnForm.setStatus("101");
                returnForm.setMessage("Username already taken");

            }
        } else {
            returnForm.setStatus("100");
            returnForm.setMessage("User already exists");

        }

        return returnForm;
    }

    // @PostMapping("/editProfile")
    // public Person EditProfile(@RequestParam String userName, @RequestParam String
    // password, @RequestParam String country, @RequestParam String dob,
    // @RequestParam String firstName, @RequestParam String lastName, @RequestParam
    // String email) {

    // Person mPerson= new Person();

    // return userAccessRepository.attemptEditProfile(mPerson).orElseThrow();
    // }


    @GetMapping(value = "/getDetails/{username}/ideas")
    public IdeaResponseForm getIdeasForBrain(@PathVariable String username) {
        IdeaResponseForm responseForm = new IdeaResponseForm();
        try {
            responseForm.setIdeas(userAccessRepository.findPersonByUserName(username).getSavedIdeasAsToDo());
        } catch (Exception e) {
            e.printStackTrace();
            responseForm.setIdeas(new HashSet<Idea>());
        }
        return responseForm;
    }

    @PostMapping("/getDetails/{username}/todos")
    public IdeaResponseForm getTodosForBrain(@PathVariable String username) {

        IdeaResponseForm responseForm = new IdeaResponseForm();
        try {
            responseForm.setIdeas(userAccessRepository.findPersonByUserName(username).getSavedIdeasAsToDo());
            responseForm.setStatus("0");
            responseForm.setMessage("message");
        } catch (Exception e) {
            e.printStackTrace();
            responseForm.setStatus("109");
            responseForm.setMessage("message");
            responseForm.setIdeas(new HashSet<Idea>());
        }
        return responseForm;
    }

    @PostMapping("/getDetails/{username}/followers")
    public PersonLoginReturnForm getAllPersonFollwed(@PathVariable String userName) {
        PersonLoginReturnForm returnForm = new PersonLoginReturnForm();

        try {
            returnForm.setPerson(userAccessRepository.findPersonByUserName(userName));
            returnForm.setStatus("0");
            returnForm.setMessage("User Created Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            
            returnForm.setStatus("99");
            returnForm.setMessage("Please Try Again Later");
        }
        return returnForm;
    }

    @PostMapping("/updateUser")
    public PersonLoginReturnForm save(@RequestBody PersonRegisterationForm personRegisterationForm) {
        
        
        PersonLoginReturnForm returnForm = new PersonLoginReturnForm();
        Person mPerson = userAccessRepository.findPersonByUserName(personRegisterationForm.getUserName());
        
        mPerson.setCountry(personRegisterationForm.getCountry());
        mPerson.setDob(personRegisterationForm.getDob());
       
        mPerson.setFirstName(personRegisterationForm.getFirstName());
        mPerson.setLastName(personRegisterationForm.getLastName());
        
        
        returnForm.setPerson(userAccessRepository.save(mPerson));
        returnForm.setStatus("0");
        returnForm.setMessage("User Updated Successfully");
        return returnForm;
    }

    @PostMapping("/updateFollowingList")
    public Person UpdateFollowingList(@RequestBody PersonLoginForm personLoginForm) {
        Person self = userAccessRepository.findPersonByUserName(personLoginForm.getUserName());
        Person personToFollow =userAccessRepository.findPersonByUserName(personLoginForm.getUserNameToFollow());
        
        self.getPeopleFollowed().add(personToFollow);

        Person person = userAccessRepository.save(self);

        return person;
    }

    @PostMapping("/searchUser")
    public PersonLoginReturnForm SearchUser(@RequestBody PersonLoginForm personLoginForm) {
        
        Person person = userAccessRepository.findPersonByUserName(personLoginForm.getUserName());
        
        PersonLoginReturnForm returnForm = new PersonLoginReturnForm();
        returnForm.setStatus("0");
        returnForm.setMessage("");
        returnForm.setPerson(userAccessRepository.save(person));
       
        return returnForm;
    }

}
