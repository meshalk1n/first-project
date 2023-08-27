package org.example.controllers;

import org.example.dao.PersonDAO;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {
    private PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO){
        this.personDAO = personDAO;
    }
    @GetMapping() //если бы мы написали "/show", то путь был бы "people/show", а пока он "people/"
    public String allPeople(Model model){
        model.addAttribute("people", personDAO.getPeople());
        return "people/show";
    }
    @GetMapping("/{id}")
    public String fetchPersonById(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.getPersonById(id));
        return "people/id";
    }
    @GetMapping("/new")
    public String showFormForNewPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }
    @PostMapping()
    public String saveNewPerson(@ModelAttribute("person") Person person){
        personDAO.addPerson(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String showEditForm(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.getPersonById(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String processEditForm(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        personDAO.updatedPerson(id,person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        personDAO.deletePerson(id);
        return "redirect:/people";
    }
}









