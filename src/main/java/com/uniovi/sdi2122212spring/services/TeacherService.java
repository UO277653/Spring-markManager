package com.uniovi.sdi2122212spring.services;

import com.uniovi.sdi2122212spring.entities.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class TeacherService {

    private List<Teacher> teacherList = new LinkedList<Teacher>();

    @PostConstruct
    public void init() {
        teacherList.add(new Teacher(1L, "1", "Profesor 1", "SDI", "Ayudante"));
        teacherList.add(new Teacher(2L, "2", "Profesor 2", "SDI", "Catedrático"));
    }

    public List<Teacher> getTeacherList(){
        return this.teacherList;
    }

    public Teacher getTeacher(Long id){
        return teacherList.stream().filter(teacher -> teacher.getId().equals(id)).findFirst().get();
    }

    public void addTeacher(Teacher teacher){

        if (teacher.getId() == null) {
            teacher.setId(teacherList.get(teacherList.size() - 1).getId() + 1);
        }

        teacherList.add(teacher);

    }

    public void deleteTeacher(Long id) {
        teacherList.removeIf(teacher -> teacher.getId().equals(id));
    }

}
