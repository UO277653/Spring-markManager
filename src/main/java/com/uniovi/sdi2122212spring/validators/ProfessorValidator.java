package com.uniovi.sdi2122212spring.validators;

import com.uniovi.sdi2122212spring.entities.Professor;
import com.uniovi.sdi2122212spring.services.ProfessorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Controller
public class ProfessorValidator implements Validator {

    @Autowired
    private ProfessorsService professorsService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Professor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Professor professor = (Professor) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "Error.empty");
        String dni = professor.getDni();
        if(dni.length()!=9 || !Character.isAlphabetic(dni.charAt(dni.length()-1))){
            errors.rejectValue("dni", "Error.professor.dni.format");
        }
        if(! (professorsService.getProfessorByDni(dni)==null)){
            errors.rejectValue("dni", "Error.professor.dni.duplicate");
        }
    }
}
