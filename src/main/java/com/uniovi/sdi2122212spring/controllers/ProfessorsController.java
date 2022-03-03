package com.uniovi.sdi2122212spring.controllers;

import com.uniovi.sdi2122212spring.entities.Professor;
import com.uniovi.sdi2122212spring.services.ProfessorsService;
import com.uniovi.sdi2122212spring.validators.ProfessorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfessorsController {

        @Autowired
        private ProfessorsService professorsService;

        @Autowired
    private ProfessorValidator professorValidator;

        @RequestMapping("/professor/list")
        public String getList(Model model){
            model.addAttribute("professorsList", professorsService.getProfessors());
            return "professor/list";
        }

        @RequestMapping(value = "/professor/add")
        public String getProfessor(Model model) {
            model.addAttribute("professor", new Professor());
            return "professor/add";
        }

        @RequestMapping(value= "/professor/add", method = RequestMethod.POST)
        public String setProfessor(@Validated Professor professor, BindingResult result) {
            professorValidator.validate(professor, result);
            if(result.hasErrors()){
                return "professor/add";
            }
            professorsService.addProfessor(professor);
            return "redirect:/professor/list";
        }

        @RequestMapping("/professor/details/{id}")
        public String getDetail(Model model, @PathVariable Long id) {
            model.addAttribute("professor", professorsService.getProfessor(id));
            return "professor/details";
        }

        @RequestMapping("/professor/delete/{id}")
        public String deleteProfessor(@PathVariable Long id){
            professorsService.deleteProfessor(id);
            return "redirect:/professor/list";
        }

        @RequestMapping(value = "/professor/edit/{id}")
        public String getEdit(Model model, @PathVariable Long id) {
            model.addAttribute("professor", professorsService.getProfessor(id));
            return "professor/edit";
        }

        @RequestMapping(value="/professor/edit", method=RequestMethod.POST)
        public String setEdit(@ModelAttribute Professor professor) {
            System.out.println(professor.getId());
            professorsService.addProfessor(professor);
            System.out.println(professor.getId());
            return "redirect:/professor/details/" + professor.getId();
        }

}
