package com.uniovi.sdi2122212spring.services;

import com.uniovi.sdi2122212spring.entities.Professor;
import com.uniovi.sdi2122212spring.repositories.ProfessorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorsService {

    @Autowired
    private ProfessorsRepository professorsRepository;

    public List<Professor> getProfessors() {
        List<Professor> professors = new ArrayList<Professor>();
        professorsRepository.findAll().forEach((professors::add));
        return professors;
    }

    public Professor getProfessor(Long id) {
        return professorsRepository.findById(id).get();
    }

    public Professor getProfessorByDni(String dni){
        return professorsRepository.findByDni(dni);
    }

    public void addProfessor(Professor professor) {
        professorsRepository.save(professor);
    }

    public void deleteProfessor(Long id) {
        professorsRepository.deleteById(id);
    }

}
