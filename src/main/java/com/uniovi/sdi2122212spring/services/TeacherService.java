package com.uniovi.sdi2122212spring.services;

import com.uniovi.sdi2122212spring.entities.Professor;
import com.uniovi.sdi2122212spring.repositories.TeachersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TeacherService {

    @Autowired
    private TeachersRepository teachersRepository;

    @PostConstruct
    public void init() {
        teachersRepository.save(new Professor(1L, "DNI1", "Profesor1", "Apellidos1", "Catedrático"));
        teachersRepository.save(new Professor(2L, "DNI2", "Profesor2", "Apellidos2", "Ayudante"));
    }

    public Professor getTeacher(Long id){
        return teachersRepository.findById(id).get();
    }

    public void addTeacher(Professor teacher){

        teachersRepository.save(teacher);

    }

    public void deleteTeacher(Long id) {

        teachersRepository.deleteById(id);
    }

}
