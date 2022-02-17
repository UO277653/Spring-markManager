package com.uniovi.sdi2122212spring.services;

import com.uniovi.sdi2122212spring.entities.Teacher;
import com.uniovi.sdi2122212spring.repositories.TeachersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeachersRepository teachersRepository;

    @PostConstruct
    public void init() {
        teachersRepository.save(new Teacher(1L, "DNI1", "Profesor1", "Apellidos1", "Catedrático"));
        teachersRepository.save(new Teacher(2L, "DNI2", "Profesor2", "Apellidos2", "Ayudante"));
    }

    public Teacher getTeacher(Long id){
        return teachersRepository.findById(id).get();
    }

    public void addTeacher(Teacher teacher){

        teachersRepository.save(teacher);

    }

    public void deleteTeacher(Long id) {

        teachersRepository.deleteById(id);
    }

}
