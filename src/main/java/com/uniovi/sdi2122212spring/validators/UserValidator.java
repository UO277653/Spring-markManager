package com.uniovi.sdi2122212spring.validators;

import com.uniovi.sdi2122212spring.entities.*;
import com.uniovi.sdi2122212spring.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UsersService usersService;
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Mark mark = (Mark) target;

        if(mark.getScore() < 0 || mark.getScore() > 10){
            errors.rejectValue("score","Error.mark.score.limit");
        }

        if(mark.getDescription().length() < 20){
            errors.rejectValue("description", "Error.mark.description.length");
        }
    }
}
